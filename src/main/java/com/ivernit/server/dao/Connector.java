/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivernit.server.dao;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author sampru
 */
public class Connector {

  private final static String HOST = "jdbc:mysql://sampru.sytes.net/IvernIt";
  private final static String DRIVER = "com.mysql.jdbc.Driver";
  private final static String USER = "ivernit";
  private final static String PASS = "1vern1t";

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Properties pro = new Properties();
    Class.forName(DRIVER);
    pro.put("user", USER);
    pro.put("password", PASS);
    return DriverManager.getConnection(HOST, pro);
  }

}
