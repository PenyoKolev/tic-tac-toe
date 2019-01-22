package com.egtinteractive;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.egtinteractive.db.DBConnection;

public class App {

  public static void main(String[] args) throws SQLException  {
    // TODO Auto-generated method stub
    try (Connection connection = DBConnection.getConnection();) {
      Statement statement = connection.createStatement();
      ResultSet rs = ((java.sql.Statement) statement).executeQuery("select * from players"); 
      while (rs.next()) { 
          System.out.println(rs.getInt(1) + " " + rs.getString(2)); 
      } 
      rs.close();  
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
