package com.sbr.QuizApp_1.controller;

import com.sbr.QuizApp_1.model.QuestionWrapper;
import com.sbr.QuizApp_1.model.Response;
import com.sbr.QuizApp_1.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;


    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noQ,String title)
    {
        return quizService.createQuiz(category,noQ,title);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id)
    {
        return quizService.getQuiz(id);
    }

    @PostMapping("getScore/{id}")
    public ResponseEntity<Integer> getScore(@PathVariable Integer id, @RequestBody List<Response> response)
    {
        return quizService.getScore(id,response);
    }

}
