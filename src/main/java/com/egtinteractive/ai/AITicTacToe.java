package com.egtinteractive.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public class AITicTacToe implements AI {

  @Override
  public boolean move(final Board board, final Marker marker) {
    final List<Integer> freeCells = getFreeCells(board);
    final Random rand = new Random();
    if (freeCells.size() < 1) {
      return true;
    }
    final int randomElement = freeCells.get(rand.nextInt(freeCells.size()));
    final int row = randomElement / board.getGrid().length;
    final int col = randomElement % board.getGrid().length;
    board.getGrid()[row][col] = marker;
    board.getCells()[randomElement] = marker;
    return false;
  }

  public List<Integer> getFreeCells(final Board board) {
    final List<Integer> freeCells = new ArrayList<>();
    for (int i = 0; i < board.getCells().length; i++) {
      if (board.getCells()[i].equals(Marker.EMPTY)) {
        freeCells.add(i);
      }
    }
    return freeCells;
  }
}
