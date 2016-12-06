package com.questionnaire.services.impl;

import com.questionnaire.entity.User;
import com.questionnaire.repository.UserRepository;
import com.questionnaire.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Igor_Strakhov
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
}
