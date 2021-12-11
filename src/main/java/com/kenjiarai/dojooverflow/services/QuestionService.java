package com.kenjiarai.dojooverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenjiarai.dojooverflow.models.Question;
import com.kenjiarai.dojooverflow.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository repository;
	
	// CREATE
	public Question create(Question item) {
		return this.repository.save(item);
	}
	
	// READ ALL
	public List<Question> all() {
		return this.repository.findAll();
	}
	
	// READ ONE - return one id. get after finding
	public Question retrieve(Long id) {
		return this.repository.findById(id).get();
	}
	
	// Update - similar to save
	public Question update(Question item) {
		return this.repository.save(item);
	}
	
	// Delete
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

}
