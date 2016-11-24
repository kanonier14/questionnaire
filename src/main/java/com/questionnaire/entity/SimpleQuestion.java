package com.questionnaire.entity;

import com.questionnaire.core.QuestionType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by Igor on 12.11.2016.
 */
public class SimpleQuestion {

    @Id
    private String idQuestion;

    private String title;

    private QuestionType questionType;

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @DBRef
    private List<SimpleAnswer> answers;

    public List<SimpleAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SimpleAnswer> answers) {
        this.answers = answers;
    }

    public SimpleQuestion() {
    }

    public String getIdQuestion() {

        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
