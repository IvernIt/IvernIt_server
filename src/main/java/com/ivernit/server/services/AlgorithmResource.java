/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.services;

import com.ivernit.server.ia.Algoritmo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sampru
 */
@Path("algorithm")
public class AlgorithmResource {

  @Context
  private UriInfo context;

  /**
   * Creates a new instance of AlgorithmResource
   */
  public AlgorithmResource() {
  }

  /**
   * Retrieves representation of an instance of
   * com.ivernit.server.services.AlgorithmResource
   *
   * @param idVegetal
   * @param estadoCrecimiento
   * @return an instance of java.lang.String
   */
  @GET
  @Consumes("text/plain")
  @Produces(MediaType.APPLICATION_XML)
  public String getXml(@QueryParam("id") int idVegetal, @QueryParam("ec") int estadoCrecimiento) {
    Algoritmo algoritmo = new Algoritmo();
    String respuesta;
    try {
      respuesta = algoritmo.results(idVegetal, estadoCrecimiento);
    } catch (Exception ex) {
      Logger.getLogger(AlgorithmResource.class.getName()).log(Level.SEVERE, null, ex);
      respuesta = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<resultados>error</resultados>";
    }
    return respuesta;
  }
}
