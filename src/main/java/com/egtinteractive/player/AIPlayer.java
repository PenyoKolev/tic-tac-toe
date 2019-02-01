package com.egtinteractive.player;

import com.egtinteractive.ai.AI;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public class AIPlayer implements Opponent {

  final AI ai;
  Marker marker;

  public AIPlayer(AI ai, Marker marker) {
    this.ai = ai;
    this.marker = marker;
  }

  @Override
  public boolean move(Board board) {
    return ai.move(board, marker);
  }
}
