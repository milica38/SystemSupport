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
    public static final String BASE_ONTOLOGY_PREFIX = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String IMPORT_ONTOLOGY_PREFIX = "http://www.semanticweb.org/anja/ontologies/2022/3/untitled-ontology-3#";
    public static final String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    public static final String OWL = "http://www.w3.org/2002/07/owl#";

    @Autowired
    public RecommendationService(QueryService queryService) {
        this.queryService = queryService;
    }

    public List<String> getComponents(String component){
        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.setNsPrefix("rdf", RDF);
        queryStr.setNsPrefix("import",IMPORT_ONTOLOGY_PREFIX);
        queryStr.append("SELECT ?s");
        queryStr.append("{");
        queryStr.append("?s rdf:type");
        queryStr.append(" import:");
        queryStr.append(component);
        queryStr.append(".");
        queryStr.append("}");
        return queryService.getQueryResult(queryStr);
    }

}
