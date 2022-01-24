package com.ds.quiz.dto;

import lombok.Data;

@Data
public class RequestPayload {
    String type;
    Integer difficulty;
    String genre;
}
