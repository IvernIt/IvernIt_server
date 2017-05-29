/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.ia;

import com.ivernit.server.xml.Resultados;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sampru
 */
public class TreeElement {

  private final String rgx = "|   ";
  private final String simbolo, cifra, dato;
  private final int nivel, fila;
  private final boolean gradedA;
  private int numOfA;

  public TreeElement(String line, int fila, boolean gradedA) {
    String datos = line.replace(rgx, "");
    this.nivel = (line.length() - datos.length()) / rgx.length();

    String[] splitted = datos.split(" ");
    this.dato = splitted[0].toLowerCase();
    this.simbolo = splitted[1];
    this.cifra = splitted[2].replace(":", "");
    this.fila = fila;
    this.gradedA = gradedA;

    if (this.gradedA) {
      String num = splitted[4];
      Matcher matcher = Pattern.compile("\\d+").matcher(num);
      matcher.find();
      this.numOfA = Integer.parseInt(matcher.group());
    }
  }

  public void writeData(Resultados.Resultado resultado) {
    switch (this.dato) {
      case "agua":
        insertarAgua(resultado);
        break;
      case "luz":
        insertarLuz(resultado);
        break;
      case "temperatura":
        insertarTemperatura(resultado);
        break;
      default:
        resultado.setTierra(this.cifra);
        break;
    }
  }

  private void insertarAgua(Resultados.Resultado resultado) {
    Integer value = Integer.parseInt(this.cifra);
    BigInteger max = resultado.getAgua().getMaxInclusive();
    BigInteger min = resultado.getAgua().getMinInclusive();
    if (this.simbolo.equals("<")) {
      value++;
    }
    if (this.simbolo.equals(">")) {
      value--;
    }

    if (this.simbolo.contains("<")) {
      if (max != null) {
        if (max.compareTo(new BigInteger(value.toString())) == 1) {
          resultado.getAgua().setMaxInclusive(new BigInteger(value.toString()));
        }
      }
    }
    if (this.simbolo.contains(">")) {
      if (min != null) {
        if (min.compareTo(new BigInteger(value.toString())) == -1) {
          resultado.getAgua().setMinInclusive(new BigInteger(value.toString()));
        }
      }
    }
  }

  private void insertarLuz(Resultados.Resultado resultado) {
    Integer value = Integer.parseInt(this.cifra);
    Integer max = resultado.getLuz().getMaxInclusive();
    Integer min = resultado.getLuz().getMinInclusive();
    if (this.simbolo.equals("<")) {
      value++;
    }
    if (this.simbolo.equals(">")) {
      value--;
    }

    if (this.simbolo.contains("<")) {
      if (max != null) {
        if (max.compareTo(value) == 1) {
          resultado.getAgua().setMaxInclusive(new BigInteger(value.toString()));
        }
      }
    }
    if (this.simbolo.contains(">")) {
      if (min != null) {
        if (min.compareTo(value) == -1) {
          resultado.getAgua().setMinInclusive(new BigInteger(value.toString()));
        }
      }
    }
  }

  private void insertarTemperatura(Resultados.Resultado resultado) {
    Integer value = Integer.parseInt(this.cifra);
    BigInteger max = resultado.getTemperatura().getMaxInclusive();
    BigInteger min = resultado.getTemperatura().getMinInclusive();
    if (this.simbolo.equals("<")) {
      value++;
    }
    if (this.simbolo.equals(">")) {
      value--;
    }

    if (this.simbolo.contains("<")) {
      if (max != null) {
        if (max.compareTo(new BigInteger(value.toString())) == 1) {
          resultado.getTemperatura().setMaxInclusive(new BigInteger(value.toString()));
        }
      }
    }
    if (this.simbolo.contains(">")) {
      if (min != null) {
        if (min.compareTo(new BigInteger(value.toString())) == -1) {
          resultado.getTemperatura().setMinInclusive(new BigInteger(value.toString()));
        }
      }
    }
  }

  public String getDato() {
    return dato;
  }

  public String getCifra() {
    return cifra;
  }

  public int getFila() {
    return fila;
  }

  public int getNivel() {
    return nivel;
  }

  public String getSimbolo() {
    return simbolo;
  }

  public boolean isGradedA() {
    return gradedA;
  }

  public int getNumOfA() {
    return numOfA;
  }

}
