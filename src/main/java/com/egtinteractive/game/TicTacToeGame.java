package com.egtinteractive.game;

import java.util.ArrayList;
import com.egtinteractive.db.Queries;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.player.Opponent;

public class TicTacToeGame implements Game {

  private static final int PRICE = 10;
  private final InputOutput io;
  private final Board board;
  private final Marker marker;
  private final Order order;
  private final Opponent opponent;
  private Result result;

  public TicTacToeGame(InputOutput io, Board board, Marker marker, Order order, Opponent opponent) {
    this.io = io;
    this.board = board;
    this.marker = marker;
    this.order = order;
    this.opponent = opponent;
  }

  @Override
  public boolean startGame() {
    if (order == Order.PLAYER_ONE_FIRST) {
      while (!orderFirstPlayer()) ;
    }
    if (order == Order.PLAYER_TWO_FIRST) {
      while (!orderSecondPlayer()) ;
    }
    writeGameToDb();
    showGame();
    showHallOfFame();
    return true;
  }

  @Override
  public int getPrice() {
    return PRICE;
  }

  private boolean orderFirstPlayer() {
    showGame();
    if (board.getCells().length < 1) {
      resultHelper("Draw !!!", Result.DRAW);
      return true;
    }
    io.write("Your next move is: ");

    int position = io.readNextInt();

    if (!isValidPosition(position)) {
      return false;
    }
    playerOneMove(position);
    if (board.hasWinner(marker)) {
      resultHelper("Player win !!!", Result.PLAYER_WIN);
      return true;
    }
    playerTwoMove();
    if (board.hasWinner(otherMarker(marker))) {
      resultHelper("Computer win !!!", Result.COMPUTER_WIN);
      return true;
    }
    return false;
  }

  private boolean orderSecondPlayer() {
    playerTwoMove();
    showGame();
    if (board.hasWinner(otherMarker(marker))) {
      resultHelper("Computer win !!!", Result.COMPUTER_WIN);
      return true;
    }
    if (board.getCells().length < 1) {
      resultHelper("Draw !!!", Result.DRAW);
      return true;
    }

    io.write("Your next move is: ");
    while (true) {
      int position = io.readNextInt();
      if (!isValidPosition(position)) {
        continue;
      } else {
        playerOneMove(position);
        break;
      }
    }
    if (board.hasWinner(marker)) {
      resultHelper("Player win !!!", Result.PLAYER_WIN);
      return true;
    }
    return false;
  }

  private boolean isValidPosition(int position) {
    if (position < 0 || position > board.getCells().length - 1) {
      io.write("Choose a number between 0 and " + (board.getCells().length - 1));
      return false;
    }
    if (board.isFree(position) == false) {
      getIo().write("Position already in use!");
      return false;
    }
    return true;
  }

  private void playerOneMove(final int position) {
    final int row = position / board.getGrid().length;
    final int col = position % board.getGrid().length;

    board.getGrid()[row][col] = marker;
    board.getCells()[position] = marker;
  }

  private void playerTwoMove() {
    if (opponent.move(board)) {
      resultHelper("Draw !!!", Result.DRAW);
    }
  }

  private void writeGameToDb() {
    final Queries query = new Queries();
    if (getResult() == Result.PLAYER_WIN) {
      io.write("Please, enter your name:");
      io.read();
      final String name = io.read();
      int id = query.getId(name);
      if (id != 0) {
        query.addWinGameKnownPlayer(id);
      } else if (name.length() != 0) {
        query.addWinGameUnknownPlayer(name);
      }
    } else {
      query.addLoseGame();
    }
  }

  private void showHallOfFame() {
    final Queries query = new Queries();
    final ArrayList<String> result = query.topThree();
    io.write("\nHall of Fame:");
    for (String string : result) {
      io.write(string);
    }
  }

  private void resultHelper(String string, Result result) {
    getIo().write(string);
    setResult(result);
  }

  private void showGame() {
    board.showBoard(io);
  }
  
  private Marker otherMarker(Marker marker) {
    if (marker.equals(Marker.X)) {
      return Marker.O;
    } else {
      return Marker.X;
    }
  }

  public Result getResult() {
    return result;
  }

  private void setResult(final Result result) {
    this.result = result;
  }

  public InputOutput getIo() {
    return io;
  }
}
