package com.questionnaire.controllers;

import com.questionnaire.core.Gender;
import com.questionnaire.core.QuestionType;
import com.questionnaire.core.State;
import com.questionnaire.core.Topic;
import com.questionnaire.entity.AnswerToQuestion;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.SimpleAnswer;
import com.questionnaire.entity.SimpleQuestion;
import com.questionnaire.entity.results.QuestionnaireResults;
import com.questionnaire.entity.sessionentity.LoginState;
import com.questionnaire.repository.AnswerToQuestionRepository;
import com.questionnaire.repository.QuestionnaireRepository;
import com.questionnaire.repository.SimpleAnswerRepository;
import com.questionnaire.repository.SimpleQuestionRepository;
import com.questionnaire.repository.UserRepository;
import com.questionnaire.services.QuestionnaireService;
import com.questionnaire.services.SessionCache;
import com.questionnaire.services.SimpleQuestionService;
import com.questionnaire.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
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
    @Autowired
    private SessionCache sessionCache;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String getCreateQUestionnairePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (userService.isAuthenticate(request)) {
            return "createquestionnaire";
        } else {
            response.sendRedirect("/user/authorize");
            return "index";
        }
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public void saveQuestionnaire(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (userService.isAuthenticate(request)) {
            List<SimpleQuestion> questions = simpleQuestionService.createQuestionsFromRequestParameters(request.getParameterMap());
            String questionnaireTitle = request.getParameter("title");
            boolean gated = request.getParameter("gated") != null;
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setGated(gated);
            questionnaire.setTitle(questionnaireTitle);
            questionnaire.setQuestions(questions);
            LoginState loginState = (LoginState) sessionCache.get(request, LoginState.class);

            questionnaire.setAuthor(userRepository.findByVkontakteId(loginState.getVkId()));
            questionnaire.setCreationDate(new Date().getTime());
            questionnaire.setTopic(Topic.valueOf(request.getParameter("topic")));

            questionnaireRepository.save(questionnaire);
            response.sendRedirect("/questionnaire/results?id=" + questionnaire.getIdQuestionnaire());
        } else {
            response.sendRedirect("/user/authorize");
        }
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
                    SimpleQuestion answeredQuestion = questionRepository.findByIdQuestion(parameter.getKey());
                    answerToQuestion.setQuestion(answeredQuestion);

                    String[] answers = parameter.getValue();

                    if (!answeredQuestion.getQuestionType().equals(QuestionType.OPEN)) {
                        List<SimpleAnswer> givenAnswers = Arrays.asList(answers).stream()
                                .map(answer -> simpleAnswerRepository.findByIdAnswer(answer))
                                .collect(Collectors.toList());
                        answerToQuestion.setAnswers(givenAnswers);
                    } else {
                        answerToQuestion.setOpenAnswer(answers[0]);
                    }
                    if (sessionCache.get(request, LoginState.class) != null) {
                        LoginState loginState = (LoginState) sessionCache.get(request, LoginState.class);
                        answerToQuestion.setAnsweredUser(userRepository.findByVkontakteId(loginState.getVkId()));
                    }
                    answerToQuestionRepository.save(answerToQuestion);
                });
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/results", method = RequestMethod.GET)
    public String getQuestionnaireResults(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (userService.isAuthenticate(request)) {
            String idQuestionnaire = request.getParameter("id");
            String gender = request.getParameter("gender");
            QuestionnaireResults questionnaireResults = questionnaireService.getResults(idQuestionnaire, gender == null ? null : Gender.valueOf(gender));
            model.addAttribute("questionnaire", questionnaireResults);
            return "resultsquestionnaire";
        } else {
            response.sendRedirect("/user/authorize");
            return "index";
        }
    }

    @RequestMapping(path = "/setstate", method = RequestMethod.GET)
    public void removeQuestionnaire(Model model, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idQuestionnaire = request.getParameter("id");
        String newState = request.getParameter("state");
        if ("remove".equals(newState)) {
            questionnaireRepository.delete(idQuestionnaire);
        } else if ("start".equals(newState)) {
            Questionnaire questionnaire = questionnaireRepository.findByIdQuestionnaire(idQuestionnaire);
            questionnaire.setState(State.STARTED);
            questionnaireRepository.save(questionnaire);
        } else if ("stop".equals(newState)) {
            Questionnaire questionnaire = questionnaireRepository.findByIdQuestionnaire(idQuestionnaire);
            questionnaire.setState(State.STOPED);
            questionnaireRepository.save(questionnaire);
        }
        response.sendRedirect("/personalaccount");
    }
}
