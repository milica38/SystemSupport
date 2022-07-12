package com.example.iz.service;

import com.example.iz.dto.BayesInputDTO;
import com.example.iz.dto.BayesOutputDTO;
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

    public List<BayesOutputDTO> findDamage(BayesInputDTO dto) throws IOException, URISyntaxException {

        List<BayesOutputDTO> results = new ArrayList<BayesOutputDTO>();

        // loading from file
        BaseIO io = new NetIO();
        ProbabilisticNetwork net = (ProbabilisticNetwork)io.load(new File(TypeReference.class.getResource("/data").toURI().getPath() +"/bayesNetwork.net"));

        // compiling
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        // states overview
        System.out.println("States overview:");
        List<Node> nodeList = net.getNodes();
        for (Node node: nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
            }
        }

        // adding an evidence

        ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(dto.getSymptome());
        int stateIndex = 0; // index of state "green"
        factNode.addFinding(stateIndex);

        System.out.println();

        // propagation
        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // states overview after propagation

        System.out.println("States overview after propagation:");
        for (Node node : nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
                if(node.getStateAt(i).equals("yes") && node.getName().endsWith("damage")) {
                    results.add(new BayesOutputDTO(node.getName().toUpperCase().replace("_", " ") ,((ProbabilisticNode) node).getMarginalAt(i)*100));
                }
            }
        }
        // saving to file
        new NetIO().save(new File(TypeReference.class.getResource("/data").toURI().getPath() +"/results.net"), net);

        return results;
    }
}
