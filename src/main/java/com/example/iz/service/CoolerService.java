package com.example.iz.service;

import com.example.iz.dto.CoolerDTO;
import com.example.iz.dto.CpuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoolerService {
    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private RecommendationService recommendationService;

    public List<CoolerDTO> getAllCoolers() {
        List<String> cNames = recommendationService.getComponents("Cooling_system");
        List<String> cModel = recommendationService.getComponentDataProperty("Cooling_system", "has_a_model");
        List<String> cBrand = recommendationService.getComponentObjectProperty("Cooling_system", "has_brand");
        List<String> cPrice = recommendationService.getComponentDataProperty("Cooling_system", "price");
        List<String> cType = recommendationService.getComponentDataProperty("Cooling_system", "CS_type");
        List<String> cRotation = recommendationService.getComponentDataProperty("Cooling_system", "CS_max_rotation");

        List<CoolerDTO> result = new ArrayList<>();

        for (int i = 0; i < cNames.size(); i++) {
            CoolerDTO dto = new CoolerDTO();
            dto.setName(cNames.get(i));
            dto.setBrand(cBrand.get(i));
            dto.setModel(cModel.get(i));
            dto.setPrice(cPrice.get(i));
            dto.setType(cType.get(i));
            dto.setRotation(cRotation.get(i));
            result.add(dto);
        }
        return result;
    }

    public List<CoolerDTO> findCoolers(String frequency){
        List<CoolerDTO> coolers = getAllCoolers();
        List<CoolerDTO> result = new ArrayList<CoolerDTO>();

        double freq = Double.parseDouble(frequency);

        for (CoolerDTO cooler: coolers) {
            if(freq > 3.6 && cooler.getType().equals("Water_cooling_system") && Double.parseDouble(cooler.getRotation()) > 2100){
                result.add(cooler);
            }
            else if(freq > 3.4 && freq < 3.7 && cooler.getType().equals("Water_cooling_system") && Double.parseDouble(cooler.getRotation()) < 2100 && Double.parseDouble(cooler.getRotation()) > 1900){
                result.add(cooler);
            }
            else if(freq > 2.5 && freq < 3.5 && cooler.getType().equals("Water_cooling_system") && Double.parseDouble(cooler.getRotation()) < 1900){
                result.add(cooler);
            }
            else if(freq < 3.3 && cooler.getType().equals("Air_cooling_system") && Double.parseDouble(cooler.getRotation()) < 1900){
                result.add(cooler);
            }
            else if(freq > 3.2 && freq < 3.6 && cooler.getType().equals("Air_cooling_system") && Double.parseDouble(cooler.getRotation()) < 2100 && Double.parseDouble(cooler.getRotation()) > 1900){
                result.add(cooler);
            }
            else if(freq > 3.5 && freq < 3.9 && cooler.getType().equals("Air_cooling_system") && Double.parseDouble(cooler.getRotation()) > 2100){
                result.add(cooler);
            }
        }
        return result;
    }
}
