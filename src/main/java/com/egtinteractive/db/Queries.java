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

  public void addWinGame(int playerId, String result) {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt =
          connection.prepareStatement(
              "insert into games(player_id, end_time, result) values(?, ?, ?)");
      stmt.setInt(1, playerId);
      stmt.setString(
          2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt.setString(3, result);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addLoseGame(String result) {
    try (Connection connection = DBConnection.getConnection(); ) {
      PreparedStatement stmt =
          connection.prepareStatement(
              "insert into games(player_id, end_time, result) values(?, ?, ?)");
      stmt.setNull(1, java.sql.Types.NULL);
      stmt.setString(
          2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt.setString(3, result);
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
          connection.prepareStatement(
              "select count(*) as wins, players.name "
                  + "from players as players "
                  + "join games as games on players.player_id = games.player_id "
                  + "where games.result = 'win' and players.name not like '' "
                  + "group by games.player_id "
                  + "order by wins desc "
                  + "limit 3;");
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(rs.getInt("wins") + " " + rs.getString("name"));
      }
      return result;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
