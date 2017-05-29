/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.ia.services;

import com.ivernit.server.ia.Algoritmo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sampru
 */
@Path("resultado")
public class ResultadoResource {

  @Context
  private UriInfo context;

  /**
   * Creates a new instance of ResultadoResource
   */
  public ResultadoResource() {
  }

  /**
   * Retrieves representation of an instance of com.ivernit.server.ia.services.ResultadoResource
   * @return an instance of java.lang.String
   */
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public String getXml() {
   Algoritmo algoritmo = new Algoritmo();
    try {
      return algoritmo.results();
    } catch (Exception ex) {
      Logger.getLogger(ResultadoResource.class.getName()).log(Level.SEVERE, null, ex);
      return "<resultados>error</resultados>";
    }
  }
}
