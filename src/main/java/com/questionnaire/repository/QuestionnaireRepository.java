package com.questionnaire.repository;

import com.questionnaire.entity.Questionnaire;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Igor on 20.11.2016.
 */
public interface QuestionnaireRepository extends MongoRepository<Questionnaire, String> {
    Questionnaire findByIdQuestionnaire(String id);
}
