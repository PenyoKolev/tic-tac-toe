package com.egtinteractive.player;

import com.egtinteractive.ai.AI;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.io.IO;

public class AIPlayer implements Opponent {

  private final AI ai;
  private final Marker marker;

  public AIPlayer(AI ai, Marker marker) {
    this.ai = ai;
    this.marker = marker;
  }

  @Override
  public int getNextMove(final Board board, IO io) {
    return ai.getNextMove(board);
  }

  @Override
  public Marker getMarker() {
    return marker;
  }
}
