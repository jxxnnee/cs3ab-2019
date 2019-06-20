package iducs.springboot.board.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Answer;
import iducs.springboot.board.domain.Board;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.service.AnswerService;
import iducs.springboot.board.service.BoardService;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired AnswerService answerService; // 의존성 주입(Dependency Injection) 
	@Autowired BoardService questionService;
		
	@PostMapping("")
	// public String createUser(Answer answer, Model model, HttpSession session) {
	public String createAnswer(@PathVariable Long questionId, String contents,HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Board question = questionService.getQuestionById(questionId);
		Answer newAnswer = new Answer(sessionUser, contents, question);
			
		answerService.saveAnswer(newAnswer);
		return String.format("redirect:/questions/%d", questionId);
	}
	
	@DeleteMapping("/{id}")
	public String deleteAnswerById(@PathVariable(value = "id") Long id, Model model) {
		Answer answer = answerService.getAnswerById(id);
		answerService.deleteAnswer(answer);		
		return String.format("redirect:/questions/%d", answer.getQuestion().getId());
	}
	
	@GetMapping("/{id}/form")
	public String getUpdateForm(@PathVariable(value = "id") Long id, Model model) {
		Answer answer = answerService.getAnswerById(id);
		User wirter = answer.getWriter();
		model.addAttribute("answer", answer);
		return "/questions/answeredit";
	}
	
	@PutMapping("/{id}")
	public String updateAnswerById(@PathVariable(value = "id") Long id, @Valid Answer formAnswer, String title, String contents, Model model) {
		Answer answer = answerService.getAnswerById(id);
		answer.setContents(formAnswer.getContents());
		answerService.updateAnswer(answer);		
		return "redirect:/questions/" + answer.getQuestion().getId();
	}
}
