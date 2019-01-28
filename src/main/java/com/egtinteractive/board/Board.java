package com.egtinteractive.board;

import com.egtinteractive.io.InputOutput;

public interface Board {

  public void showBoard(final InputOutput io);

  public Marker isFree(final int position);

  public Marker[][] getGrid();

  public Marker[] getFreeCells();
}
