package com.kenjiarai.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kenjiarai.dojooverflow.models.Answer;
import com.kenjiarai.dojooverflow.models.Question;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long>{
	List<Answer> findAll();
	
	// Retrieves a list of all answers for a particular question
	List<Answer> findAllByQuestion(Question question);
	
	// Retrieves a list of any tags a question is not associated with
	List<Answer> findByQuestionNotContains(Question question);
	
}
