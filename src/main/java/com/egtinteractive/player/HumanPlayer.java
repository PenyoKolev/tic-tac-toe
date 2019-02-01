package com.egtinteractive.player;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public class HumanPlayer implements Opponent {
  Marker marker;

  public HumanPlayer(Marker marker) {
    this.marker = marker;
  }

  @Override
  public boolean move(final Board board) {
    // TODO Auto-generated method stub
    return false;
  }
}
