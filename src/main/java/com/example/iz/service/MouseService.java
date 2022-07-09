package com.example.iz.service;

import com.example.iz.dto.MouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MouseService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private RecommendationService recommendationService;

    public List<MouseDTO> getAllMouses() {
        List<String> mouseNames = recommendationService.getComponents("Mouse");
        List<String> mouseModel = recommendationService.getComponentDataProperty("Mouse", "has_a_model");
        List<String> mouseBrand = recommendationService.getComponentObjectProperty("Mouse", "has_brand");
        List<String> mouseType = recommendationService.getComponentDataProperty("Mouse", "Mouse_type");
        List<String> mouseDpi = recommendationService.getComponentDataProperty("Mouse", "Mouse_DPI");


        List<MouseDTO> result = new ArrayList<>();

        for(int i = 0; i < mouseNames.size(); i++) {
            MouseDTO dto = new MouseDTO();
            dto.setName(mouseNames.get(i));
            dto.setBrand(mouseBrand.get(i));
            dto.setModel(mouseModel.get(i));
            dto.setType(mouseType.get(i));
            dto.setDpi(mouseDpi.get(i));
            result.add(dto);
        }
        return result;
    }
}
