package com.example.iz.connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import com.example.iz.model.CaseDescription;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.jcolibri.util.FileIO;
//import ucm.gaia.jcolibri.cbrcore.CBRCase;
//import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
//import ucm.gaia.jcolibri.exception.InitializingException;
//import ucm.gaia.jcolibri.util.FileIO;


public class CsvConnector implements Connector {
    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("/data/pc.csv")));
            if (br == null)
                throw new Exception("Error opening file");

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");

                CBRCase cbrCase = new CBRCase();

                CaseDescription caseDescription = new CaseDescription();

                // TODO

                caseDescription.setCores(Integer.parseInt(values[0]));
                caseDescription.setCpuFrequency(Double.parseDouble(values[1]));
                caseDescription.setRamSize(Integer.parseInt(values[2]));
                caseDescription.setStorageType(values[3]);
                caseDescription.setStorageSize(Integer.parseInt(values[4]));
                caseDescription.setFormFactor(values[5]);
                caseDescription.setGpuSize(Integer.parseInt(values[6]));
                caseDescription.setPrice(Integer.parseInt(values[7]));
                caseDescription.setBrand(values[8]);
                caseDescription.setName(values[9]);


                cbrCase.setDescription(caseDescription);
                cases.add(cbrCase);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
        return null;
    }

    @Override
    public void storeCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void close() {
    }

    @Override
    public void deleteCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void initFromXMLfile(URL arg0) throws InitializingException {
    }
}
