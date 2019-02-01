package com.egtinteractive.player;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.io.InputOutput;

public class HumanPlayer implements Opponent {
  private final InputOutput io;

  public HumanPlayer(InputOutput io) {
    this.io = io;
  }

  @Override
  public boolean move(final Board board, final Marker marker) {
    io.write("Player two next move is: ");
    while (true) {
      final int position = io.readNextInt();
      if (position < 0 || position > board.getCells().length - 1) {
        io.write("Choose a number between 0 and " + (board.getCells().length - 1));
        continue;
      } else if (board.isFree(position) == false) {
        io.write("Position already in use!");
        continue;
      } else {
        final int row = position / board.getGrid().length;
        final int col = position % board.getGrid().length;
        board.getGrid()[row][col] = marker;
        board.getCells()[position] = marker;
        break;
      }
    }
    return false;
  }
}
