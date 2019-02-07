package com.egtinteractive.player;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.io.IO;

public interface Opponent {

  public int getNextMove(final Board board, IO io);

  public Marker getMarker();
}
