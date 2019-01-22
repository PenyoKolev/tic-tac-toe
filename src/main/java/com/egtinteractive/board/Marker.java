package com.egtinteractive.board;

public enum Marker {
  X,
  O,
  EMPTY {
    @Override
    public String toString() {
      return " ";
    }
  };
}
