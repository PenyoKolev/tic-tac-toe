package com.egtinteractive.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class DBConnection {

  static String url = "jdbc:mysql://localhost:3306/game_db";
  static String user = "root";
  static String password = "3211";

  public static Connection getConnection() {
    try {
      DriverManager.registerDriver(new Driver());
      return DriverManager.getConnection(url, user, password);
    } catch (SQLException ex) {
      throw new RuntimeException("Error connecting to the database", ex);
    }
  }
}
