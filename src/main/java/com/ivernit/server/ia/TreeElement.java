/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.ia;

import static com.ivernit.server.ia.TreeElement.Dato.*;
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
  private final String simbolo, cifra;
  private final int nivel, fila;
  private final Dato dato;
  private final boolean gradedA;
  private int numOfA;

  public TreeElement(String line, int fila, boolean gradedA) {
    String datos = line.replace(rgx, "");
    nivel = (line.length() - datos.length()) / rgx.length();
    String[] splitted = datos.split(" ");
    dato = getDato(splitted[0]);
    simbolo = splitted[1];
    cifra = splitted[2].replace(":", "");
    this.fila = fila;
    this.gradedA = gradedA;
    if (this.gradedA) {
      String num = splitted[4];
      Matcher matcher = Pattern.compile("\\d+").matcher(num);
      matcher.find();
      numOfA = Integer.parseInt(matcher.group());
    }
  }

  public void writeData(Resultados.Resultado resultado) {
    Integer value;
    switch (this.dato) {
      case AGUA:
        value = Integer.parseInt(this.cifra);
        if (this.simbolo.equals("<=")) {
          resultado.getAgua().setMaxInclusive(new BigInteger(value.toString()));
        }
        if (this.simbolo.equals(">=")) {
          resultado.getAgua().setMinInclusive(new BigInteger(value.toString()));
        }
        if (this.simbolo.equals("<")) {
          value--;
          resultado.getAgua().setMaxInclusive(new BigInteger(value.toString()));
        }
        if (this.simbolo.equals(">")) {
          value++;
          resultado.getAgua().setMinInclusive(new BigInteger(value.toString()));
        }
        break;
      case LUZ:
        value = Integer.parseInt(this.cifra);
        if (this.simbolo.equals("<=")) {
          resultado.getLuz().setMaxInclusive(value);
        }
        if (this.simbolo.equals(">=")) {
          resultado.getLuz().setMinInclusive(value);
        }
        if (this.simbolo.equals("<")) {
          value--;
          resultado.getLuz().setMaxInclusive(value);
        }
        if (this.simbolo.equals(">")) {
          value++;
          resultado.getLuz().setMinInclusive(value);
        }
        break;
      case TEMPERATURA:
        value = Integer.parseInt(this.cifra);
        if (this.simbolo.equals("<=")) {
          resultado.getTemperatura().setMaxInclusive(new BigInteger(value.toString()));
        }
        if (this.simbolo.equals(">=")) {
          resultado.getTemperatura().setMinInclusive(new BigInteger(value.toString()));
        }
        if (this.simbolo.equals("<")) {
          value--;
          resultado.getTemperatura().setMaxInclusive(new BigInteger(value.toString()));
        }
        if (this.simbolo.equals(">")) {
          value++;
          resultado.getTemperatura().setMinInclusive(new BigInteger(value.toString()));
        }
        break;
      default:
        resultado.setTierra(this.cifra);
        break;
    }
  }

  public enum Dato {
    AGUA, LUZ, TEMPERATURA, TIERRA
  }

  public Dato getDato() {
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

  public static Dato getDato(String str) {
    Dato ret;
    switch (str.toUpperCase()) {
      case "AGUA":
        ret = AGUA;
        break;
      case "LUZ":
        ret = LUZ;
        break;
      case "TEMPERATURA":
        ret = TEMPERATURA;
        break;
      default:
        ret = TIERRA;
        break;
    }
    return ret;
  }
}
