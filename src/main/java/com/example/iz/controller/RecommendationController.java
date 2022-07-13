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
    @Autowired
    private  HeadphonesService hpService;
    @Autowired
    private  KeyboardService kbService;
    @Autowired
    private CoolerService coolerService;
    @Autowired MouseService mouseService;
    @Autowired PowerSupplyService psService;

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

    @CrossOrigin
    @GetMapping("/headphones")
    public ResponseEntity<List<HeadphonesDTO>> getAllHeadphones(){
        var response = hpService.getAllHeadphones();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/keyboards")
    public ResponseEntity<List<KeyboardDTO>> getAllKeyboards(){
        var response = kbService.getAllKeyboards();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/mouses")
    public ResponseEntity<List<MouseDTO>> getAllMouses(){
        var response = mouseService.getAllMouses();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/powerSupply")
    public ResponseEntity<List<PowerSupplyDTO>> getAllPSs(){
        var response = psService.getAllPSs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/mouse/search")
    public ResponseEntity<List<MouseDTO>> findMouses(@RequestBody RecommendationForMouseDTO dto){
        var response = mouseService.findMouses(dto.getHpSensitivities(), dto.getKeyboardType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/coolers")
    public ResponseEntity<List<CoolerDTO>> getAllCoolers(){
        var response = coolerService.getAllCoolers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/cooler/search")
    public ResponseEntity<List<CoolerDTO>> findMouses(@RequestBody RecommendationForCoolerDTO dto){
        var response = coolerService.findCoolers(dto.getFrequency());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/ps/search")
    public ResponseEntity<List<PowerSupplyDTO>> findPSs(@RequestBody RecommendationForPpowerSupply dto){
        var response = psService.findPSs(dto.getSocket(), dto.getSlots());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
