package com.example.iz.service;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    static Model model = ModelFactory.createDefaultModel();

    public List<String> getMotherboards(){
        List<String> result = new ArrayList<>();
        try {
            InputStream is = TypeReference.class.getResourceAsStream("/data/PC_classes.owl");
            RDFDataMgr.read(model,is, Lang.TURTLE);
            String queryString =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                            + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                            + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                            + "PREFIX base: <http://www.semanticweb.org/hp/ontologies/2022/3/untitled-ontology-3#>"

                            + "PREFIX iz: <https://raw.githubusercontent.com/milica38/SystemSupport/main/instances.owl#>"
                                    + "PREFIX PC_classes: <https://raw.githubusercontent.com/milica38/SystemSupport/main/PC_classes.owl#>"

                                    + "SELECT  ?PC_Element "
                            +"WHERE "
                            + "{ "
                          //  + " base:has_component "
                            +"PC_classes:" + "Motherboard " +
                            " a ?PC_Element "
                            +" .} ";
            Query query = QueryFactory.create(queryString);
            System.out.println(query);
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            try {
                ResultSet results = qexec.execSelect();
                while (results.hasNext()) {
                    QuerySolution solution = results.nextSolution();
                    Resource r = solution.getResource("PC_Element");
                    String motherboard = r.getLocalName();
                    System.out.println(motherboard);
                    result.add(motherboard);
                }
                return result;
            } catch(Exception e) {
                e.printStackTrace();
            }finally {
                qexec.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
