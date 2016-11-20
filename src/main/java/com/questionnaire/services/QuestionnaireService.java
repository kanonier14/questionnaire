package com.questionnaire.services;

import com.questionnaire.entity.results.QuestionnaireResults;

/**
 * Created by Igor on 21.11.2016.
 */
public interface QuestionnaireService {

    QuestionnaireResults getResults(String id);
}
