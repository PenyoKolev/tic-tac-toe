package com.egtinteractive.game;

import java.util.ArrayList;
import com.egtinteractive.db.Queries;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.InputOutput;

public class Launcher {

  InputOutput io = new ConsoleIO();
  Game game;

  public Launcher(Game game) {
    this.game = game;
  }

  public boolean start() {
    while (!game.isOver()) {
      System.out.println("Your next move is: ");
      game.showGame();

      int x = io.readNextInt();
      if (x == -1) {
        break;
      }
      game.move(x);
    }
    Queries query = new Queries();
    if (game.result() == Result.PLAYER_WIN) {
      System.out.println("Please, enter your name:");
      io.read();
      String name = io.read();
      int id = query.getId(name);
      if (id != 0) {
        query.addWinGameKnownPlayer(id);
      } else {
        query.addWinGameUnknownPlayer(name);
      }
    } else {
      query.addLoseGame();
    }

    game.showGame();
    ArrayList<String> result = query.topThree();
    System.out.println("\nHall of Fame:");
    for (String string : result) {
      System.out.println(string);
    }

    return true;
  }
}
