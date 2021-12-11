package com.kenjiarai.dojooverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenjiarai.dojooverflow.models.TagQuestion;
import com.kenjiarai.dojooverflow.repositories.TagQuestionRepository;

@Service
public class TagQuestionService {

	@Autowired
	private TagQuestionRepository repository;
	
	// CREATE
	public TagQuestion create(TagQuestion item) {
		return this.repository.save(item);
	}
	
	// READ ALL
	public List<TagQuestion> all() {
		return this.repository.findAll();
	}
	
	// READ ONE - return one id. get after finding
	public TagQuestion retrieve(Long id) {
		return this.repository.findById(id).get();
	}
	
	// Update - similar to save
	public TagQuestion update(TagQuestion item) {
		return this.repository.save(item);
	}
	
	// Delete
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
}