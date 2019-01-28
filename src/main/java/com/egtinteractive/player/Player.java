package com.egtinteractive.player;

import com.egtinteractive.board.Marker;

public class Player {
  private String name;
  private Marker marker;

  public Player() {
    this.marker = Marker.X;
  }

  public String getName() {
  return name;
  }

  public Marker getMarker() {
  return marker;
  }

  public void setMarker(final Marker marker) {
  this.marker = marker;
  }
}
