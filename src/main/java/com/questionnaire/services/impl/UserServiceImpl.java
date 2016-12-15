package com.questionnaire.services.impl;

import com.questionnaire.core.Gender;
import com.questionnaire.entity.User;
import com.questionnaire.entity.sessionentity.LoginState;
import com.questionnaire.repository.UserRepository;
import com.questionnaire.services.SessionCache;
import com.questionnaire.services.UserService;
import com.vk.api.sdk.objects.users.UserXtrCounters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor_Strakhov
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionCache sessionCache;

    @Override
    public User saveUser(UserXtrCounters userInfo) {
        User user = userRepository.findByVkontakteId(userInfo.getId());
        if (user == null) {
            user = new User();
            user.setVkontakteId(userInfo.getId());
            if (userInfo.getSex() == 1) {
                user.setGender(Gender.FEMALE);
            } else if (userInfo.getSex() == 2) {
                user.setGender(Gender.MALE);
            }
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public boolean isAuthenticate(HttpServletRequest request) {
        if (sessionCache.get(request, LoginState.class) != null) {
            request.getSession().setAttribute("authenticate", true);
            return true;
        }
        return false;
    }
}
