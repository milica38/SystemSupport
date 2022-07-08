package com.example.iz.service;

import com.example.iz.dto.CpuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CpuService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private QueryService queryService;
    @Autowired
    private RecommendationService recommendationService;

    public List<CpuDTO> getAllCPUs() {
        List<String> cpuNames = recommendationService.getComponents("CPU");
        List<String> cpuModel = recommendationService.getComponentDataProperty("CPU", "CPU_model");
        List<String> cpuBrand = recommendationService.getComponentObjectProperty("CPU", "has_brand");
        List<String> cpuCore = recommendationService.getComponentDataProperty("CPU", "CPU_coreNumber");
        List<String> cpuClockSpeed = recommendationService.getComponentDataProperty("CPU", "CPU_frequency");

        List<CpuDTO> result = new ArrayList<>();

        for(int i = 0; i < cpuNames.size(); i++) {
            CpuDTO dto = new CpuDTO();
            dto.setName(cpuNames.get(i));
            dto.setBrand(cpuBrand.get(i));
            dto.setModel(cpuModel.get(i));
            dto.setCore(cpuCore.get(i));
            dto.setClockSpeed(cpuClockSpeed.get(i));
            result.add(dto);
        }
        return result;
    }
}
