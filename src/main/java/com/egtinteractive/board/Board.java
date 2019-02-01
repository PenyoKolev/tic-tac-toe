package com.egtinteractive.board;

import com.egtinteractive.io.InputOutput;

public interface Board {

  public void showBoard(final InputOutput io);

  public boolean isFree(final int position);
  
  public boolean hasWinner(Marker marker);

  public Marker[][] getGrid();

  public Marker[] getCells();
  
}
