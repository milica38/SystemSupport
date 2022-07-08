package com.example.iz.service;

import com.example.iz.dto.SpeakersDTO;
import org.apache.jena.base.Sys;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeakersService {

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    @Autowired
    private QueryService queryService;
    @Autowired
    private RecommendationService recommendationService;

    public List<SpeakersDTO> findSpeakers(String searchValue) {

        List<SpeakersDTO> allSpeakers = getAllSpeakers();
        List<SpeakersDTO> result = new ArrayList<SpeakersDTO>();
        for (SpeakersDTO s: allSpeakers) {
            if(s.getFrequencyResponse().equals(searchValue))
                result.add(s);
        }
        return result;
    }
    public List<SpeakersDTO> getAllSpeakers() {
        List<String> speakersNames = recommendationService.getComponents("Speakers");
        List<String> speakersFreq = recommendationService.getComponentDataProperty("Speakers", "frequency_response");
        List<String> speakersBrand = recommendationService.getComponentObjectProperty("Speakers", "has_brand");

        List<SpeakersDTO> result = new ArrayList<SpeakersDTO>();

        for (int i = 0; i < speakersNames.size(); i++) {

            SpeakersDTO dto = new SpeakersDTO();
            dto.setName(speakersNames.get(i));
            dto.setFrequencyResponse(speakersFreq.get(i));
            dto.setBrand(speakersBrand.get(i));
            result.add(dto);
        }

        return result;
    }
}
