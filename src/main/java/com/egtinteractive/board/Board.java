package com.egtinteractive.board;

public class Board {
  Marker[][] grid = {
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY},
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY},
    {Marker.EMPTY, Marker.EMPTY, Marker.EMPTY}
  };

  Marker[] freeCells = {
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
    for (int i = 0; i < freeCells.length; i++) {
      System.out.print(freeCells[i] + " ");
      if (i == 2 || i == 5) {
        System.out.println();
      }
    }
  }

  public Marker isFree(int position) {
    return freeCells[position];
  }

  public Marker[][] getGrid() {
    return grid;
  }

  public Marker[] getFreeCells() {
    return freeCells;
  }
}
