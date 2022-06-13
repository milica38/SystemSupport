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

    private FIS fis;
    private List<String> computerUsages;
    private String blockName;

    public FuzzyService() throws URISyntaxException {
        var path = TypeReference.class.getResource("/data/template.fcl").toURI().getPath();
        this.fis = FIS.load(path, true);
        if (fis != null)
            System.out.println("Fuzzy rules successfully loaded!");

        computerUsages = Arrays.asList("homeUsage","miningUsage","gamingUsage","programmingUsage");
        blockName = "sablon";
    }

    public List<FuzzyOutputDTO> performQuery(int coreNumber, int ramSize, int storageSize, int gpuSize) {
        fis.setVariable("coreNumber", coreNumber);
        fis.setVariable("ramSize", ramSize);
        fis.setVariable("gpuSize", gpuSize);
        fis.setVariable("storageSize", storageSize);
        fis.evaluate();

        var response = new ArrayList<FuzzyOutputDTO>();
        for(var usage : computerUsages)
        {
            var percentage = fis.getFunctionBlock(blockName).getVariable(usage);
            response.add(new FuzzyOutputDTO(usage,percentage.getValue()));
        }

        return response;
    }


}
