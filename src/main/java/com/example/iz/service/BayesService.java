package com.example.iz.service;

import com.example.iz.dto.BayesDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BayesService {

    public List<BayesDTO> findDamage(BayesDTO dto) throws IOException, URISyntaxException {
        List<BayesDTO> results = new ArrayList<BayesDTO>();

        // loading from file
        BaseIO io = new NetIO();
        ProbabilisticNetwork net = (ProbabilisticNetwork)io.load(new File(TypeReference.class.getResource("/data").toURI().getPath() +"/bayesNetwork.net"));

        // compiling
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        // states overview
        List<Node> nodeList = net.getNodes();
        for (Node node: nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
            }
        }

        // adding an evidence
        ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(dto.getSymptome());
        int stateIndex = 1; // index of state "green"
        factNode.addFinding(stateIndex);

        System.out.println();

        // propagation
        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // states overview after propagation
        for (Node node : nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
            }
        }
        // saving to file
        new NetIO().save(new File("/data/results.net"), net);

        return results;
    }
}
