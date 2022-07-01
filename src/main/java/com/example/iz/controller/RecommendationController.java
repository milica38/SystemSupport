package com.example.iz.controller;

import com.example.iz.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService service;

    @GetMapping(value = "/motherboards")
    public ResponseEntity<?> motherboards() {
        List<String> result = service.getMotherboards();
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
