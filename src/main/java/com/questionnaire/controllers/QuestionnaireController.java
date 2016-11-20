package com.questionnaire.controllers;

import com.questionnaire.entity.AnswerToQuestion;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.SimpleAnswer;
import com.questionnaire.entity.SimpleQuestion;
import com.questionnaire.entity.results.QuestionnaireResults;
import com.questionnaire.repository.AnswerToQuestionRepository;
import com.questionnaire.repository.QuestionnaireRepository;
import com.questionnaire.repository.SimpleAnswerRepository;
import com.questionnaire.repository.SimpleQuestionRepository;
import com.questionnaire.services.QuestionnaireService;
import com.questionnaire.services.SimpleQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Igor on 12.11.2016.
 */
@Controller
@RequestMapping(path = "/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private SimpleQuestionRepository questionRepository;
    @Autowired
    private AnswerToQuestionRepository answerToQuestionRepository;
    @Autowired
    private SimpleAnswerRepository simpleAnswerRepository;
    @Autowired
    private SimpleQuestionService simpleQuestionService;
    @Autowired
    private QuestionnaireService questionnaireService;


    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String getCreateQUestionnairePage() {
        return "createquestionnaire";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public void saveQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<SimpleQuestion> questions = simpleQuestionService.createQuestionsFromRequestParameters(request.getParameterMap());
        String questionnaireTitle = request.getParameter("title");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setTitle(questionnaireTitle);
        questionnaire.setQuestions(questions);
        questionnaireRepository.save(questionnaire);
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/answer", params = "id", method = RequestMethod.GET)
    public String getQuestionnaireForAnswer(Model model, HttpServletRequest request, HttpServletResponse response) {
        String idQuestionnaire = request.getParameter("id");
        Questionnaire questionnaire = questionnaireRepository.findByIdQuestionnaire(idQuestionnaire);
        model.addAttribute("questionnaire", questionnaire);
        return "answerquestionnaire";
    }

    @RequestMapping(path = "/answer", method = RequestMethod.POST)
    private void answerForQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, String[]> parameters = request.getParameterMap();
        parameters.entrySet().stream()
                .filter(parameter -> questionRepository.findByIdQuestion(parameter.getKey()) != null)
                .forEach(parameter -> {
                    AnswerToQuestion answerToQuestion = new AnswerToQuestion();
                    answerToQuestion.setQuestion(questionRepository.findByIdQuestion(parameter.getKey()));
                    String[] answers = parameter.getValue();
                    List<SimpleAnswer> givenAnswers = Arrays.asList(answers).stream()
                            .map(answer -> simpleAnswerRepository.findByIdAnswer(answer))
                            .collect(Collectors.toList());
                    answerToQuestion.setAnswers(givenAnswers);
                    answerToQuestionRepository.save(answerToQuestion);
                });
        response.sendRedirect("/");
    }

    @RequestMapping(path = "results", params = "id", method = RequestMethod.GET)
    public String getQuestionnaireResults(Model model, HttpServletRequest request, HttpServletResponse response) {
        String idQuestionnaire = request.getParameter("id");
        QuestionnaireResults questionnaireResults = questionnaireService.getResults(idQuestionnaire);
        model.addAttribute("questionnaire", questionnaireResults);
        return "resultsquestionnaire";
    }
}
