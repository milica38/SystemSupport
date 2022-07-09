package com.example.iz.service;

import com.example.iz.dto.HeadphonesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeadphonesService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private RecommendationService recommendationService;

    public List<HeadphonesDTO> getAllHeadphones() {
        List<String> hpNames = recommendationService.getComponents("Headphones");
        List<String> hpModel = recommendationService.getComponentDataProperty("Headphones", "has_a_model");
        List<String> hpBrand = recommendationService.getComponentObjectProperty("Headphones", "has_brand");
        List<String> hpSensitivities = recommendationService.getComponentDataProperty("Headphones", "sensitivities");


        List<HeadphonesDTO> result = new ArrayList<>();

        for(int i = 0; i < hpNames.size(); i++) {
            HeadphonesDTO dto = new HeadphonesDTO();
            dto.setName(hpNames.get(i));
            dto.setBrand(hpBrand.get(i));
            dto.setModel(hpModel.get(i));
            dto.setSensitivities(hpSensitivities.get(i));
            result.add(dto);
        }
        return result;
    }
}
