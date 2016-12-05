package com.questionnaire.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by Igor on 06.12.2016.
 */
public class User {

    @Id
    private String userId;

    private Integer vkontakteId;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getVkontakteId() {
        return vkontakteId;
    }

    public void setVkontakteId(Integer vkontakteId) {
        this.vkontakteId = vkontakteId;
    }
}
