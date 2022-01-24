package com.ds.quiz.service.implementation;

import com.ds.quiz.dto.QuizRequest;
import com.ds.quiz.dto.RequestPayload;
import com.ds.quiz.service.QuizService;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private MongoDatabase database;

    @Override
    public List<Document> getQuiz(RequestPayload payload) {
        String collection = getCollection(payload.getType());
        List<Document> quizList = new ArrayList<>();
        List<Document> pipeline =  Arrays.asList(
                new Document("$sample",new Document("size",5)),
                new Document("$project",new Document()
                        .append("question", "$question")
                        .append("options", "$options")
                        .append("_id", 0))
        );
        AggregateIterable<Document> quizDocument = database.getCollection(collection).aggregate(pipeline);
        quizDocument.forEach(quizList::add);
        log.info("Quiz Fetched {}",quizList);
        return quizList;
    }

    @Override
    public ResponseEntity<?> postQuiz(QuizRequest request) {
        String collection = "multiple_choice";
        Document document = new Document();
        Document options = new Document();
        document.append("question", request.getQuestion());
        int index = 0;
        int correctIndex = request.getCorrect();
        for(String option :request.getOptions()){
            if(options.containsKey(option) && index==correctIndex) {
                options.remove(option);
            }
            options.putIfAbsent(option, index==correctIndex);
            index++;
        }
        if (options.size() < 2) {
            log.error("Less Than two Options On Data Insertion");
            return new ResponseEntity<>("Less Than two Options", HttpStatus.BAD_REQUEST);
        } else {
            document.append("options", options);
            database.getCollection(collection).insertOne(document);
            log.info("Quiz Added");
            return new ResponseEntity<>("Quiz Added", HttpStatus.OK);
        }
    }

    private String getCollection(String type) {
        switch (type) {
            case "MULTIPLE_CHOICE" : return "multiple_choice";
            default: return "";
        }
    }
}
