package com.questionnaire.core;

/**
 * @author Igor_Strakhov
 */
public enum QuestionType {

    RADIO("radio"),
    CHECKBOX("checkbox"),
    OPEN("open");

    private String name;

    QuestionType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
