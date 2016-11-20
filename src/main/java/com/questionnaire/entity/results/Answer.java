package com.questionnaire.entity.results;

/**
 * Created by Igor on 21.11.2016.
 */
public class Answer {

    private String id;
    private String title;
    private Integer number;

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
}
