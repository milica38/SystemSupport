package com.example.iz.service;

import com.example.iz.dto.RamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RamService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private QueryService queryService;
    @Autowired
    private RecommendationService recommendationService;

    public List<RamDTO> getAllRams() {
        List<String> ramNames = recommendationService.getComponents("RAM");
        List<String> ramType = recommendationService.getComponentDataProperty("RAM", "DDrate_type");
        List<String> ramBrand = recommendationService.getComponentObjectProperty("RAM", "has_brand");
        List<String> ramSize = recommendationService.getComponentDataProperty("RAM", "RAM_size");

        List<RamDTO> result = new ArrayList<>();

        for(int i = 0; i < ramNames.size(); i++) {
            RamDTO dto = new RamDTO();
            dto.setName(ramNames.get(i));
            dto.setBrand(ramBrand.get(i));
            dto.setType(ramType.get(i));
            dto.setRamSize(ramSize.get(i));
            result.add(dto);
        }
        return result;
    }
}
