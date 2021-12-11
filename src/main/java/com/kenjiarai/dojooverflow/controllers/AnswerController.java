package com.kenjiarai.dojooverflow.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kenjiarai.dojooverflow.models.Answer;
import com.kenjiarai.dojooverflow.models.Question;
import com.kenjiarai.dojooverflow.services.AnswerService;
import com.kenjiarai.dojooverflow.services.QuestionService;

@Controller
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionService questionService;
	
	
	// Go to specific question page with answers
	@PostMapping("/answers/add")
	public String add(
			@Valid @ModelAttribute("submission") Answer answer,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			@RequestParam(value="questionId") Long questionId) {
		
		System.out.println(questionId); // prints out questionId in console
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "An Answer must be included.");
			return String.format("redirect:/questions/%d", questionId);
		}
		
		// Create objects for answer and question
		Answer thisAnswer = this.answerService.create(answer);
		Question thisQuestion = this.questionService.retrieve(questionId);
		// System.out.println(thisQuestion.getId()); // prints out id in console
		
		// Add answer to answers list through question
		 thisAnswer.setQuestion(thisQuestion); // this worked. does this need to be assigned from the child?
		
		// thisQuestion.getAnswers().add(thisAnswer); // this did not work - not sure why though. 
		// i suspect it's because there is not connecting id in the questions table.
		// Create a List from getAnswers and then add the answer
//		List<Answer> currentAnswers = thisQuestion.getAnswers();
//		currentAnswers.add(thisAnswer);
//		thisQuestion.setAnswers(currentAnswers);
		
		this.questionService.update(thisQuestion);
		
		redirectAttributes.addFlashAttribute("message", "An Answer has been added to your Question.");

		return String.format("redirect:/questions/%d", questionId);
		
	}
	
	// Delete Answer
	@GetMapping("/answers/delete/{id}")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes
			) {
		this.answerService.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "Your Answer has been deleted.");
		
		return "redirect:/questions";
	}
	
	
	
	
	
}
