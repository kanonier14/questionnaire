package com.questionnaire.repository;

import com.questionnaire.entity.AnswerToQuestion;
import com.questionnaire.entity.SimpleQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Igor on 20.11.2016.
 */
public interface AnswerToQuestionRepository extends MongoRepository<AnswerToQuestion, String> {

    List<AnswerToQuestion> findByQuestion(SimpleQuestion question);
}
