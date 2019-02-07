package com.egtinteractive.board;

import com.egtinteractive.io.IO;

public interface Board {

  public boolean isFree(final int position);

  public void makeMove(final int index, final Marker marker);

  public Marker[] getCells();

  public void showBoard(IO io);

  public Marker hasWinner();
}
