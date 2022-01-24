package com.ds.quiz.dto;

import lombok.Data;
import org.bson.BsonDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class Quiz {
    @Id
    private String id;
    private Integer difficulty;
    private String genre;
    private String question;
    private Map<String, Boolean> answers;

    @Override
    public String toString() {
        return "{" +
                "_id='" + id + '\'' +
                ", difficulty=" + difficulty +
                ", genre='" + genre + '\'' +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
