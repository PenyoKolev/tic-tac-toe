package com.egtinteractive.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.sql.DataSource;

public class Queries {

  public void addWinGameKnownPlayer(final int id) {
    DataSource ds = DBFactory.getDataSource();
    Connection con = null;
    PreparedStatement stmt = null;
    PreparedStatement stmt1 = null;
    try {
      con = ds.getConnection();
      con.setAutoCommit(false);
      stmt = con.prepareStatement("update players set score = score + 1 where player_id = ?");
      stmt.setInt(1, id);
      stmt.executeUpdate();
      stmt1 = con.prepareStatement("insert into games(end_time, result) values(?, 'PLAYER_WIN')");
      stmt1.setString(
          1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt1.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (stmt1 != null) stmt1.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
      } catch (SQLException e2) {
        throw new RuntimeException(e2);
      }
    }
  }

  public void addWinGameUnknownPlayer(final String name) {
    DataSource ds = DBFactory.getDataSource();
    Connection con = null;
    PreparedStatement stmt = null;
    PreparedStatement stmt1 = null;
    try {
      con = ds.getConnection();
      con.setAutoCommit(false);
      stmt = con.prepareStatement("insert into players (name) values (?)");
      stmt.setString(1, name);
      stmt.executeUpdate();
      stmt1 = con.prepareStatement("insert into games(end_time, result) values(?, 'PLAYER_WIN')");
      stmt1.setString(
          1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt1.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (stmt1 != null) stmt1.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
      } catch (SQLException e2) {
        throw new RuntimeException(e2);
      }
    }
  }

  public void addLoseGame() {
    DataSource ds = DBFactory.getDataSource();
    Connection con = null;
    PreparedStatement stmt = null;
    try {
      con = ds.getConnection();
      stmt = con.prepareStatement("insert into games(end_time, result) values(?, 'COMPUTER_WIN')");
      stmt.setString(
          1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (stmt != null) stmt.close();
        if (con != null) con.close();
      } catch (SQLException e2) {
        throw new RuntimeException(e2);
      }
    }
  }

  public int getId(final String name) {
    DataSource ds = DBFactory.getDataSource();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = ds.getConnection();
      stmt = con.prepareStatement("select * from players where name = ?");
      stmt.setString(1, name);
      rs = stmt.executeQuery();
      if (rs.next()) {
        return rs.getInt("player_id");
      } else {
        return 0;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
      } catch (SQLException e2) {
        throw new RuntimeException(e2);
      }
    }
  }

  public ArrayList<String> topThree() {
    final ArrayList<String> result = new ArrayList<>();
    DataSource ds = DBFactory.getDataSource();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = ds.getConnection();
      stmt =
          con.prepareStatement(
              "select * from players "
                  + "where name not like '' "
                  + "order by score desc "
                  + "limit 3");
      rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(rs.getInt("score") + " " + rs.getString("name"));
      }
      return result;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
      } catch (SQLException e2) {
        throw new RuntimeException(e2);
      }
    }
  }
}
