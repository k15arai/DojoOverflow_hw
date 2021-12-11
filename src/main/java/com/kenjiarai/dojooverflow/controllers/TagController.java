package com.kenjiarai.dojooverflow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kenjiarai.dojooverflow.services.TagService;

@Controller
public class TagController {
	
	@Autowired
	TagService tagService;
	
	// View All Tags
	@GetMapping("/tags")
	public String viewTags(Model model) {
		model.addAttribute("tags", tagService.all());
		return "viewTags.jsp";
	}
	
	// Delete Tag
	@GetMapping("/tags/delete/{id}")
	public String deleteTag (
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes
			) {
		this.tagService.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "Your Tag has been deleted.");
		
		return "redirect:/tags/";
	}	
	
	
}
