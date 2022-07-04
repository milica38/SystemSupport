package com.example.iz.controller;

import com.example.iz.dto.*;
import com.example.iz.service.*;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.JFuzzyLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fuzzyLogic")
public class FuzzyController {

    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private QueryService queryService;
    @Autowired
    private GpuService gpuService;
    @Autowired
    private CpuService cpuService;
   
    private final FuzzyService service;

    @Autowired
    public FuzzyController(FuzzyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<FuzzyOutputDTO>> performFuzzyQuery(@RequestBody FuzzyInputDTO dto){
        var response = service.performQuery(dto.getCoreNumber(),dto.getRamSize(),
                                        dto.getStorageSize(),dto.getGpuSize(), dto.getCpuClockSpeed());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/gpu")
    public ResponseEntity<List<GpuDTO>> getAllGraphicsCards(){
        var response = gpuService.getAllGraphicsCards();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cpu")
    public ResponseEntity<List<CpuDTO>> getAllCPUs(){
        var response = cpuService.getAllCPUs();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
