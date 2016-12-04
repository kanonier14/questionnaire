package com.questionnaire.controllers;

import com.questionnaire.core.Constants;
import com.questionnaire.repository.QuestionnaireRepository;
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

/**
 * @author Igor_Strakhov
 */
@Controller
public class RootController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @RequestMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("questionnaires", questionnaireRepository.findAll());
        return "index";
    }

    @RequestMapping(path = "/personalaccount")
    public String getPersonalAccountPage() {
        return "personalaccount";
    }

    @RequestMapping(path = "/authorization/user")
    public String getAuthentication(HttpServletRequest request, HttpServletResponse response) throws ClientException, ApiException {
        String code = request.getParameter("code");
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(Integer.valueOf(Constants.ID_APPLICATION), Constants.KEY_APPLICATION, "http://localhost:8080/authorization/user", code)
                .execute();
        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        UserXtrCounters user = vk.users().get(actor).nameCase(UsersNameCase.NOMINATIVE).execute().get(0);
        return "index";
    }

    @RequestMapping(path = "/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("https://oauth.vk.com/authorize?client_id=5761453&display=page&redirect_uri=http://localhost:8080/authorization/user&scope=friends&response_type=code&v=5.60");
    }
}
