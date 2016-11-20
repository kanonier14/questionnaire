package com.questionnaire.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Igor on 12.11.2016.
 */
public class Questionnaire {

    @Id
    private String idQuestionnaire;

    private String title;

    @DBRef
    private List<SimpleQuestion> questions;

    public List<SimpleQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SimpleQuestion> questions) {
        this.questions = questions;
    }

    public Questionnaire() {

    }

    public String getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(String idQuestionnaire) {
        this.idQuestionnaire = idQuestionnaire;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
