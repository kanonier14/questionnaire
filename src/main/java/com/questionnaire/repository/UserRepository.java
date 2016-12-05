package com.questionnaire.repository;

import com.questionnaire.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Igor on 06.12.2016.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByVkontakteId(Integer vkId);
}
