package com.egtinteractive.board;

import com.egtinteractive.io.InputOutput;

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
  public void showBoard(InputOutput io) {
    for (int i = 0; i < grid.length; i++) {
      StringBuilder sb = new StringBuilder();
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
