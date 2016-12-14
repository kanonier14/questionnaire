package com.questionnaire.services.impl;

import com.questionnaire.entity.User;
import com.questionnaire.entity.sessionentity.LoginState;
import com.questionnaire.repository.UserRepository;
import com.questionnaire.services.SessionCache;
import com.questionnaire.services.UserService;

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
    public User saveUser(Integer vkontakteId) {
        User user = userRepository.findByVkontakteId(vkontakteId);
        if (user == null) {
            user = new User();
            user.setVkontakteId(vkontakteId);
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
