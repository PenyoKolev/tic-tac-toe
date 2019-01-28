package com.egtinteractive.game;

import java.util.ArrayList;
import com.egtinteractive.db.Queries;
import com.egtinteractive.io.InputOutput;

public class Launcher {

  private final InputOutput io;
  private final Game game;

  public Launcher(final Game game, final InputOutput io) {
    this.game = game;
    this.io = io;
  }

  public boolean start() {
    while (!game.isOver()) {
      io.write("Your next move is: ");
      game.showGame();
      int x = io.readNextInt();
      if (x == -1) {
        break;
      }
      game.move(x);
    }
    final Queries query = new Queries();
    if (game.result() == Result.PLAYER_WIN) {
      io.write("Please, enter your name:");
      io.read();
      final String name = io.read();
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
    final ArrayList<String> result = query.topThree();
    io.write("\nHall of Fame:");
    for (String string : result) {
      io.write(string);
    }
    return true;
  }
}
