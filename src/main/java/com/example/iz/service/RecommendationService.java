package com.example.iz.service;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final QueryService queryService;
    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static final String OWL = "http://www.w3.org/2002/07/owl#";

    @Autowired
    public RecommendationService(QueryService queryService) {
        this.queryService = queryService;
    }

    public List<String> getComponents(String componentName){
        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("rdf", RDF);
        queryStr.setNsPrefix("base", BASE);
        queryStr.append("SELECT ?component");
        queryStr.append("{");
        queryStr.append("?component rdf:type ");
        queryStr.append("base:");
        queryStr.append(componentName);
        queryStr.append(".");
        queryStr.append("}");
        System.out.println(queryStr);
        return queryService.getQueryResult(queryStr);
    }

    public List<String> findSpeakers(String searchValue){
        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("rdf", RDF);
        queryStr.setNsPrefix("base", BASE);
        queryStr.append("SELECT ?component");
        queryStr.append("{");
        queryStr.append("?component rdf:type ");
        queryStr.append("base:Speakers");
        queryStr.append(".");
        queryStr.append("?component base:frequency_response '");
        queryStr.append(searchValue + "'");
        queryStr.append(".");
        queryStr.append("}");
        System.out.println(queryStr);
        return queryService.getQueryResult(queryStr);
    }

}
