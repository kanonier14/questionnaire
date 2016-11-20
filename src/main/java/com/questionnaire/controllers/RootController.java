package com.questionnaire.controllers;

import com.questionnaire.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
