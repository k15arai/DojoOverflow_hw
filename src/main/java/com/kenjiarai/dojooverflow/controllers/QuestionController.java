package com.kenjiarai.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kenjiarai.dojooverflow.models.Answer;
import com.kenjiarai.dojooverflow.models.Question;
import com.kenjiarai.dojooverflow.models.Tag;
import com.kenjiarai.dojooverflow.models.TagQuestion;
import com.kenjiarai.dojooverflow.services.QuestionService;
import com.kenjiarai.dojooverflow.services.TagQuestionService;
import com.kenjiarai.dojooverflow.services.TagService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private TagQuestionService tagQuestionService;

	// Questions Dashboard Page
	@GetMapping("/questions")
	public String dashboard(Model model) {
		model.addAttribute("questions", this.questionService.all());
		return "questionsDashboard.jsp";
	}
	
	// New Question Page
	@GetMapping("/questions/new")
	public String questions(@ModelAttribute("item") Question question) {
		return "newQuestion.jsp";
	}
	
	// Create a Question add Tags
	@PostMapping("/questions/new")
	public String add(
			@Valid @ModelAttribute("item") Question question,
			BindingResult result, 
			@RequestParam(value="question_tags") String questionTags,
			RedirectAttributes redirectAttributes) {	
		
		if ( result.hasErrors() ) return "newQuestion.jsp";

		Question currentQuestion = this.questionService.create(question);
		
		// Return error if tags area left blank
		if ( questionTags == "" ) {
			redirectAttributes.addFlashAttribute("message", "Please enter some tags.");
			return "redirect:/questions/new";
		}
		
		// Split tags by comma
		String[] splitQuestionTags = questionTags.toLowerCase().trim().split("\\s*,\\s*");
		ArrayList<String> tagsArrayList = new ArrayList<String>(
			Arrays.asList(splitQuestionTags)
		);
		
		// Return error if more than 3 tags are submitted
		if (tagsArrayList.size() > 3) {
			redirectAttributes.addFlashAttribute("message", "Please enter only 3 tags separated by commas.");
			return "redirect:/questions/new";
		}

		// Save the unique tags to the database
		for (String s: tagsArrayList) {
			
			// Create Tag if it doesn't exist
			if (!tagService.checkTag(s)) {
				Tag currentTag = this.tagService.create(new Tag(s));
				TagQuestion currentTagQuestion = new TagQuestion(currentTag, currentQuestion);
				this.tagQuestionService.create(currentTagQuestion);
			} else {
				Tag existingTag = this.tagService.findTag(s);
				TagQuestion existingTagQuestion = new TagQuestion(existingTag, currentQuestion);
				this.tagQuestionService.create(existingTagQuestion);
			}
		}
		
		redirectAttributes.addFlashAttribute("message", "Your question and tags have been added.");
		return "redirect:/questions";
		
	}
	
	// View Question and Answer Form page
	@GetMapping("/questions/{id}")
	public String viewQuestion (
			@PathVariable Long id, 
			@ModelAttribute("submission") Answer answer,
			Model model) {
		model.addAttribute("question", this.questionService.retrieve(id));
		model.addAttribute("tags", this.questionService.retrieve(id).getTags());
		model.addAttribute("answers", this.questionService.retrieve(id).getAnswers());
		
		return "viewQuestion.jsp";
	}
	
	// Delete Question
	@GetMapping("/questions/delete/{id}")
	public String deleteQuestion (
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes
			) {
		this.questionService.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "Your Question has been deleted.");
		
		return "redirect:/questions/";
	}
	
}
