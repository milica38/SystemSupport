package com.example.iz.service;

import com.example.iz.dto.CpuDTO;
import com.example.iz.dto.GpuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CpuService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

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

    public List<CpuDTO> findCpus(String ssdSize, String ramSize){
        List<CpuDTO> cpus = getAllCPUs();
        List<CpuDTO> result = new ArrayList<CpuDTO>();

        int ssd = Integer.parseInt(ssdSize);
        int ram = Integer.parseInt(ramSize);

        for (CpuDTO cpu: cpus) {
            if(ram < 8 && ssd < 900 && Double.parseDouble(cpu.getClockSpeed()) < 3.3){
                result.add(cpu);
            }
            else if (ram < 8 && ssd > 900 && Double.parseDouble(cpu.getClockSpeed()) > 3.2 && Double.parseDouble(cpu.getClockSpeed()) < 3.5){
                result.add(cpu);
            }
            else if (ram > 8 && ssd < 900 && Double.parseDouble(cpu.getClockSpeed()) > 3.4 && Double.parseDouble(cpu.getClockSpeed()) < 3.7){
                result.add(cpu);
            }
            else if (ram > 8 && ssd > 900 && Double.parseDouble(cpu.getClockSpeed()) > 3.6){
                result.add(cpu);
            }
        }
        return result;
    }
}
