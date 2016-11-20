package com.questionnaire.services;

import com.questionnaire.entity.SimpleAnswer;

import java.util.List;
import java.util.Map;

/**
 * Created by Igor on 20.11.2016.
 */
public interface SimpleAnswerService {

    public List<SimpleAnswer> createAnswersFromRequestParameters(Map<String, String[]> parameters, String question);
}
