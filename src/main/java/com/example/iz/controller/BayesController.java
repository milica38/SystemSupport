package com.example.iz.controller;

import com.example.iz.dto.BayesDTO;
import com.example.iz.service.BayesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/bayes")
public class BayesController {
    @Autowired
    private BayesService service;

    @CrossOrigin
    @GetMapping
    public List<BayesDTO> findDamage(BayesDTO dto) throws IOException, URISyntaxException {
        return service.findDamage(dto);
    }
}
