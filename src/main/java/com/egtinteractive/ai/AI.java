package com.egtinteractive.ai;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public interface AI {

  public boolean move(Board board, Marker marker);
}
