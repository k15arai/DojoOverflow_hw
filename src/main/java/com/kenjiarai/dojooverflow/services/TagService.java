package com.kenjiarai.dojooverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenjiarai.dojooverflow.models.Question;
import com.kenjiarai.dojooverflow.models.Tag;
import com.kenjiarai.dojooverflow.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository repository;
	
	// CREATE
	public Tag create(Tag item) {
		return this.repository.save(item);
	}
	
	// Check if tag exists
	public boolean checkTag(String item) {
		boolean isExists = repository.existsTagBySubject(item);
		if (isExists) {
			return true;
		} else {
			return false;
//			Tag tag = new Tag();
//			tag.setSubject(item);
//			this.repository.save(tag);
		}
	}
	
	// Retrieve tag
	public Tag findTag(String item) {
		return repository.findDistinctBySubject(item);
	}
	
	// READ ALL
	public List<Tag> all() {
		return this.repository.findAll();
	}
	
	// READ ONE - return one id. get after finding
	public Tag retrieve(Long id) {
		return this.repository.findById(id).get();
	}
	
	// Update - similar to save
	public Tag update(Tag item) {
		return this.repository.save(item);
	}
	
	// Delete
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
	
	// Find ALL Tags for a particular Question
	public List<Tag> includedTags(Question question) {
		return this.repository.findAllByQuestions(question);
	}

	// Tags a question is not associated with
	public List<Tag> excludedTags(Question question) {
		return this.repository.findByQuestionsNotContains(question);
	}
	
	// Find ALL Tags or particular Tag
	public Boolean checkForTag(String item) {
		return this.repository.existsTagBySubject(item);
	}
}
