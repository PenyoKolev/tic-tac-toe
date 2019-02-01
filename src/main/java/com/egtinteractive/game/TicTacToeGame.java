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
    if (order.equals(Order.PLAYER_ONE_FIRST)) {
      while (!orderFirstPlayer()) ;
    }
    if (order.equals(Order.PLAYER_TWO_FIRST)) {
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
    if (isDraw()) {
      return true;
    }
    playerOneMove();
    if (hasWinner(marker)) {
      return true;
    }
    playerTwoMove();
    if (hasWinner(otherMarker(marker))) {
      return true;
    }
    return false;
  }

  private boolean orderSecondPlayer() {
    playerTwoMove();
    showGame();
    if (hasWinner(otherMarker(marker))) {
      return true;
    }
    if (isDraw()) {
      return true;
    }
    playerOneMove();
    if (hasWinner(marker)) {
      return true;
    }
    return false;
  }

  private void playerOneMove() {
    io.write("Your next move is: ");
    while (true) {
      final int position = io.readNextInt();
      if (!isValidPosition(position)) {
        continue;
      } else {
        final int row = position / board.getGrid().length;
        final int col = position % board.getGrid().length;
        board.getGrid()[row][col] = marker;
        board.getCells()[position] = marker;
        break;
      }
    }
  }

  private void playerTwoMove() {
    if (opponent.move(board, otherMarker(marker))) {
      resultHelper("Draw !!!", Result.DRAW);
    }
  }

  private void writeGameToDb() {
    final Queries query = new Queries();
    if (getResult().equals(Result.PLAYER_WIN)) {
      io.write("Please, enter your name:");
      io.read();
      final String name = io.read();
      final int id = query.getId(name);
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

  private void resultHelper(final String string, final Result result) {
    getIo().write(string);
    setResult(result);
  }

  private boolean hasWinner(final Marker marker) {
    if (board.hasWinner(marker)) {
      if (marker == this.marker) {
        resultHelper("Player win !!!", Result.PLAYER_WIN);
      } else {
        resultHelper("Computer win !!!", Result.COMPUTER_WIN);
      }
      return true;
    }
    return false;
  }

  private boolean isDraw() {
    if (board.getCells().length < 1) {
      resultHelper("Draw !!!", Result.DRAW);
      return true;
    }
    return false;
  }

  private boolean isValidPosition(final int position) {
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

  private void showGame() {
    board.showBoard(io);
  }

  private Marker otherMarker(final Marker marker) {
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
