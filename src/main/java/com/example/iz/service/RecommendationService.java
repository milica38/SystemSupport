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

    public static final String BASE = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static final String OWL = "http://www.w3.org/2002/07/owl#";

    @Autowired
    private QueryService queryService;

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

        Query q = queryStr.asQuery();
        var rawResponse =  queryService.executeQuery(q);
        System.out.println(rawResponse);
        var components = new ArrayList<String>();
        for(var rawRam : rawResponse)
            components.add(rawRam.split("#")[1].split(">")[0]);

        return components;
    }

    public List<String> getComponentDataProperty(String componentName, String propertyName){

        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("rdf", RDF);
        queryStr.setNsPrefix("base", BASE);
        queryStr.append("SELECT ?p");
        queryStr.append("{");
        queryStr.append("?component rdf:type ");
        queryStr.append("base:" + componentName);
        queryStr.append(". ");
        queryStr.append("?component base:" + propertyName + " ?p ");
        queryStr.append(".");
        queryStr.append("}");
        System.out.println(queryStr);

        Query q = queryStr.asQuery();
        var rawResponse = queryService.executeQuery(q);
        var result = new ArrayList<String>();
        for (var rawRam : rawResponse) {
            //System.out.println(rawRam);
            result.add(rawRam.split("=")[1].split("\"")[1].split("\"")[0]);
        }

        return result;
    }

    public List<String> getComponentObjectProperty(String componentName, String propertyName){

        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("rdf", RDF);
        queryStr.setNsPrefix("base", BASE);
        queryStr.append("SELECT ?p");
        queryStr.append("{");
        queryStr.append("?component rdf:type ");
        queryStr.append("base:" + componentName);
        queryStr.append(". ");
        queryStr.append("?component base:" + propertyName + " ?p ");
        queryStr.append(".");
        queryStr.append("}");
        System.out.println(queryStr);

        Query q = queryStr.asQuery();
        var rawResponse = queryService.executeQuery(q);
        var result = new ArrayList<String>();
        for (var rawRam : rawResponse) {
            System.out.println(rawRam);
            result.add(rawRam.split("#")[1].split(">")[0]);
        }

        return result;
    }

}
