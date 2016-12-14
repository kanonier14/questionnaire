package com.questionnaire.services;

import com.questionnaire.entity.Questionnaire;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Igor on 13.12.2016.
 */
public interface FilterService {

    List<Questionnaire> filterAuthQuestionnaire(HttpServletRequest request, String topic,
                                                String dateOrder);
}
