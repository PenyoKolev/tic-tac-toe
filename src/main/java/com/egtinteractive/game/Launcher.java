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

  /*
   * [WARNING] author ivailozd
   *
   *  Bounded to particular type of games
   *
   */
  public boolean start() {
    while (!game.isOver()) {
      io.write("Your next move is: ");
      game.showGame();
      int x = io.readNextInt();
      if (x == -1) {
        break;
      }
      if (x > 8) {
        io.write("Choose a number between 0 and 8");
        continue;
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
      } else if (name.length() != 0) {
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
