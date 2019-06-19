package iducs.springboot.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Answer;
import iducs.springboot.board.domain.Question;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.service.AnswerService;
import iducs.springboot.board.service.QuestionService;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired AnswerService answerService; // 의존성 주입(Dependency Injection) 
	@Autowired QuestionService questionService;
		
	@PostMapping("")
	// public String createUser(Answer answer, Model model, HttpSession session) {
	public String createAnswer(@PathVariable Long questionId, String contents,HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Question question = questionService.getQuestionById(questionId);
		Answer newAnswer = new Answer(sessionUser, contents, question);
		System.out.println(sessionUser.getUserId() + "//" + contents + "//" + question.getTitle());		
		answerService.saveAnswer(newAnswer);
		return String.format("redirect:/questions/%d", questionId);
	}
}
