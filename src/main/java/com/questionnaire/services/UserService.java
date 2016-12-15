package com.questionnaire.services;

import com.questionnaire.entity.User;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor_Strakhov
 */
public interface UserService {

    User saveUser(UserXtrCounters userInfo);
    boolean isAuthenticate(HttpServletRequest request);
}
