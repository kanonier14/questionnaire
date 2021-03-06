package com.questionnaire.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Igor on 20.11.2016.
 */
public class AnswerToQuestion {

    @Id
    private String idAnswer;

    @DBRef
    private SimpleQuestion question;

    @DBRef
    private List<SimpleAnswer> answers;

    private String openAnswer;

    @DBRef
    private User answeredUser;

    public AnswerToQuestion() {
    }

    public String getIdAnswer() {

        return idAnswer;
    }

    public void setIdAnswer(String idAnswer) {
        this.idAnswer = idAnswer;
    }

    public SimpleQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SimpleQuestion question) {
        this.question = question;
    }

    public List<SimpleAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SimpleAnswer> answers) {
        this.answers = answers;
    }

    public User getAnsweredUser() {
        return answeredUser;
    }

    public void setAnsweredUser(User answeredUser) {
        this.answeredUser = answeredUser;
    }

    public String getOpenAnswer() {
        return openAnswer;
    }

    public void setOpenAnswer(String openAnswer) {
        this.openAnswer = openAnswer;
    }
}
