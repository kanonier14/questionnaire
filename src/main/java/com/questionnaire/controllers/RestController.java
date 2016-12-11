package com.questionnaire.controllers;

import com.questionnaire.entity.Questionnaire;
import com.questionnaire.repository.QuestionnaireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor_Strakhov
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @RequestMapping("/questionnaires/get")
    public QuestionnaireResult getQuestionnaires(HttpServletRequest request) {
        List<Questionnaire> questionnaires = new ArrayList<>();
        if (request.getParameterMap().size() == 0) {
            questionnaires = questionnaireRepository.findAll();
            Collections.sort(questionnaires, (o1, o2) -> {
                long resCompare = o1.getCreationDate() - o2.getCreationDate();
                if (resCompare == 0) {
                    return 0;
                } else if (resCompare > 0) {
                    return 1;
                } else return -1;
            });
        }
        QuestionnaireResult result = new QuestionnaireResult();
        result.setQuestionnaires(questionnaires.subList(0,9));
        result.setPagesCount((int) Math.ceil(questionnaires.size() / 9));
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
