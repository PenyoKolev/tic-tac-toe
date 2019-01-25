package com.egtinteractive.board;

import com.egtinteractive.io.InputOutput;

public interface Board {
  
  public void showBoard(InputOutput io);

  public Marker isFree(int position);

  public Marker[][] getGrid();

  public Marker[] getFreeCells();
}
