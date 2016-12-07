package com.questionnaire.services.impl;

import com.questionnaire.entity.AnswerToQuestion;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.results.Answer;
import com.questionnaire.entity.results.Question;
import com.questionnaire.entity.results.QuestionnaireResults;
import com.questionnaire.repository.AnswerToQuestionRepository;
import com.questionnaire.repository.QuestionnaireRepository;
import com.questionnaire.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Igor on 21.11.2016.
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private AnswerToQuestionRepository answerToQuestionRepository;

    @Override
    public QuestionnaireResults getResults(String id) {
        QuestionnaireResults questionnaireResults = new QuestionnaireResults();
        Questionnaire requestedQuestionnaire = questionnaireRepository.findByIdQuestionnaire(id);

        questionnaireResults.setTitle(requestedQuestionnaire.getTitle());
        List<Question> questions = requestedQuestionnaire.getQuestions().stream()
                .map(question -> {
                    Question questionResult = new Question();
                    questionResult.setTitle(question.getTitle());
                    questionResult.setQuestionType(question.getQuestionType());
                    List<AnswerToQuestion> answersToQuestion = answerToQuestionRepository.findByQuestion(question);
                    List<Answer> answers = question.getAnswers().stream()
                            .map(simpleAnswer -> {
                                Answer answer = new Answer();
                                answer.setTitle(simpleAnswer.getTitle());
                                Integer countAnswers = answersToQuestion.stream()
                                        .filter(answerToQuestion -> answerToQuestion.getAnswers().contains(simpleAnswer))
                                        .collect(Collectors.toList()).size();
                                answer.setNumber(countAnswers);
                                return answer;
                            }).collect(Collectors.toList());
                    questionResult.setAnswers(answers);
                    return questionResult;
                }).collect(Collectors.toList());
        questionnaireResults.setQuestions(questions);
        questionnaireResults.setId(id);
        return questionnaireResults;
    }
}
