package com.questionnaire.controllers;

import com.questionnaire.core.State;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.sessionentity.LoginState;
import com.questionnaire.repository.QuestionnaireRepository;

import com.questionnaire.services.FilterService;
import com.questionnaire.services.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor_Strakhov
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    QuestionnaireRepository questionnaireRepository;
    @Autowired
    FilterService filterService;

    @RequestMapping("/questionnaires/get")
    public QuestionnaireResult getQuestionnaires(HttpServletRequest request) {
        String topic = request.getParameter("topic");
        String dateOrder = request.getParameter("dateOrder");
        List<Questionnaire> questionnaires = filterService.filterAuthQuestionnaire(request, topic, dateOrder);
        questionnaires = questionnaires.stream()
                            .filter(questionnaire -> State.STARTED.equals(questionnaire.getState()))
                            .collect(Collectors.toList());
        QuestionnaireResult result = new QuestionnaireResult();
        Integer requestedPage;
        if (request.getParameter("pagenumber") != null) {
            requestedPage = Integer.valueOf(request.getParameter("pagenumber"));
            int fromIndex = 9*(requestedPage-1);
            int toIndex = questionnaires.size() > 9*requestedPage ? 9*requestedPage : questionnaires.size();
            result.setQuestionnaires(questionnaires.subList(fromIndex, toIndex));
        }
        if (request.getParameterMap().size() == 0) {
            int fromIndex = 0;
            int toIndex = questionnaires.size() > 9 ? 9 : questionnaires.size();
            result.setQuestionnaires(questionnaires.subList(fromIndex, toIndex));
        }

        result.setPagesCount((int) Math.ceil(questionnaires.size() / 9.0));
        return result;
    }

    public class QuestionnaireResult {

        private int pagesCount;
        private List<Questionnaire> questionnaires;

        public int getPagesCount() {
            return pagesCount;
        }

        public void setPagesCount(int pagesCount) {
            this.pagesCount = pagesCount;
        }

        public List<Questionnaire> getQuestionnaires() {
            return questionnaires;
        }

        public void setQuestionnaires(List<Questionnaire> questionnaires) {
            this.questionnaires = questionnaires;
        }
    }
}
