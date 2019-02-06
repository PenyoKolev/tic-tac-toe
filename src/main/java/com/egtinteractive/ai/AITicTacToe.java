package com.egtinteractive.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public class AITicTacToe implements AI {

  @Override
  public int getNextMove(final Board board) {
    final List<Integer> freeCells = new ArrayList<>();
    for (int i = 0; i < board.getCells().length; i++) {
      if (board.getCells()[i].equals(Marker.EMPTY)) {
        freeCells.add(i);
      }
    }
    final Random rand = new Random();
    if (freeCells.size() < 1) {
      return -1;
    }
    return freeCells.get(rand.nextInt(freeCells.size()));
  }
}
