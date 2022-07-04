package com.example.iz.service;

import com.example.iz.dto.GpuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GpuService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private QueryService queryService;
    @Autowired
    private RecommendationService recommendationService;

    public List<GpuDTO> getAllGraphicsCards() {
        List<String> gpuNames = recommendationService.getComponents("Graphics_card");
        List<String> gpuMemory = recommendationService.getComponentDataProperty("Graphics_card", "GPU_maximum_memory");
        List<String> gpuBrand = recommendationService.getComponentObjectProperty("Graphics_card", "has_brand");
        List<String> gpuModel = recommendationService.getComponentDataProperty("Graphics_card", "has_a_model");

        List<GpuDTO> result = new ArrayList<>();

        for(int i = 0; i < gpuNames.size(); i++) {
            GpuDTO dto = new GpuDTO();
            dto.setName(gpuNames.get(i));
            dto.setBrand(gpuBrand.get(i));
            dto.setMaxMemory(gpuMemory.get(i));
            dto.setModel(gpuModel.get(i));
            result.add(dto);
        }
        return result;
    }

}
