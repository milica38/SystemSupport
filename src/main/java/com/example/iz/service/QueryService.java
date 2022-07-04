package com.example.iz.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {

    public final static String CLASSES_AND_INSTANCES_PATH = "/data/PC_classes.owl";
    protected Model model;

    public QueryService() {
        model = ModelFactory.createDefaultModel();
        try{
            InputStream is = TypeReference.class.getResourceAsStream(CLASSES_AND_INSTANCES_PATH);
            RDFDataMgr.read(model,is,Lang.TURTLE);
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Model loaded");
    }

    public List<String> executeQuery(Query query){
        QueryExecution exec = QueryExecutionFactory.create(query,model);
        return PrintQuery(exec.execSelect());
    }

    public List<String> PrintQuery(ResultSet resultSet){
        List<String> strings = new ArrayList<>();
        while (resultSet.hasNext()) {
            QuerySolution solution = resultSet.nextSolution();
            strings.add(solution.toString());
        }
        return strings;
    }

}
