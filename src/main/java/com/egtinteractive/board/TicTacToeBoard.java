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

  @Override
  public boolean isFree(final int position) {
    return freeCells[position] == Marker.EMPTY;
  }

  @Override
  public Marker[][] getGrid() {
    return grid;
  }

  @Override
  public Marker[] getFreeCells() {
    return freeCells;
  }

  @Override
  public boolean hasWinner() {
    return checkRows() || checkColumns() || checkDiagonals();
  }

  public boolean checkRows() {
    for (int i = 0; i < 3; i++) {
      final Marker[] row = this.getGrid()[i];
      if (row[0] == row[1] && row[1] == row[2] && row[2] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  public boolean checkColumns() {
    for (int i = 0; i < 3; i++) {
      if (getGrid()[0][i] == getGrid()[1][i]
          && getGrid()[1][i] == getGrid()[2][i]
          && getGrid()[2][i] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  public boolean checkDiagonals() {
    final Marker[][] grid = getGrid();
    if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != Marker.EMPTY) {
      return true;
    }
    if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] != Marker.EMPTY) {
      return true;
    }
    return false;
  }
}
