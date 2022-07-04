package com.example.iz.service;

import com.example.iz.dto.GpuDTO;
import com.example.iz.dto.SsdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SsdService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private QueryService queryService;
    @Autowired
    private RecommendationService recommendationService;

    public List<SsdDTO> getAllSSDs() {
        List<String> ssdNames = recommendationService.getComponents("SSD");
        List<String> ssdSize = recommendationService.getComponentDataProperty("SSD", "SSD_memory_size");
        List<String> ssdBrand = recommendationService.getComponentObjectProperty("SSD", "has_brand");
        List<String> ssdModel = recommendationService.getComponentDataProperty("SSD", "has_a_model");

        List<SsdDTO> result = new ArrayList<>();

        for(int i = 0; i < ssdNames.size(); i++) {
            SsdDTO dto = new SsdDTO();
            dto.setName(ssdNames.get(i));
            dto.setBrand(ssdBrand.get(i));
            dto.setStorageSize(ssdSize.get(i));
            dto.setModel(ssdModel.get(i));
            result.add(dto);
        }
        return result;
    }
}
