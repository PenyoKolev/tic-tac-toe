package com.egtinteractive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.db.Queries;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.player.Player;

public class TicTacToeGame implements Game {

  /*
   * [WARNING] author ivailozd
   *
   * Should some of these be final?
   *
   */
  private final Player player;
  private final Board board;
  private final InputOutput io;
  private final int price;
  private boolean isOver;
  private Result result;

  public TicTacToeGame(InputOutput io) {
    this.player = new Player();
    this.board = new TicTacToeBoard();
    this.io = io;
    this.price = 10;
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
      move(x);
    }
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
    showGame();
    final ArrayList<String> result = query.topThree();
    io.write("\nHall of Fame:");
    for (String string : result) {
      io.write(string);
    }
    return true;
  }

  /*
   * [WARNING] author ivailozd
   *
   * Hard-coded playing order and code repetition.
   *
   */
  public void move(final int position) {
    if (getBoard().isFree(position) == false) {
      getIo().write("Position already in use!");
      return;
    }
    playerMove(position);
    if (board.hasWinner()) {
      resultHelper("Player win !!!", Result.PLAYER_WIN);
      return;
    }
    aiMove();
    if (board.hasWinner()) {
      resultHelper("Computer win !!!", Result.COMPUTER_WIN);
      return;
    }
  }

  public void playerMove(final int position) {
    final int row = position / 3;
    final int col = position % 3;

    getBoard().getGrid()[row][col] = player.getMarker();
    getBoard().getFreeCells()[position] = player.getMarker();
  }

  /*
   * [WARNING] author ivailozd
   *
   * How could the AI logic be changed in the future?
   *
   */
  public void aiMove() {
    final List<Integer> freeCells = new ArrayList<>();
    for (int i = 0; i < getBoard().getFreeCells().length; i++) {
      if (getBoard().getFreeCells()[i] == Marker.EMPTY) {
        freeCells.add(i);
      }
    }
    final Random rand = new Random();
    if (freeCells.size() < 1) {
      resultHelper("Draw !!!", Result.DRAW);
      return;
    }
    final int randomElement = freeCells.get(rand.nextInt(freeCells.size()));
    final int row = randomElement / 3;
    final int col = randomElement % 3;
    final Marker marker;
    if (player.getMarker() == Marker.X) {
      marker = Marker.O;
    } else {
      marker = Marker.X;
    }
    getBoard().getGrid()[row][col] = marker;
    getBoard().getFreeCells()[randomElement] = player.getMarker();
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

  public Board getBoard() {
    return board;
  }

  public InputOutput getIo() {
    return io;
  }
}
