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
        System.out.println("Model successfully loaded!");
    }

    public List<String> executeQuery(String queryString)  {
        Query query = QueryFactory.create(queryString);
        QueryExecution exec = QueryExecutionFactory.create(query,model);
        return PrintQuery(exec.execSelect());
    }

    public List<String> executeQuery(Query query){
        QueryExecution exec = QueryExecutionFactory.create(query,model);
        return PrintQuery(exec.execSelect());
    }

    public ArrayList<String> getQueryPropertiesResult(ParameterizedSparqlString queryStr) {
        Query q = queryStr.asQuery();
        var rawResponse =  executeQuery(q);
        var components = new ArrayList<String>();
        for(var rawRam : rawResponse){
            if(rawRam.contains("double"))
                components.add(rawRam.split("\"")[1].split("\"")[0]);
            else
                components.add(rawRam.split("=")[1].split(" ")[1]);
        }
        return components;
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
