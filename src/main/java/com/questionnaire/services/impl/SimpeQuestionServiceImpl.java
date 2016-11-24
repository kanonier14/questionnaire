package com.questionnaire.services.impl;

import com.questionnaire.core.QuestionType;
import com.questionnaire.entity.SimpleAnswer;
import com.questionnaire.entity.SimpleQuestion;
import com.questionnaire.repository.SimpleQuestionRepository;
import com.questionnaire.services.SimpleAnswerService;
import com.questionnaire.services.SimpleQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Igor on 20.11.2016.
 */
@Service
public class SimpeQuestionServiceImpl implements SimpleQuestionService {

    private static final String TITLE_POSTFIX = "_title";
    private final String QUESTION_TITLE_REGEXP = "question_([0-9]+)_title";
    private final String TYPE_POSTFIX = "_type";
    private final Pattern questionPattern = Pattern.compile(QUESTION_TITLE_REGEXP);

    @Autowired
    private SimpleQuestionRepository questionRepository;
    @Autowired
    private SimpleAnswerService simpleAnswerService;

    @Override
    public List<SimpleQuestion> createQuestionsFromRequestParameters(Map<String, String[]> parameters) {
        List<SimpleQuestion> questions = parameters.entrySet().stream()
                .filter(parameter -> questionPattern.matcher(parameter.getKey()).matches())
                .map(parameter -> {
                    SimpleQuestion question = new SimpleQuestion();
                    question.setTitle(parameter.getValue()[0]);
                    question.setQuestionType(QuestionType.valueOf(parameters.get(parameter.getKey().replaceAll(TITLE_POSTFIX, TYPE_POSTFIX))[0]));
                    List<SimpleAnswer> answers = simpleAnswerService
                            .createAnswersFromRequestParameters(parameters, parameter.getKey().replaceAll(TITLE_POSTFIX, ""));
                    question.setAnswers(answers);
                    questionRepository.save(question);
                    return question;
                }).collect(Collectors.toList());
        return questions;
    }
}
