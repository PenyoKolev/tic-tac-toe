package com.egtinteractive.board;

public class Board {
  Marker[][] grid = {
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY},
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY},
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY}
  };

  boolean[][] freeCells = {
    {true, true, true},
    {true, true, true},
    {true, true, true}
  };

  public void move(int position, Marker sign) {
    if (isFree(position)) {
      int row = position / 3;
      int col = position % 3;
      grid[row][col] = sign;
      if (sign != Marker.EMPTY) {
        freeCells[row][col] = false;
      }
    }
  }

  public void showBoard() {
    for (int i = 0; i < grid.length; i++) {
      StringBuilder sb = new StringBuilder();
      sb.append(" ")
          .append(grid[i][0])
          .append(" | ")
          .append(grid[i][1])
          .append(" | ")
          .append(grid[i][2]);
      System.out.println(sb.toString());
      if (i < grid.length - 1) {
        System.out.println("-----------");
      }
    }
  }

  public void showOccupied() {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {

        System.out.print(freeCells[i][j] + " ");
      }
      System.out.println();
    }
  }

  public boolean isFree(int position) {
    int row = position / 3;
    int col = position % 3;
    return freeCells[row][col];
  }
}
