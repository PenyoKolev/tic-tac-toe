package com.egtinteractive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.egtinteractive.db.Queries;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.player.HumanPlayer;
import com.egtinteractive.player.Opponent;

public class TicTacToeGame implements Game {

  private Scanner scanner = new Scanner(System.in);
  private static final int PRICE = 10;
  private final Board board;
  private final List<Opponent> opponents;
  private String winnerName;

  public TicTacToeGame(Board board, List<Opponent> opponents) {
    this.board = board;
    this.opponents = opponents;
  }

  @Override
  public boolean startGame() {
    int maxMoves = board.getCells().length;
    boolean noMoreMoves = false;
    for (int i = 0; i < maxMoves; ) {
      if (noMoreMoves) {
        break;
      }
      for (Opponent opponent : opponents) {
        i++;
        if (i != maxMoves + 1) {
          final int index = opponent.getNextMove(board);
          board.makeMove(index, opponent.getMarker());
          Marker marker = board.hasWinner();
          if (marker == Marker.EMPTY) {
            continue;
          } else {
            System.out.println("The winner is: " + opponent.getMarker());
            if (opponent instanceof HumanPlayer) {
              System.out.println("Please, enter your name:");
              winnerName = scanner.nextLine();
            }
            noMoreMoves = true;
            break;
          }
        } else {
          noMoreMoves = true;
          break;
        }
      }
    }

    System.out.println();
    showGame();
    writeGameToDb();
    showHallOfFame();
    return true;
  }

  @Override
  public int getPrice() {
    return PRICE;
  }

  private void writeGameToDb() {
    final Queries query = new Queries();
    if (winnerName == null) {
      query.addLoseGame();
      return;
    }
    final int id = query.getId(winnerName);
    if (id != 0) {
      query.addWinGameKnownPlayer(id);
    } else if (winnerName.length() != 0) {
      query.addWinGameUnknownPlayer(winnerName);
    }
  }

  private void showGame() {
    board.showBoard();
  }

  private void showHallOfFame() {
    final Queries query = new Queries();
    final ArrayList<String> result = query.topThree();
    System.out.println("\nHall of Fame:");
    for (String string : result) {
      System.out.println(string);
    }
  }
}
