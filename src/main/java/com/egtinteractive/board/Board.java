package com.egtinteractive.board;

public interface Board {

  public boolean isFree(final int position);

  public void makeMove(final int index, final Marker marker);

  public Marker[] getCells();

  public void showBoard();

  public Marker hasWinner();
}
