package com.questionnaire.services.impl;

import com.questionnaire.entity.SimpleAnswer;
import com.questionnaire.repository.SimpleAnswerRepository;
import com.questionnaire.services.SimpleAnswerService;
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
public class SimpleAnswerServiceImpl implements SimpleAnswerService{

    @Autowired
    private SimpleAnswerRepository answerRepository;

    private final String ANSWER_TITLE_REGEXP = "question_([0-9]+)_answer_([0-9]+)";
    private final Pattern answerPattern = Pattern.compile(ANSWER_TITLE_REGEXP);

    @Override
    public List<SimpleAnswer> createAnswersFromRequestParameters(Map<String, String[]> parameters, String question) {
        List<SimpleAnswer> answers = parameters.entrySet().stream()
                .filter(parameter -> answerPattern.matcher(parameter.getKey()).matches()
                                     && parameter.getKey().contains(question))
                .map(parameter -> {
                    SimpleAnswer answer = new SimpleAnswer();
                    answer.setTitle(parameter.getValue()[0]);
                    answerRepository.save(answer);
                    return answer;
                }).collect(Collectors.toList());
        return answers;
    }
}
