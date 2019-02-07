package com.egtinteractive.player;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.io.IO;

public class HumanPlayer implements Opponent {
  private final Marker marker;

  public HumanPlayer(Marker marker) {
    this.marker = marker;
  }

  @Override
  public int getNextMove(final Board board, IO io) {
    io.write("Your next move: ");
    while (true) {
      final int position = Integer.parseInt(io.read());
      if (position < 0 || position > board.getCells().length - 1) {
        io.write("Choose a number between 0 and " + (board.getCells().length - 1));
        continue;
      } else if (board.isFree(position) == false) {
        io.write("Position already in use!");
        continue;
      } else {
        return position;
      }
    }
  }

  @Override
  public Marker getMarker() {
    return marker;
  }
}
