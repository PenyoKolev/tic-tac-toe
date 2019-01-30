package com.egtinteractive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.egtinteractive.board.TicTacToeBoard;
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
  private Board board;
  private boolean isOver;
  private Result result;
  private int price;
  private Launcher launcher;
  private InputOutput io;

  public TicTacToeGame() {
    setBoard(new TicTacToeBoard());
    player = new Player();
    setResult(Result.DRAW);
    setPrice(10);
  }

  @Override
  public boolean startGame(final Game game, final InputOutput io) {
    setIo(io);
    launcher = new Launcher(game, io);
    launcher.start();
    return true;
  }

  /*
   * [WARNING] author ivailozd
   *
   * Hard-coded playing order and code repetition.
   *
   */
  public void move(final int position) {
    if (getBoard().isFree(position) != Marker.EMPTY) {
      getIo().write("Position already in use!");
      return;
    }
    playerMove(position);
    if (isWin()) {
      resultHelper("Player win !!!", Result.PLAYER_WIN);
      return;
    }
    aiMove();
    if (isWin()) {
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

  public boolean isWin() {
    return checkRows() || checkColumns() || checkDiagonals();
  }

  /*
   * [WARNING] author ivailozd
   *
   * These checks should be board's responsibility because it knows its size.
   * And if the Board interface has just one method, e.g. hasWinner(),
   * there could be boards with different winning rules.
   *
   */
  public boolean checkRows() {
    for (int i = 0; i < 3; i++) {
      final Marker[] row = getBoard().getGrid()[i];
      if (row[0] == row[1] && row[1] == row[2] && row[2] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  public boolean checkColumns() {
    for (int i = 0; i < 3; i++) {
      if (getBoard().getGrid()[0][i] == getBoard().getGrid()[1][i]
          && getBoard().getGrid()[1][i] == getBoard().getGrid()[2][i]
          && getBoard().getGrid()[2][i] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  public boolean checkDiagonals() {
    final Marker[][] grid = getBoard().getGrid();
    if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[2][2] != Marker.EMPTY) {
      return true;
    }
    if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[2][0] != Marker.EMPTY) {
      return true;
    }
    return false;
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

  public void setPrice(final int price) {
    this.price = price;
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

  public void setBoard(final Board board) {
    this.board = board;
  }

  public InputOutput getIo() {
    return io;
  }

  public void setIo(final InputOutput io) {
    this.io = io;
  }
}
