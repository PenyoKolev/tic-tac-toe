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

  public void move(final int position) {
    if (getBoard().isFree(position) != Marker.EMPTY) {
      getIo().write("Position already in use!");
      return;
    }
    playerMove(position);
    if (isWin()) {
      getIo().write("Player win !!!");
      isOver = true;
      setResult(Result.PLAYER_WIN);
      return;
    }
    aiMove();
    if (isWin()) {
      getIo().write("Computer win !!!");
      isOver = true;
      setResult(Result.COMPUTER_WIN);
      return;
    }
    if (board.getFreeCells().length < 1) {
      getIo().write("Computer win !!!");
      isOver = true;
      setResult(Result.DRAW);
      return;
    }
  }

  public void playerMove(final int position) {
    final int row = position / 3;
    final int col = position % 3;
    getBoard().getGrid()[row][col] = player.getMarker();
    getBoard().getFreeCells()[position] = player.getMarker();
  }

  public void aiMove() {
    final List<Integer> freeCells = new ArrayList<>();
    for (int i = 0; i < getBoard().getFreeCells().length; i++) {
      if (getBoard().getFreeCells()[i] == Marker.EMPTY) {
        freeCells.add(i);
      }
    }
    final Random rand = new Random();
    if (freeCells.size() < 1) {
      isOver = true;
      result = Result.DRAW;
      getIo().write("DRAW !!!");
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

  @Override
  public void showGame() {
    getBoard().showBoard(io);
  }

  public Player getPlayer() {
    return player;
  }

  public boolean isOver() {
    return isOver;
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
