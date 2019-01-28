package com.egtinteractive.player;

import com.egtinteractive.board.Marker;

public class Player {
  private Marker marker;

  public Player() {
    this.marker = Marker.X;
  }

  public Marker getMarker() {
    return marker;
  }
}
