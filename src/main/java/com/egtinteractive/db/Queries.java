package com.egtinteractive.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Queries {

  public void addPlayer(String name) {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt = connection.prepareStatement("insert into players(name) values(?)");
      stmt.setString(1, name);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addWinGameKnownPlayer(int id) {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt =
          connection.prepareStatement(
              "update players set score = score + 1 where player_id = ?");
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addWinGameUnknownPlayer(String name) {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt =
          connection.prepareStatement("insert into players (name) values (?)");
      stmt.setString(1, name);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addGame(String result) {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt =
          connection.prepareStatement("insert into games(end_time, result) values(?, ?)");
      stmt.setString(
          1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt.setString(2, result);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int getId(String name) {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt = connection.prepareStatement("select * from players where name = ?");
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return rs.getInt("player_id");
      } else {
        return 0;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<String> topThree() {
    ArrayList<String> result = new ArrayList<>();
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt =
          connection.prepareStatement("select * from players order by score desc limit 3");
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(rs.getInt("score") + " " + rs.getString("name"));
      }
      return result;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
