package com.example.iz.controller;

import com.example.iz.dto.SpeakersDTO;
import com.example.iz.service.QueryService;
import com.example.iz.service.RecommendationService;
import com.example.iz.service.SpeakersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private QueryService queryService;
    @Autowired
    private SpeakersService speakersService;


    @CrossOrigin
    @GetMapping("/{componentName}")
    public ResponseEntity<List<String>> getComponents(@PathVariable String componentName){
        var response = recommendationService.getComponents(componentName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/speakers/{searchValue}")
    public ResponseEntity<List<SpeakersDTO>> findSpeakers(@RequestBody String searchValue){
        var response = speakersService.findSpeakers(searchValue);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/speakers")
    public ResponseEntity<List<SpeakersDTO>> getAllSpeakers(){
        var response = speakersService.getAllSpeakers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
