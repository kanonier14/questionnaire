package com.questionnaire.entity.results;

import java.util.List;

/**
 * Created by Igor on 21.11.2016.
 */
public class Answer {

    private String id;
    private String title;
    private Integer number;
    private List<String> openAnswers;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Answer() {

    }

    public List<String> getOpenAnswers() {
        return openAnswers;
    }

    public void setOpenAnswers(List<String> openAnswers) {
        this.openAnswers = openAnswers;
    }
}
