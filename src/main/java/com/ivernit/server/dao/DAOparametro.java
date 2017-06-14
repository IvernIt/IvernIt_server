/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sampru
 */
public class DAOparametro {

  public static ArrayList<String> getListaParametrosNotasPorVegetal(int idVegetal, int estadoCrecimiento, Connection con) throws SQLException {
    ArrayList<String> list = new ArrayList();

    PreparedStatement ps = con.prepareStatement(
            "SELECT p.pAgua AS Agua, p.pHorasLuz AS Luz, p.pTemperatura AS Temperatura, p.pTipoTierra AS Tierra, c.cResultado AS Nota "
            + "FROM parametro p JOIN cultivo c ON p.pId = c.pId "
            + "JOIN vegetal v ON c.vId = v.vId "
            + "WHERE c.vId = ? AND v.ecId = ?");
    ps.setInt(1, idVegetal);
    ps.setInt(2, idVegetal);

    try (ResultSet rs = ps.executeQuery()) {

      while (rs.next()) {
        list.add(rs.getString("Agua") + ","
                + rs.getString("Luz") + ","
                + rs.getString("Temperatura") + ","
                + rs.getString("Tierra") + ","
                + rs.getString("Nota"));
      }
    }
    return list;
  }

}
