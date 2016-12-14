package com.questionnaire.entity;

import com.questionnaire.core.Topic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Igor on 12.11.2016.
 */
public class Questionnaire {

    @Id
    private String idQuestionnaire;

    private String title;

    private boolean gated;

    private long creationDate;

    private Topic topic;

    @DBRef
    private List<SimpleQuestion> questions;

    @DBRef
    private User author;



    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
