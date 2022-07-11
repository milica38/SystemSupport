package com.example.iz.service;

import com.example.iz.connector.CsvConnector;
import com.example.iz.dto.SimilarityDTO;
import com.example.iz.model.CaseDescription;
import es.ucm.fdi.gaia.jcolibri.casebase.LinealCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbraplications.StandardCBRApplication;
import es.ucm.fdi.gaia.jcolibri.cbrcore.*;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.selection.SelectCases;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Array;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SimilarityService implements StandardCBRApplication {

    Connector _connector;  /** Connector object */
    CBRCaseBase _caseBase;  /** CaseBase object */
    NNConfig simConfig;  /** KNN configuration */


    public void configure() throws ExecutionException {
        _connector =  new CsvConnector();

        _caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization

        simConfig = new NNConfig(); // KNN configuration
        simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average



        // simConfig.addMapping(new Attribute("attribute", CaseDescription.class), new Interval(5));
        // TODO

        simConfig.addMapping(new Attribute("cores", CaseDescription.class), new Equal());
        simConfig.addMapping(new Attribute("cpuFrequency", CaseDescription.class), new Interval(5));
        simConfig.addMapping(new Attribute("ramSize", CaseDescription.class), new Interval(128));
        simConfig.addMapping(new Attribute("storageType", CaseDescription.class), new EqualsStringIgnoreCase());
        simConfig.addMapping(new Attribute("storageSize", CaseDescription.class), new Interval(8000));
        simConfig.addMapping(new Attribute("formFactor", CaseDescription.class), new EqualsStringIgnoreCase());
        simConfig.addMapping(new Attribute("gpuSize", CaseDescription.class), new Interval(16));
        simConfig.addMapping(new Attribute("price", CaseDescription.class), new Interval(300000));


        // Equal - returns 1 if both individuals are equal, otherwise returns 0
        // Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval)
        // Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case
        // EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
        // MaxString - returns a similarity value depending of the biggest substring that belong to both strings
        // EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
        // EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
        // Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
        // TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity)
    }

    public void cycle(CBRQuery query) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
        eval = SelectCases.selectTopKRR(eval, 4);
        System.out.println("Retrieved cases:");
        List<String> cases = new ArrayList<>();
        for (RetrievalResult nse : eval)
            cases.add(nse.get_case().getDescription().toString());
        addToFile(cases);
    }


    public void postCycle() throws ExecutionException {

    }

    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        for (CBRCase c: cases)
            System.out.println(c.getDescription());
        return _caseBase;
    }

    private void addToFile(List<String> cases) {
        try{
            String content = "";
            for (String c : cases)
                content += c + "\n";

            String path="results\\results.txt";
            File file = new File(path);

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private List<SimilarityDTO> readFromFile() {
        List<SimilarityDTO> cases = new ArrayList<>();
        try {
            String path="results\\results.txt";
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;

            while ((str = br.readLine()) != null)
                cases.add(processSingleResult(str));
        } catch (Exception e) {
            System.out.println(e);
        }
        return cases;
    }

    private SimilarityDTO processSingleResult(String result) {
        String[] parts = result.split(",");
        int cores = Integer.parseInt(parts[0].split("=")[1]);
        double cpuFrequency = Double.parseDouble(parts[1].split("=")[1]);
        int ramSize = Integer.parseInt(parts[2].split("=")[1]);
        String storageType = parts[3].split("=")[1];
        int storageSize = Integer.parseInt(parts[4].split("=")[1]);
        String formFactor = parts[5].split("=")[1];
        int gpuSize = Integer.parseInt(parts[6].split("=")[1]);
        int price = Integer.parseInt(parts[7].split("=")[1]);
        String brand = parts[8].split("=")[1];
        String name = parts[9].split("=")[1];



        return new SimilarityDTO(cores, cpuFrequency, ramSize,storageType, storageSize, formFactor, gpuSize, price,brand,name);
    }

    public List<SimilarityDTO> similarity(int cores,double cpuFrequency,int ramSize,String storageType,int storageSize,String formFactor,int gpuSize,int price) {
        StandardCBRApplication recommender = new SimilarityService();
        try {
            recommender.configure();

            recommender.preCycle();

            CBRQuery query = new CBRQuery();
            CaseDescription caseDescription = new CaseDescription();

            // TODO
            caseDescription.setCores(cores);
            caseDescription.setCpuFrequency(cpuFrequency);
            caseDescription.setRamSize(ramSize);
            caseDescription.setStorageType(storageType);
            caseDescription.setStorageSize(storageSize);
            caseDescription.setFormFactor(formFactor);
            caseDescription.setGpuSize(gpuSize);
            caseDescription.setPrice(price);

            query.setDescription( caseDescription );

            recommender.cycle(query);

            return readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }





}
