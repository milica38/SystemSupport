package com.example.iz.controller;


import com.example.iz.dto.SimilarityDTO;
import com.example.iz.model.CaseDescription;
import com.example.iz.service.*;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/similarity")
public class SimilarityController {

    @Autowired
    private SimilarityService similarityService;

    @GetMapping("/{cores}/{cpuFrequency}/{ramSize}/{storageType}/{storageSize}/{formFactor}/{gpuSize}/{price}")
    public ResponseEntity<List<SimilarityDTO>> getSimilarity(@PathVariable int cores,
                                                             @PathVariable double cpuFrequency,
                                                             @PathVariable int ramSize,
                                                             @PathVariable String storageType,
                                                             @PathVariable int storageSize,
                                                             @PathVariable String formFactor,
                                                             @PathVariable int gpuSize,
                                                             @PathVariable int price) throws Exception {

        return new ResponseEntity<>( this.similarityService.similarity(cores, cpuFrequency, ramSize,storageType, storageSize, formFactor, gpuSize, price), HttpStatus.OK);
    }







}
