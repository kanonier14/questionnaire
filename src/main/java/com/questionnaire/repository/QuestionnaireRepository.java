package com.questionnaire.repository;

import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Igor on 20.11.2016.
 */
public interface QuestionnaireRepository extends MongoRepository<Questionnaire, String> {
    Questionnaire findByIdQuestionnaire(String id);
    List<Questionnaire> findByAuthor(User author);
}
