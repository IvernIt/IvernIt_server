/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.ia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sampru
 */
public class Main {
  
  public static void main(String[] args) {
    try {
      Algoritmo algoritmo = new Algoritmo();
      System.out.println(algoritmo.results(1, 1));
    } catch (Exception ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
