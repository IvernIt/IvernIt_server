/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sampru
 */
public class ParametersArffGenerator {

  private static final String HEADER = "@relation vegetables_grow\n"
          + "\n"
          + "@attribute 'agua' numeric\n"
          + "% A numeric value measuring the litres per square centimeter in the greenhouse\n"
          + "\n"
          + "@attribute 'luz' numeric\n"
          + "% A numeric value measuring the hours of exposition to light of the greenhouse\n"
          + "\n"
          + "@attribute 'temperatura' numeric\n"
          + "% A numeric value measuring the mean temperature of the greenhouse\n"
          + "\n"
          + "@attribute 'tierra' {arcilloso, limoso, arenoso, margoso, gredoso, pantanoso}\n"
          + "% A nominal type that establishes the type of ground used on the greenhouse\n"
          + "\n"
          + "@attribute 'nota' {A, B, C, D}\n"
          + "% A nominal value measuring the category achieved by the vegetables of the greenhouse,\n"
          + "% being A the highest and D the lowest scores.\n"
          + "\n"
          + "@data";

  public static void generateFile(String filename, ArrayList<String> data) throws IOException {
    
    if (!filename.endsWith(".arff")){
      filename += ".arff";
    }
    
    File file = new File(filename);
    if (!file.exists()) {
      file.createNewFile();
    }

    try (FileOutputStream fileOut = new FileOutputStream(file)) {

      fileOut.write(ParametersArffGenerator.HEADER.getBytes());
      fileOut.flush();

      for (String s : data) {
        fileOut.write(("\n" + s).getBytes());
        fileOut.flush();
      }
    }
  }

  public static String generateString(ArrayList<String> data) throws IOException {
    String ret = "";

    ret += HEADER;
    for (String s : data) {
      ret += ("\n" + s);
    }

    return ret;
  }
}
