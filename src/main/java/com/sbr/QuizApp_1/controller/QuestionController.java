package com.sbr.QuizApp_1.controller;

import com.sbr.QuizApp_1.model.Question;
import com.sbr.QuizApp_1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAll()
    {
        return questionService.getAll();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category)
    {
        return questionService.getByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return  questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@RequestBody Question question)
    {
        return questionService.update(id,question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id)
    {
        return questionService.delete(id);
    }
}
