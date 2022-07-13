package com.example.iz.service;

import com.example.iz.dto.GpuDTO;
import com.example.iz.dto.PowerSupplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PowerSupplyService {
    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private RecommendationService recommendationService;

    public List<PowerSupplyDTO> getAllPSs() {
        List<String> psNames = recommendationService.getComponents("Power_supply");
        List<String>  psVoltage = recommendationService.getComponentDataProperty("Power_supply", "Power_supply_voltage");
        List<String> psBrand = recommendationService.getComponentObjectProperty("Power_supply", "has_brand");
        List<String> psModel = recommendationService.getComponentDataProperty("Power_supply", "has_a_model");

        List<PowerSupplyDTO> result = new ArrayList<>();

        for(int i = 0; i < psNames.size(); i++) {
            PowerSupplyDTO dto = new PowerSupplyDTO();
            dto.setName(psNames.get(i));
            dto.setBrand(psBrand.get(i));
            dto.setVoltage(psVoltage.get(i));
            dto.setModel(psModel.get(i));
            result.add(dto);
        }
        return result;
    }

    public List<PowerSupplyDTO> findPSs(String sockets, String usbSlots){
        List<PowerSupplyDTO> pss = getAllPSs();
        List<PowerSupplyDTO> result = new ArrayList<PowerSupplyDTO>();

        int slots = Integer.parseInt(usbSlots);

        for (PowerSupplyDTO ps: pss) {
            if(slots < 10 && sockets.equals("LGA 1200") && Integer.parseInt(ps.getVoltage()) <= 650){
                result.add(ps);
            }
            else if((slots < 10 && sockets.equals("LGA 1700") && Integer.parseInt(ps.getVoltage()) <= 650 && Integer.parseInt(ps.getVoltage()) > 560)){
                result.add(ps);
            }

            if(slots > 9 && sockets.equals("LGA 1200") && Integer.parseInt(ps.getVoltage()) == 700){
                result.add(ps);
            }
            else if((slots > 9 && sockets.equals("LGA 1700") && Integer.parseInt(ps.getVoltage()) > 700)){
                result.add(ps);
            }
        }
        return result;
    }
}
