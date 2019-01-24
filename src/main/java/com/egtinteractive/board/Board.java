package com.egtinteractive.board;

public interface Board {
  
  public void showBoard();

  public Marker isFree(int position);

  public Marker[][] getGrid();

  public Marker[] getFreeCells();
}
