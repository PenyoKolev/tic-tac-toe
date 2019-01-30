package com.egtinteractive.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class DBConnection {

  /*
   * [WARNING] author ivailozd
   *
   * Make this a configuration
   *
   */
  private static final String url = "jdbc:mysql://localhost:3306/game_db";
  private static final String user = "root";
  private static final String password = "3211";

  public static Connection getConnection() {
    try {
      DriverManager.registerDriver(new Driver());
      return DriverManager.getConnection(url, user, password);
    } catch (SQLException ex) {
      throw new RuntimeException("Error connecting to the database", ex);
    }
  }
}
