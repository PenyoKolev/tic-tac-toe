package com.egtinteractive;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.egtinteractive.board.Marker;
import com.egtinteractive.db.Queries;
import com.egtinteractive.game.Game;
import com.egtinteractive.game.Game.Result;

public class App {

  public static void main(String[] args) throws SQLException {
    Game game = new Game();
    Marker marker = Marker.X;
    game.getPlayer().setMarker(marker);

    Scanner scanner = new Scanner(System.in);

    while (!game.isEnd()) {
      System.out.println("Your next move is: ");
      game.showGame();

      int x = scanner.nextInt();
      if (x == -1) {
        break;
      }
      game.move(x);
    }
    Queries query = new Queries();
    if (game.getResult() == Result.PLAYER) {
      System.out.println("Please, enter your name:");
      scanner.nextLine();
      String name = scanner.nextLine();
      int id = query.getId(name);
      if (id != 0) {
        query.addWinGameKnownPlayer(id);
      } else {
        query.addWinGameUnknownPlayer(name);
      }
    } else {
      query.addGame("COMPUTER_WIN");
    }

    game.showGame();
    scanner.close();
    ArrayList<String> result = query.topThree();
    System.out.println("\nHall of Fame:" ); 
    for (String string : result) {
      System.out.println(string ); 
    }
  }
}
