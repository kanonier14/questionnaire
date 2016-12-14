package com.questionnaire.services.impl;

import com.questionnaire.core.Topic;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.sessionentity.LoginState;
import com.questionnaire.repository.QuestionnaireRepository;
import com.questionnaire.services.FilterService;
import com.questionnaire.services.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
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
    public List<Questionnaire> filterAuthQuestionnaire(HttpServletRequest request, String topic,
                                                       String dateOrder) {
        List<Questionnaire> resultList;
        if (request.getSession().getAttribute("authenticate") != null) {
            LoginState loginState = (LoginState)sessionCache.get(request, LoginState.class);
            resultList = questionnaireRepository.findAll().stream()
                    .filter(questionnaire -> {
                        boolean flag = !questionnaire.getAuthor().getVkontakteId().equals(loginState.getVkId());
                        if (topic != null && !topic.isEmpty() && !"ALL".equals(topic)) {
                            flag = flag && questionnaire.getTopic().equals(Topic.valueOf(topic));
                        }
                        return flag;
                    })
                    .collect(Collectors.toList());
        } else {
            resultList = questionnaireRepository.findAll().stream()
                    .filter(questionnaire -> {
                        boolean flag = !questionnaire.isGated();
                        if (topic != null && !topic.isEmpty() && !"ALL".equals(topic)) {
                            flag = flag && questionnaire.getTopic().equals(Topic.valueOf(topic));
                        }
                        return flag;
                    })
                    .collect(Collectors.toList());
        }
        Collections.sort(resultList, (q1,q2) -> {
            if (dateOrder == null || dateOrder.isEmpty() || "asc".equals(dateOrder)) {
                long resCompare = q2.getCreationDate() - q1.getCreationDate();
                if (resCompare == 0) {
                    return 0;
                } else if (resCompare > 0) {
                    return 1;
                } else return -1;
            } else {
                long resCompare = q1.getCreationDate() - q2.getCreationDate();
                if (resCompare == 0) {
                    return 0;
                } else if (resCompare > 0) {
                    return 1;
                } else return -1;
            }
        });
        return resultList;
    }
}
