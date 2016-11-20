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
}
