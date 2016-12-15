package com.questionnaire.services.impl;

import com.questionnaire.core.Gender;
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
    public QuestionnaireResults getResults(String id, Gender gender) {
        QuestionnaireResults questionnaireResults = new QuestionnaireResults();
        Questionnaire requestedQuestionnaire = questionnaireRepository.findByIdQuestionnaire(id);

        List<Question> questions = getQuestions(requestedQuestionnaire, gender);

        questionnaireResults.setQuestions(questions);
        questionnaireResults.setTitle(requestedQuestionnaire.getTitle());
        questionnaireResults.setId(id);
        if (requestedQuestionnaire.getState() == null) {
            questionnaireResults.setState("NOT_STARTED");
        } else {
            questionnaireResults.setState(requestedQuestionnaire.getState().name());
        };
        return questionnaireResults;
    }

    private List<Question> getQuestions(Questionnaire requestedQuestionnaire, Gender gender) {
        return requestedQuestionnaire.getQuestions().stream()
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
                                        .filter(answerToQuestion -> {
                                            if (gender == null) {
                                                return answerToQuestion.getAnswers().contains(simpleAnswer);
                                            } else {
                                                return answerToQuestion.getAnswers().contains(simpleAnswer) && answerToQuestion.getAnsweredUser() != null
                                                       && answerToQuestion.getAnsweredUser().getGender() != null && answerToQuestion.getAnsweredUser().getGender().equals(gender);
                                            }
                                        })
                                        .collect(Collectors.toList()).size();
                                answer.setNumber(countAnswers);
                                return answer;
                            }).collect(Collectors.toList());
                    questionResult.setAnswers(answers);
                    return questionResult;
                }).collect(Collectors.toList());
    }
}
