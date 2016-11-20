package com.questionnaire.repository;

import com.questionnaire.entity.SimpleAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Igor on 20.11.2016.
 */
public interface SimpleAnswerRepository extends MongoRepository<SimpleAnswer, String> {

    SimpleAnswer findByIdAnswer(String id);
}
