package com.egtinteractive.player;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public interface Opponent {

  public int getNextMove(final Board board);

  public Marker getMarker();
}
