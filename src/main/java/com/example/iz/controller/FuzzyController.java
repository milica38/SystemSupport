package com.example.iz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/fuzzyLogic")
public class FuzzyController {

    @GetMapping
    public ResponseEntity<String> getCurrentUser() {

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
