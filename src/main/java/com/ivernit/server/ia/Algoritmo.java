/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.ia;

import com.ivernit.server.xml.Resultados;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 *
 * @author sampru
 */
public class Algoritmo {

  private final String PATH = "files/vegetable_parameters.arff";
  private final int HEADER_SIZE = 3;
  private final int BOTTOM_SIZE = 4;

  private List<TreeElement> tree;

  public String results() throws FileNotFoundException, IOException, Exception {
    String xml;
    try (BufferedReader reader = new BufferedReader(
            new FileReader(PATH))) {

      Instances instancias = createInstancias(reader);

      J48 classifier = createClassifier();

      classifier.buildClassifier(instancias);

      Resultados resultados = processResults(classifier.toString());
      
      xml = createXml(resultados);
    }
    return xml;
  }

  private Instances createInstancias(BufferedReader reader) throws IOException {
    Instances instancias = new Instances(reader);
    instancias.setClassIndex(instancias.numAttributes() - 1);
    return instancias;
  }

  private J48 createClassifier() throws Exception {
    J48 classifier = new J48();
    classifier.setBatchSize("100");
    classifier.setBinarySplits(false);
    classifier.setCollapseTree(true);
    classifier.setConfidenceFactor(0.25f);
    classifier.setDebug(false);
    classifier.setDoNotCheckCapabilities(false);
    classifier.setDoNotMakeSplitPointActualValue(false);
    classifier.setMinNumObj(2);
    classifier.setNumDecimalPlaces(2);
    classifier.setNumFolds(3);
    classifier.setReducedErrorPruning(false);
    classifier.setSaveInstanceData(false);
    classifier.setSeed(1);
    classifier.setSubtreeRaising(true);
    classifier.setUnpruned(false);
    classifier.setUseLaplace(false);
    classifier.setUseMDLcorrection(true);
    return classifier;
  }

  private Resultados processResults(String results) throws JAXBException {
    Resultados resultados = new Resultados();
    Resultados.Resultado resultado = new Resultados.Resultado();
    resultado.setAgua(new Resultados.Resultado.Agua());
    resultado.setLuz(new Resultados.Resultado.Luz());
    resultado.setTemperatura(new Resultados.Resultado.Temperatura());

    tree = new ArrayList();
    String[] lines = results.split("\n");

    for (int i = HEADER_SIZE; i <= lines.length - 1 - BOTTOM_SIZE; i++) {
      TreeElement element;
      if (lines[i].contains(": A (")) {
        element = new TreeElement(lines[i], i, true);
      } else {
        element = new TreeElement(lines[i], i, false);
      }
      tree.add(element);
    }

    for (int i = 0; i < tree.size(); i++) {
      if (tree.get(i).isGradedA()) {
        TreeElement reference = tree.get(i);
        reference.writeData(resultado);
        int j = i;
        while (reference.getNivel() - 1 != -1) {
          j--;
          if (reference.getNivel() == tree.get(j).getNivel() + 1) {
            reference = tree.get(j);
            reference.writeData(resultado);
          }
        }
      }
    }
    resultados.getResultado().add(resultado);

    return resultados;
  }

  private String createXml(Resultados resultados) throws JAXBException {
    String str;
    StringWriter sw = new StringWriter();
    JAXBContext ctx = JAXBContext.newInstance(Resultados.class);
    Marshaller marshaller = ctx.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(resultados, sw);
    str = sw.toString();
    return str;
  }

}
