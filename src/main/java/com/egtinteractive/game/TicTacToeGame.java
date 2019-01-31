package com.egtinteractive.game;

import java.util.ArrayList;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.db.Queries;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.ai.AI;
import com.egtinteractive.ai.AITicTacToe;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.player.HumanPlayer;

public class TicTacToeGame implements Game {

  private final HumanPlayer player;
  private final Board board;
  private final InputOutput io;
  private final int price;
  private final Order order;
  private Result result;
  private final AI ai;

  public TicTacToeGame(InputOutput io, Marker marker, Order order) {
    this.player = new HumanPlayer(marker);
    this.board = new TicTacToeBoard();
    this.io = io;
    this.price = 10;
    this.order = order;
    if (marker == Marker.X) { // TODO remove logic from here
      this.ai = new AITicTacToe(board, Marker.O);
    } else {
      this.ai = new AITicTacToe(board, Marker.X);
    }
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

  private boolean orderFirstPlayer() {
    showGame();
    if (ai.getFreeCells().size() < 1) {
      resultHelper("Draw !!!", Result.DRAW);
      return true;
    }
    io.write("Your next move is: ");

    int position = io.readNextInt();

    if (!isValidPosition(position)) {
      return false;
    }
    playerOneMove(position);
    if (board.hasWinner()) {
      resultHelper("Player win !!!", Result.PLAYER_WIN);
      return true;
    }
    playerTwoMove();
    if (board.hasWinner()) {
      resultHelper("Computer win !!!", Result.COMPUTER_WIN);
      return true;
    }
    return false;
  }

  private boolean orderSecondPlayer() {
    playerTwoMove();
    showGame();
    if (board.hasWinner()) {
      resultHelper("Computer win !!!", Result.COMPUTER_WIN);
      return true;
    }
    if (ai.getFreeCells().size() < 1) {
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
    if (board.hasWinner()) {
      resultHelper("Player win !!!", Result.PLAYER_WIN);
      return true;
    }
    return false;
  }

  private boolean isValidPosition(int position) {
    if (position > 8 || position < 0) {
      io.write("Choose a number between 0 and 8");
      return false;
    }
    if (getBoard().isFree(position) == false) {
      getIo().write("Position already in use!");
      return false;
    }
    return true;
  }

  private void playerOneMove(final int position) {
    final int row = position / 3;
    final int col = position % 3;

    getBoard().getGrid()[row][col] = player.getMarker();
    getBoard().getFreeCells()[position] = player.getMarker();
  }

  private void playerTwoMove() {
    if (ai.move()) {
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
    getBoard().showBoard(io);
  }

  @Override
  public int getPrice() {
    return price;
  }

  public Result getResult() {
    return result;
  }

  private void setResult(final Result result) {
    this.result = result;
  }

  public Board getBoard() {
    return board;
  }

  public InputOutput getIo() {
    return io;
  }
}
