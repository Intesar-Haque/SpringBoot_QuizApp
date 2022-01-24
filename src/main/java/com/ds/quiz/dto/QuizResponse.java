package com.ds.quiz.dto;

import lombok.Data;

import java.util.Map;

@Data
public class QuizResponse {
    String question;
    Map<String, Boolean> options;

    public QuizResponse() {
    }

    public QuizResponse(String question, Map<String, Boolean> answers) {
        this.question = question;
        this.options = answers;
    }
}
