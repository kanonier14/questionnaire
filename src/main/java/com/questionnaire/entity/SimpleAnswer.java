package com.questionnaire.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Igor on 20.11.2016.
 */
public class SimpleAnswer {

    @Id
    private String idAnswer;

    private String title;

    public List<AnswerToQuestion> getAnswersToQuestion() {
        return answersToQuestion;
    }

    public void setAnswersToQuestion(List<AnswerToQuestion> answersToQuestion) {
        this.answersToQuestion = answersToQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleAnswer answer = (SimpleAnswer) o;

        if (!idAnswer.equals(answer.idAnswer)) return false;
        return title != null ? title.equals(answer.title) : answer.title == null;

    }

    @Override
    public int hashCode() {
        int result = idAnswer.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @DBRef

    private List<AnswerToQuestion> answersToQuestion;

    public String getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(String idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SimpleAnswer() {

    }
}
