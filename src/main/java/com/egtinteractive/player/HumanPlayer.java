package com.egtinteractive.player;

import com.egtinteractive.board.Marker;

public class HumanPlayer implements Player {
  Marker marker;

  public HumanPlayer(Marker marker) {
    this.marker = marker;
  }

  @Override
  public Marker getMarker() {
    return marker;
  }

  @Override
  public void setMarker(Marker marker) {
    this.marker = marker;
  }
}
