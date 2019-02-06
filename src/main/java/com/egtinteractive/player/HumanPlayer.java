package com.egtinteractive.player;

import java.util.Scanner;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;

public class HumanPlayer implements Opponent {
  Scanner scanner = new Scanner(System.in);
  private final Marker marker;

  public HumanPlayer(Marker marker) {
    this.marker = marker;
  }

  @Override
  public int getNextMove(final Board board) {
    System.out.println("Your next move: ");
    while (true) {
      final int position = scanner.nextInt();
      if (position < 0 || position > board.getCells().length - 1) {
        System.out.println("Choose a number between 0 and " + (board.getCells().length - 1));
        continue;
      } else if (board.isFree(position) == false) {
        System.out.println("Position already in use!");
        continue;
      } else {
        return position;
      }
    }
  }

  @Override
  public Marker getMarker() {
    return marker;
  }
}
