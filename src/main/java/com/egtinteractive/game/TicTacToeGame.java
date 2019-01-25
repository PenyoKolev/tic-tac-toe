package com.egtinteractive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.player.Player;

public class TicTacToeGame implements Game {

  private Board board;
  Player player;
  private boolean isOver;
  private Result result;
  private int price;
  Launcher launcher;

  public TicTacToeGame() {
    setBoard(new TicTacToeBoard());
    player = new Player();
    setResult(Result.DRAW);
    setPrice(40);
  }

  @Override
  public boolean startGame(Game game) {
    launcher = new Launcher(game);
    launcher.start();
    return true;
  }

  public void move(int position) {
    if (getBoard().isFree(position) != Marker.EMPTY) {
      System.out.println("Position already in use");
      return;
    }
    playerMove(position);
    if (isWin()) {
      System.out.println("Player win !!!");
      isOver = true;
      setResult(Result.PLAYER_WIN);
      return;
    }
    aiMove();
    if (isWin()) {
      System.out.println("Computer win !!!");
      isOver = true;
      setResult(Result.COMPUTER_WIN);
      return;
    }
    if (board.getFreeCells().length < 1) {
      System.out.println("Computer win !!!");
      isOver = true;
      setResult(Result.DRAW);
      return;
    }
  }

  public void playerMove(int position) {
    int row = position / 3;
    int col = position % 3;
    getBoard().getGrid()[row][col] = player.getMarker();
    getBoard().getFreeCells()[position] = player.getMarker();
  }

  public void aiMove() {
    List<Integer> freeCells = new ArrayList<>();
    for (int i = 0; i < getBoard().getFreeCells().length; i++) {
      if (getBoard().getFreeCells()[i] == Marker.EMPTY) {
        freeCells.add(i);
      }
    }
    Random rand = new Random();
    if (freeCells.size() < 1) {
      isOver = true;
      result = Result.DRAW;
      return;
      
    }
    int randomElement = freeCells.get(rand.nextInt(freeCells.size()));
    int row = randomElement / 3;
    int col = randomElement % 3;
    Marker marker;
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
      Marker[] row = getBoard().getGrid()[i];
      if (row[0] == row[1] && row[1] == row[2] && row[2] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  public boolean checkColumns() { // TODO Fix this
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
    Marker[][] grid = getBoard().getGrid();
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
    getBoard().showBoard();
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

  public void setResult(Result result) {
    this.result = result;
  }

  public void setPrice(int price) {
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

  public void setBoard(Board board) {
    this.board = board;
  }
}
