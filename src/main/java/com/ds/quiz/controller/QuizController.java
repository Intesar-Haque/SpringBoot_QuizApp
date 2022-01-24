package com.ds.quiz.controller;

import com.ds.quiz.dto.QuizRequest;
import com.ds.quiz.dto.RequestPayload;
import com.ds.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuizController {

    private QuizService quizService;

    @PostMapping("/get-quiz")
    List<Document> getQuiz(){
        RequestPayload payload = new RequestPayload();
        payload.setType("MULTIPLE_CHOICE");
        return quizService.getQuiz(payload);
    }
    @PostMapping("/post-quiz")
    ResponseEntity<?> postQuiz(@RequestBody QuizRequest quiz){
        return quizService.postQuiz(quiz);
    }


}
