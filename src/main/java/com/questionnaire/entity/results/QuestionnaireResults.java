package com.questionnaire.entity.results;

import java.util.List;

/**
 * Created by Igor on 21.11.2016.
 */
public class QuestionnaireResults {

    private String id;
    private String title;
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionnaireResults() {

    }
}
