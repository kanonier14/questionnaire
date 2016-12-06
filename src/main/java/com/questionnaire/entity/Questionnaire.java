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

     private boolean gated;

    @DBRef
    private List<SimpleQuestion> questions;

    @DBRef
    private User author;

    public List<SimpleQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SimpleQuestion> questions) {
        this.questions = questions;
    }

    public boolean isGated() {
        return gated;
    }

    public void setGated(boolean gated) {
        this.gated = gated;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
