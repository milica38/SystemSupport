package com.example.iz.controller;

import com.example.iz.dto.FuzzyInputDTO;
import com.example.iz.dto.FuzzyOutputDTO;
import com.example.iz.service.FuzzyService;
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

    private final FuzzyService service;

    @Autowired
    public FuzzyController(FuzzyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<FuzzyOutputDTO>> performFuzzyQuery(@RequestBody FuzzyInputDTO fuzzyQueryDTO){
        var response = service.performQuery(fuzzyQueryDTO.getCoreNumber(),fuzzyQueryDTO.getRamSize(),fuzzyQueryDTO.getStorageSize(),fuzzyQueryDTO.getGpuSize());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
