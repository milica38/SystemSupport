package com.example.iz.controller;

import com.example.iz.dto.BayesInputDTO;
import com.example.iz.dto.BayesOutputDTO;
import com.example.iz.service.BayesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<BayesOutputDTO> findDamage(@RequestBody BayesInputDTO dto) throws IOException, URISyntaxException {
        return service.findDamage(dto);
    }
}
