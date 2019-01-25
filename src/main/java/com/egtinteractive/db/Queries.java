package com.egtinteractive.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Queries {

  public void addWinGameKnownPlayer(int id) {
    try (Connection connection = DBConnection.getConnection(); ) {
      connection.setAutoCommit(false);
      PreparedStatement stmt =
          connection.prepareStatement("update players set score = score + 1 where player_id = ?");
      stmt.setInt(1, id);
      stmt.executeUpdate();

      PreparedStatement stmt1 =
          connection.prepareStatement(
              "insert into games(end_time, result) values(?, 'PLAYER_WIN')");
      stmt1.setString(
          1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt1.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void addWinGameUnknownPlayer(String name) {
    try (Connection connection = DBConnection.getConnection(); ) {
      connection.setAutoCommit(false);
      PreparedStatement stmt = connection.prepareStatement("insert into players (name) values (?)");
      stmt.setString(1, name);
      stmt.executeUpdate();
      PreparedStatement stmt1 =
          connection.prepareStatement(
              "insert into games(end_time, result) values(?, 'PLAYER_WIN')");
      stmt1.setString(
          1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt1.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void addLoseGame() {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt =
          connection.prepareStatement(
              "insert into games(end_time, result) values(?, 'COMPUTER_WIN')");
      stmt.setString(
          1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
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
          connection.prepareStatement(
              "select * from players "
                  + "where name not like '' "
                  + "order by score desc "
                  + "limit 3");
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
