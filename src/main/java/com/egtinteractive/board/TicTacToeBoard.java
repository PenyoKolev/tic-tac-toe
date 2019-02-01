package com.egtinteractive.board;

import java.util.Arrays;
import com.egtinteractive.io.InputOutput;

public class TicTacToeBoard implements Board {
  private Marker[][] grid;
  private Marker[] cells;
  private final int size;

  public TicTacToeBoard(int size) {
    this.size = size;
    populateArrays(size);
  }

  @Override
  public void showBoard(final InputOutput io) {
    for (int i = 0; i < size; i++) {
      final StringBuilder sb = new StringBuilder();
      sb.append(" ");
      for (int j = 0; j < size - 1; j++) {
        sb.append(grid[i][j]).append(" | ");
      }
      sb.append(grid[i][size - 1]);
      io.write(sb.toString());
      if (i < size - 1) {
        for (int j = 0; j < size; j++) {
          io.writeSameLine("----");
        }
        io.write("");
      }
    }
  }

  @Override
  public boolean isFree(final int position) {
    return cells[position].equals(Marker.EMPTY);
  }

  @Override
  public Marker[][] getGrid() {
    return grid;
  }

  @Override
  public Marker[] getCells() {
    return cells;
  }

  @Override
  public boolean hasWinner(final Marker marker) {
    return checkRows(marker) || checkColumns(marker) || checkDiagonals(marker);
  }

  public boolean checkRows(final Marker marker) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (grid[i][j].equals(marker)) count++;
      }
      if (count == size) return true;
      count = 0;
    }
    return false;
  }

  private boolean checkColumns(final Marker marker) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (grid[j][i].equals(marker)) count++;
      }
      if (count == size) return true;
      count = 0;
    }
    return false;
  }

  private boolean checkDiagonals(final Marker marker) {
    final Marker[][] grid = getGrid();
    boolean leftDiagonal = true;
    boolean rightDiagonal = true;
    for (int i = 0; i < grid.length; i++) {
      leftDiagonal = true;
      rightDiagonal = true;
      int row = size - 1 - i;
      if (!grid[i][i].equals(marker)) leftDiagonal = false;
      if (!grid[row][i].equals(marker)) rightDiagonal = false;
      if (!(leftDiagonal || rightDiagonal)) break;
    }
    return (leftDiagonal || rightDiagonal);
  }

  private void populateArrays(final int size) {
    cells = new Marker[size * size];
    grid = new Marker[size][size];
    Arrays.fill(cells, Marker.EMPTY);
    for (Marker[] row : grid) {
      Arrays.fill(row, Marker.EMPTY);
    }
  }
}
