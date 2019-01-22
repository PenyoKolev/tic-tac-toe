package com.egtinteractive;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.db.DBConnection;

public class App {

  public static void main(String[] args) throws SQLException {
    // TODO Auto-generated method stub
    try (Connection connection = DBConnection.getConnection(); ) {
      Statement statement = connection.createStatement();
      ResultSet rs = ((java.sql.Statement) statement).executeQuery("select * from players");
      while (rs.next()) {
        System.out.println(rs.getInt(1) + " " + rs.getString(2));
      }
      rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Board board = new Board();
    board.move(0, Marker.O);
    board.move(1, Marker.X);
    board.move(2, Marker.EMPTY);
    board.move(3, Marker.O);
    board.move(4, Marker.X);
    board.move(5, Marker.X);
    board.move(6, Marker.O);
    System.out.println(board.isFree(2));

    board.showBoard();
    board.showOccupied();
  }
}
