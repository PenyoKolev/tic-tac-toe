package player;

import com.egtinteractive.board.Marker;

public class Player {
  private String name;
  private Marker marker;

  public Player() {
  }

  public String getName() {
  return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public Marker getMarker() {
  return marker;
  }

  public void setMarker(Marker marker) {
  this.marker = marker;
  }
}
