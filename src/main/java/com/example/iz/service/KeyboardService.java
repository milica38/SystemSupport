package com.example.iz.service;

import com.example.iz.dto.HeadphonesDTO;
import com.example.iz.dto.KeyboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private RecommendationService recommendationService;

    public List<KeyboardDTO> getAllKeyboards() {
        List<String> kbNames = recommendationService.getComponents("Keyboard");
        List<String> kbModel = recommendationService.getComponentDataProperty("Keyboard", "has_a_model");
        List<String> kbBrand = recommendationService.getComponentObjectProperty("Keyboard", "has_brand");
        List<String> kbTypes = recommendationService.getComponentDataProperty("Keyboard", "Keyboard_type");


        List<KeyboardDTO> result = new ArrayList<>();

        for(int i = 0; i < kbNames.size(); i++) {
            KeyboardDTO dto = new KeyboardDTO();
            dto.setName(kbNames.get(i));
            dto.setBrand(kbBrand.get(i));
            dto.setModel(kbModel.get(i));
            dto.setType(kbTypes.get(i));
            result.add(dto);
        }
        return result;
    }
}
