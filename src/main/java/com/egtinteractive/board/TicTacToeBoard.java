package com.egtinteractive.board;

import com.egtinteractive.io.InputOutput;

public class TicTacToeBoard implements Board {
  private final Marker[][] grid = {
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY},
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY},
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY}
  };

  private final Marker[] freeCells = {
    Marker.EMPTY,
    Marker.EMPTY,
    Marker.EMPTY,
    Marker.EMPTY,
    Marker.EMPTY,
    Marker.EMPTY,
    Marker.EMPTY,
    Marker.EMPTY,
    Marker.EMPTY
  };

  @Override
  public void showBoard(final InputOutput io) {
    for (int i = 0; i < grid.length; i++) {
      final StringBuilder sb = new StringBuilder();
      sb.append(" ")
          .append(grid[i][0])
          .append(" | ")
          .append(grid[i][1])
          .append(" | ")
          .append(grid[i][2]);
      io.write(sb.toString());
      if (i < grid.length - 1) {
        io.write("-----------");
      }
    }
  }

  /*
   * [WARNING] author ivailozd
   *
   * Adhering to naming convention this method should return a boolean.
   * Either rename it or return a boolean. Which approach will make the method
   * more usable?
   *
   */
  @Override
  public Marker isFree(final int position) {
    return freeCells[position];
  }

  @Override
  public Marker[][] getGrid() {
    return grid;
  }

  @Override
  public Marker[] getFreeCells() {
    return freeCells;
  }
}
