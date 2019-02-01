package com.egtinteractive.player;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public interface Opponent {

  public boolean move(final Board board, final Marker marker);
}
