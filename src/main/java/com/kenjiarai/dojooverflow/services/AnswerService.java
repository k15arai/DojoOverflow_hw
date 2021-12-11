package com.kenjiarai.dojooverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenjiarai.dojooverflow.models.Answer;
import com.kenjiarai.dojooverflow.models.Question;
import com.kenjiarai.dojooverflow.repositories.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repository;
	
	// CREATE
	public Answer create(Answer item) {
		return this.repository.save(item);
	}
	
	// READ ALL
	public List<Answer> all() {
		return this.repository.findAll();
	}
	
	// READ ONE - return one id. get after finding
	public Answer retrieve(Long id) {
		return this.repository.findById(id).get();
	}
	
	// Update - similar to save
	public Answer update(Answer item) {
		return this.repository.save(item);
	}
	
	// Delete
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
	
	// Find ALL Answers for a particular Question
	public List<Answer> includedAnswers(Question question) {
		return this.repository.findAllByQuestion(question);
	}

	// Answers a question is not associated with
	public List<Answer> excludedAnswers(Question question) {
		return this.repository.findByQuestionNotContains(question);
	}
}
