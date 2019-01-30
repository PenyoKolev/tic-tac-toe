package com.egtinteractive.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.jdbc.Driver;

public class DBConnection {
  
  
                    //TODO single connection
  
  
  public static Connection getConnection() {
    try {
      DriverManager.registerDriver(new Driver());
      Properties p = getCredentials("src/main/resources/configuration.properties");
      return DriverManager.getConnection(
          p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
    } catch (SQLException e) {
      throw new RuntimeException("Error connecting to the database", e);
    }
  }

  private static Properties getCredentials(String filePath) {
    Properties p = new Properties();
    try (FileInputStream fis = new FileInputStream(filePath)) {
      p.load(fis);
    } catch (IOException e) {
      throw new RuntimeException("File not found", e);
    }
    return p;
  }
}
