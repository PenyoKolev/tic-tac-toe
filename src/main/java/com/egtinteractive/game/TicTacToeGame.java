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
  private final Marker marker;
  private final Order order;
  private boolean isOver;
  private Result result;
  private final AI ai;

  public TicTacToeGame(InputOutput io, Marker marker, Order order) {
    this.player = new HumanPlayer(marker);
    this.board = new TicTacToeBoard();
    this.io = io;
    this.price = 10;
    if (marker == Marker.X) {                       //TODO remove logic from here
      this.ai = new AITicTacToe(board, Marker.O);
    } else {
      this.ai = new AITicTacToe(board, Marker.X);
    }
    
    this.marker = marker;
    this.order = order;
  }

  @Override
  public boolean startGame() {
    while (!isOver) {
      io.write("Your next move is: ");
      showGame();
      int x = io.readNextInt();
      if (x == -1) {
        break;
      }
      if (x > 8) {
        io.write("Choose a number between 0 and 8");
        continue;
      }

      if (getBoard().isFree(x) == false) {
        getIo().write("Position already in use!");
        continue;
      }

      if (order == Order.PLAYER_ONE_FIRST) {
        playerOneMove(x);
        if (board.hasWinner()) {
          resultHelper("Player win !!!", Result.PLAYER_WIN);
          break;
        }
        playerTwoMove();
        if (board.hasWinner()) {
          resultHelper("Computer win !!!", Result.COMPUTER_WIN);
          break;
        }
      } else {
        playerTwoMove();
        if (board.hasWinner()) {
          resultHelper("Computer win !!!", Result.COMPUTER_WIN);
          break;
        }
        playerOneMove(x);
        if (board.hasWinner()) {
          resultHelper("Player win !!!", Result.PLAYER_WIN);
          break;
        }
      }
    }
    writeGameToDb();
    showGame();
    showHallOfFame();
    return true;
  }

  /*
   * [WARNING] author ivailozd
   *
   * Hard-coded playing order and code repetition.
   *
   */

  public void playerOneMove(final int position) {
    final int row = position / 3;
    final int col = position % 3;

    getBoard().getGrid()[row][col] = player.getMarker();
    getBoard().getFreeCells()[position] = player.getMarker();
  }

  public void playerTwoMove() {
    if (ai.move()) {
      resultHelper("Draw !!!", Result.DRAW);
    }
  }

  private void writeGameToDb() {
    final Queries query = new Queries();
    if (result() == Result.PLAYER_WIN) {
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
    setOver(true);
    setResult(result);
  }

  @Override
  public void showGame() {
    getBoard().showBoard(io);
  }

  @Override
  public boolean isOver() {
    return isOver;
  }

  public void setOver(final boolean isOver) {
    this.isOver = isOver;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(final Result result) {
    this.result = result;
  }

  @Override
  public Result result() {
    return result;
  }

  @Override
  public int getPrice() {
    return price;
  }

  public Marker getMarker() {
    return marker;
  }

  public Board getBoard() {
    return board;
  }

  public InputOutput getIo() {
    return io;
  }
}
