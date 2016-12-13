package com.questionnaire.services.impl;

import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.sessionentity.LoginState;
import com.questionnaire.repository.QuestionnaireRepository;
import com.questionnaire.services.FilterService;
import com.questionnaire.services.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Igor on 13.12.2016.
 */
@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    QuestionnaireRepository questionnaireRepository;
    @Autowired
    SessionCache sessionCache;

    @Override
    public List<Questionnaire> filterAuthQuestionnaire(HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticate") != null) {
            LoginState loginState = (LoginState)sessionCache.get(request, LoginState.class);
            return questionnaireRepository.findAll().stream()
                    .filter(questionnaire -> !questionnaire.getAuthor().getVkontakteId().equals(loginState.getVkId()))
                    .collect(Collectors.toList());
        } else {
            return questionnaireRepository.findAll().stream()
                    .filter(questionnaire -> !questionnaire.isGated())
                    .collect(Collectors.toList());
        }
    }
}
