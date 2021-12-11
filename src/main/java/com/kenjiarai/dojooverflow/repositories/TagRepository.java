package com.kenjiarai.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kenjiarai.dojooverflow.models.Question;
import com.kenjiarai.dojooverflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
	List<Tag> findAll();
	
	// Retrieves a list of all tags for a particular question
	List<Tag> findAllByQuestions(Question question);
	
	// Retrieves a list of any tags a question is not associated with
	List<Tag> findByQuestionsNotContains(Question question);
	
	// Retrieves a list of any tags that matches a tag
	boolean existsTagBySubject(String subject);
	
	// Find tag by string
	Tag findDistinctBySubject(String subject);
}