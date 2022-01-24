package com.ds.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizRequest {
    String question;
    Integer correct;
    List<String> options;
}
