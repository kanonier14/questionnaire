package com.questionnaire.services;

import com.questionnaire.entity.SimpleQuestion;

import java.util.List;
import java.util.Map;

/**
 * Created by Igor on 20.11.2016.
 */
public interface SimpleQuestionService {

    List<SimpleQuestion> createQuestionsFromRequestParameters(Map<String, String[]> parameters);
}
