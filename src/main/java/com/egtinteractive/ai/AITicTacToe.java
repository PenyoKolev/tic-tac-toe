package com.egtinteractive.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public class AITicTacToe implements AI {
  public final Board board;
  public final Marker marker;

  public AITicTacToe(Board board, Marker marker) {
    this.board = board;
    this.marker = marker;
  }

  @Override
  public boolean move() {
    List<Integer> freeCells = getFreeCells();
    final Random rand = new Random();
    if (freeCells.size() < 1) {
      return true;
    }
    final int randomElement = freeCells.get(rand.nextInt(freeCells.size()));
    final int row = randomElement / 3;
    final int col = randomElement % 3;
    board.getGrid()[row][col] = marker;
    board.getFreeCells()[randomElement] = marker;
    return false;
  }

  @Override
  public List<Integer> getFreeCells() {
    final List<Integer> freeCells = new ArrayList<>();
    for (int i = 0; i < board.getFreeCells().length; i++) {
      if (board.getFreeCells()[i] == Marker.EMPTY) {
        freeCells.add(i);
      }
    }
    return freeCells;
  }
}
