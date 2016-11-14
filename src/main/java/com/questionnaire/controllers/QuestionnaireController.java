package com.questionnaire.controllers;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Igor on 12.11.2016.
 */
@Controller
@RequestMapping(path = "/questionnaire")
public class QuestionnaireController {

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String getCreateQUestionnairePage() {
        return "createquestionnaire";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public void saveQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }
}
