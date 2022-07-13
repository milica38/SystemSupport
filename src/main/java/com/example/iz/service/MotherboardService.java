package com.example.iz.service;

import com.example.iz.dto.MotherboardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotherboardService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private RecommendationService recommendationService;

    public List<MotherboardDTO> getAllMotherboards() {
        List<String> motherboardNames = recommendationService.getComponents("Motherboard");
        List<String> motherboardFormFactors = recommendationService.getComponentDataProperty("Motherboard", "Motherboard_form_factor");
        List<String> motherboardBrands = recommendationService.getComponentObjectProperty("Motherboard", "has_brand");
        List<String> motherboardUSBSlots = recommendationService.getComponentDataProperty("Motherboard", "Motherboard_USB_slots");
        List<String> motherboardChipsets = recommendationService.getComponentDataProperty("Motherboard", "Motherboard_chipset");
        //List<String> motherboardSataConnections = recommendationService.getComponentDataProperty("Motherboard", "SATA_connections");
        List<String> motherboardProcessorSockets = recommendationService.getComponentDataProperty("Motherboard", "processor_socket");

        List<MotherboardDTO> result = new ArrayList<>();

        for(int i = 0; i < motherboardNames.size(); i++) {
            MotherboardDTO dto = new MotherboardDTO();
            dto.setName(motherboardNames.get(i));
            dto.setFormFactor(motherboardFormFactors.get(i));
            dto.setBrand(motherboardBrands.get(i));
            dto.setUsbSlots(motherboardUSBSlots.get(i));
            dto.setChipset(motherboardChipsets.get(i));
            dto.setSocket(motherboardProcessorSockets.get(i));
            result.add(dto);
        }
        return result;
    }
}
