package com.questionnaire.entity.results;

import java.util.List;

/**
 * Created by Igor on 21.11.2016.
 */
public class Question {
    private String id;
    private String title;
    private List<Answer> answers;

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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question() {

    }
}