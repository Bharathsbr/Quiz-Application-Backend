package com.sbr.QuizApp_1.service;

import com.sbr.QuizApp_1.model.Question;
import com.sbr.QuizApp_1.model.QuestionWrapper;
import com.sbr.QuizApp_1.model.Quiz;
import com.sbr.QuizApp_1.model.Response;
import com.sbr.QuizApp_1.repo.QuestionDao;
import com.sbr.QuizApp_1.repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int noQ, String title) {
        List<Question> questions=questionDao.findQuestionByRandomByCategory(category,noQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        for(Question question : questions)
        {
            QuestionWrapper qw=new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(Integer id, List<Response> response) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int score=0;
        int i=0;
        for(Response response1:response)
        {
            if(response1.getResponse().equals(questions.get(i).getRightAnswer()))
            {
                score++;
            }
            i++;
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
