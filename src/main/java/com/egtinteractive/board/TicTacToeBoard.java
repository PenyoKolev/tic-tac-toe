package com.egtinteractive.board;

import java.util.Arrays;
import com.egtinteractive.io.InputOutput;

public class TicTacToeBoard implements Board {
  private static final int SIZE = 3;
  private final Marker[][] grid = new Marker[SIZE][SIZE];
  private final Marker[] freeCells = new Marker[SIZE * SIZE];

  public TicTacToeBoard() {
    populateArrays();
  }

  @Override
  public void showBoard(final InputOutput io) {
    for (int i = 0; i < SIZE; i++) {
      final StringBuilder sb = new StringBuilder();
      sb.append(" ")
          .append(grid[i][0])
          .append(" | ")
          .append(grid[i][1])
          .append(" | ")
          .append(grid[i][2]);
      io.write(sb.toString());
      if (i < SIZE - 1) {
        io.write("-----------");
      }
    }
  }

  @Override
  public boolean isFree(final int position) {
    return freeCells[position].equals(Marker.EMPTY);
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
    for (int i = 0; i < SIZE; i++) {
      final Marker[] row = this.getGrid()[i];
      if (row[0] == row[1] && row[1] == row[2] && row[2] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  private void populateArrays() {
    Arrays.fill(freeCells, Marker.EMPTY);
    for (Marker[] row : grid) {
      Arrays.fill(row, Marker.EMPTY);
    }
  }

  private boolean checkColumns() {
    for (int i = 0; i < SIZE; i++) {
      if (getGrid()[0][i] == getGrid()[1][i]
          && getGrid()[1][i] == getGrid()[2][i]
          && getGrid()[2][i] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  private boolean checkDiagonals() {
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
