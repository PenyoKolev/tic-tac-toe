package com.egtinteractive.player;

import com.egtinteractive.ai.AI;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public class AIPlayer implements Opponent {

  final AI ai;

  public AIPlayer(AI ai) {
    this.ai = ai;
  }

  @Override
  public boolean move(final Board board, final Marker marker) {
    return ai.move(board, marker);
  }
}
