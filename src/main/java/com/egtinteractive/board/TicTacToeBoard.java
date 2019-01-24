package com.egtinteractive.board;

public class TicTacToeBoard implements Board{
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

  @Override
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

  @Override
  public Marker isFree(int position) {
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
