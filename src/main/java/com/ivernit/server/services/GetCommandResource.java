/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sampru
 */
@Path("getCommand")
public class GetCommandResource {

  @Context
  private UriInfo context;

  /**
   * Creates a new instance of GetCommandResource
   */
  public GetCommandResource() {
  }

  /**
   * PUT method for updating or creating an instance of GetCommandResource
   * @param order order to receive
   * @return 
   */
  @PUT
  @Path("/{order}/")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_XML)
  public String annadirComando(@PathParam("order") String order) {
     System.out.println("Orden recivida: " + order); 
     return "<ok>" + order + "</ok>";
  }
}
