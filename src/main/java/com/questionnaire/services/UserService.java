package com.questionnaire.services;

import com.questionnaire.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor_Strakhov
 */
public interface UserService {

    User saveUser(Integer vkontakteId);
    boolean isAuthenticate(HttpServletRequest request);
}
