package iducs.springboot.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Answer;
import iducs.springboot.board.domain.Board;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.exception.ResourceNotFoundException;
import iducs.springboot.board.repository.UserRepository;
import iducs.springboot.board.service.BoardService;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.util.HttpSessionUtils;

@Controller
@RequestMapping("/questions")
public class BoardController {
	@Autowired BoardService questionService; // 의존성 주입(Dependency Injection) : 
	
	@GetMapping("")
	public String getAllQuestion(Model model, HttpSession session, Long pageNo) {
		User writer = (User) session.getAttribute("user");
		if(pageNo == null)
			pageNo = new Long(1);
		if(writer != null)
			model.addAttribute("isLogined", writer);
		List<Board> questions = questionService.getQuestions(pageNo);
		model.addAttribute("questions", questions);
		return "/questions/list"; 
	}	
	
	@PostMapping("")
	// public String createUser(Question question, Model model, HttpSession session) {
	public String createQuestion(String title, String contents, Model model, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Board newQuestion = new Board(title, sessionUser, contents);		
		// Question newQuestion = new Question(question.getTitle(), writer, question.getContents());	
		questionService.saveQuestion(newQuestion);
		return "redirect:/questions"; // get 방식으로  리다이렉션 - Controller를 통해 접근
	}
	
	@GetMapping("/{id}")
	public String getQuestionById(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Board question = questionService.getQuestionById(id);
		User writer = question.getWriter();
		
		if(sessionUser == null)
			return "redirect:/users/login-form";
		
		if(sessionUser.equals(writer))
			model.addAttribute("isOwner", true);
		
		
		model.addAttribute("size", question.getAnswers().size());
		model.addAttribute("sessionUser", sessionUser);
		model.addAttribute("question", question);
		return "/questions/info";
	}
	
	@GetMapping("/{id}/form")
	public String getUpdateForm(@PathVariable(value = "id") Long id, Model model) {
		Board question = questionService.getQuestionById(id);
		User wirter = question.getWriter();
		List<Answer> answers = question.getAnswers();
		model.addAttribute("question", question);
		model.addAttribute("answers", answers);
		return "/questions/edit";
	}
	
	@PutMapping("/{id}")
	public String updateQuestionById(@PathVariable(value = "id") Long id, @Valid Board formQuestion, String title, String contents, Model model) {
		Board question = questionService.getQuestionById(id);
		question.setTitle(title);
		question.setContents(contents);
		
		questionService.updateQuestion(question);		
		return "redirect:/questions/" + id;
	}
	
	@DeleteMapping("/{id}")
	public String deleteQuestionById(@PathVariable(value = "id") Long id, Model model) {
		Board question = questionService.getQuestionById(id);
		questionService.deleteQuestion(question);
		model.addAttribute("userId", question.getWriter().getUserId());
		return "/questions/withdrawal";
	}
}
