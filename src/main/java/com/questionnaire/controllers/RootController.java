package com.questionnaire.controllers;

import com.questionnaire.core.Constants;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.sessionentity.LoginState;
import com.questionnaire.repository.QuestionnaireRepository;
import com.questionnaire.repository.UserRepository;
import com.questionnaire.services.SessionCache;
import com.questionnaire.services.VkOAuthService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UsersNameCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor_Strakhov
 */
@Controller
public class RootController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionCache sessionCache;


    @RequestMapping("/")
    public String getMainPage(Model model, HttpServletRequest request) {
        List<Questionnaire> questionnaires;
        if (request.getSession().getAttribute("authenticate") != null) {
            LoginState loginState = (LoginState)sessionCache.get(request, LoginState.class);
            questionnaires = questionnaireRepository.findAll().stream()
                                .filter(questionnaire -> !questionnaire.getAuthor().getVkontakteId().equals(loginState.getVkId()))
                                .collect(Collectors.toList());
        } else {
            questionnaires = questionnaireRepository.findAll().stream()
                                .filter(questionnaire -> !questionnaire.isGated())
                                .collect(Collectors.toList());
        }
        model.addAttribute("questionnaires", questionnaires);
        return "index";
    }

    @RequestMapping(path = "/personalaccount")
    public String getPersonalAccountPage(Model model, HttpServletRequest request) {
        LoginState loginState = (LoginState) sessionCache.get(request, LoginState.class);
        List<Questionnaire> questionnaires = questionnaireRepository.findByAuthor(userRepository.findByVkontakteId(loginState.getVkId()));
        model.addAttribute("questionnaires", questionnaires);
        return "personalaccount";
    }



    @RequestMapping("/questionnaries")
    public String getQuestionnairePage() {
        return "questionnaries";
    }
}
