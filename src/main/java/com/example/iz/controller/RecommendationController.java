package com.example.iz.controller;

import com.example.iz.service.QueryService;
import com.example.iz.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final QueryService queryService;

    @Autowired
    public  RecommendationController(RecommendationService rs, QueryService qs){
        this.recommendationService = rs;
        this.queryService = qs;
    }

    @GetMapping()
    public ResponseEntity<List<String>> performQuery(@RequestBody String query){
        var response = queryService.executeQuery(query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{componentName}")
    public ResponseEntity<List<String>> getComponents(@PathVariable String componentName){
        var response = recommendationService.getComponents(componentName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
