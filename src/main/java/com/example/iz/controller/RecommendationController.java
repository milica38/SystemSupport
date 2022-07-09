package com.example.iz.controller;

import com.example.iz.dto.*;
import com.example.iz.service.*;
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
    private SpeakersService speakersService;
    @Autowired
    private MotherboardService motherboardService;
    @Autowired
    private GpuService gpuService;
    @Autowired
    private CpuService cpuService;


    @CrossOrigin
    @GetMapping("/{componentName}")
    public ResponseEntity<List<String>> getComponents(@PathVariable String componentName){
        var response = recommendationService.getComponents(componentName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/speakers/search")
    public ResponseEntity<List<SpeakersDTO>> findSpeakers(@RequestParam String searchValue){
        var response = speakersService.findSpeakers(searchValue);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/speakers")
    public ResponseEntity<List<SpeakersDTO>> getAllSpeakers(){
        var response = speakersService.getAllSpeakers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/motherboards")
    public ResponseEntity<List<MotherboardDTO>> getAllMotherboards(){
        var response = motherboardService.getAllMotherboards();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/gpu/search")
    public ResponseEntity<List<GpuDTO>> findGPUs(@RequestBody RecommendationForGpuDTO dto){
        var response = gpuService.findGpus(dto.getMotherboardUSBslots(), dto.getCpuFrequency());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/cpu/search")
    public ResponseEntity<List<CpuDTO>> findCPUs(@RequestBody RecommendationForCpuDTO dto){
        var response = cpuService.findCpus(dto.getSsdMemorySize(), dto.getRamSize());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
