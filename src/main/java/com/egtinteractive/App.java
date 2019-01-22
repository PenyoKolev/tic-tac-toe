package com.egtinteractive;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.egtinteractive.board.Marker;
import com.egtinteractive.db.DBConnection;
import com.egtinteractive.game.Game;

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

    Game game = new Game();
    Marker marker = Marker.X;
    game.getPlayer().setMarker(marker);
    
    Scanner scanner = new Scanner(System.in);
    while(!game.isEnd()) {
      System.out.println("Your next move is: " );
      game.showGame();

      int x = scanner.nextInt();
      if (x == -1) {
        break;
        
      }
      game.move(x);

    }
    game.showGame();
    scanner.close();

  }
}
