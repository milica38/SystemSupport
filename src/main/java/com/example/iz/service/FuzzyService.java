package com.example.iz.service;

import com.example.iz.dto.FuzzyOutputDTO;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import net.sourceforge.jFuzzyLogic.JFuzzyLogic;
import net.sourceforge.jFuzzyLogic.FIS;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FuzzyService {
    private String path;

    public FuzzyService() throws URISyntaxException {
        this.path = TypeReference.class.getResource("/data/template.fcl").toURI().getPath();
    }

    public List<FuzzyOutputDTO> performQuery(int coreNumber, int ramSize, int storageSize, int gpuSize, double cpuClockSpeed) {

        String args[] = { "-noCharts", "-e", path, ""+coreNumber, ""+cpuClockSpeed, ""+gpuSize, ""+ramSize, ""+storageSize};
        JFuzzyLogic jFuzzyLogic = new JFuzzyLogic(args);
        jFuzzyLogic.run();

        FIS fis = jFuzzyLogic.getFis();

        List<FuzzyOutputDTO> response = new ArrayList<FuzzyOutputDTO>();
        List<String> usages = Arrays.asList("home","gaming","programming", "webHosting");
        for(String usage : usages)
        {
            var percentage = fis.getFunctionBlock("sablon").getVariable(usage);
            response.add(new FuzzyOutputDTO(usage,percentage.getValue()));
        }

        return response;
    }


}
