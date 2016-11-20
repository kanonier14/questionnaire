package com.questionnaire.repository;

import com.questionnaire.entity.SimpleQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Igor on 20.11.2016.
 */
public interface SimpleQuestionRepository extends MongoRepository<SimpleQuestion, String> {

    SimpleQuestion findByIdQuestion(String id);
}
