package com.ds.quiz.service;

import com.ds.quiz.dto.QuizRequest;
import com.ds.quiz.dto.RequestPayload;
import org.bson.Document;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    List<Document> getQuiz(RequestPayload payload);
    ResponseEntity<?> postQuiz(QuizRequest request);
}
