package com.egtinteractive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.player.Player;

public class TicTacToeGame implements Game{
  

  Board board;
  Player player;
  private boolean end;
  private Result result;
  private int price;
  Launcher launcher;

  public TicTacToeGame() {
    board = new TicTacToeBoard();
    player = new Player();
    price = 10;
    setResult(Result.DRAW);
    setPrice(0);
  }
  
  @Override
  public boolean startGame(Game game) {
    launcher = new Launcher(game);
    launcher.start();
    return true;
  }

  public void move(int position) {
    if (board.isFree(position) != Marker.EMPTY) {
      System.out.println("Position already in use");
      return;
    }
    playerMove(position);
    if (isWin()) {
      System.out.println("Player win !!!");
      end = true;
      setResult(Result.PLAYER_WIN);
      return;
    }
    aiMove();
    if (isWin()) {
      System.out.println("Computer win !!!");
      end = true;
      setResult(Result.COMPUTER_WIN);
      return;
    }
  }

  public void playerMove(int position) {
      int row = position / 3;
      int col = position % 3;
      board.getGrid()[row][col] = player.getMarker();
      board.getFreeCells()[position] = player.getMarker();
  }

  public void aiMove() {
    List<Integer> freeCells = new ArrayList<>();
    for (int i = 0; i < board.getFreeCells().length; i++) {
      if (board.getFreeCells()[i] == Marker.EMPTY) {
        freeCells.add(i);
      }
    }
    Random rand = new Random();
    int randomElement = freeCells.get(rand.nextInt(freeCells.size()));
    int row = randomElement / 3;
    int col = randomElement % 3;
    Marker marker;
    if (player.getMarker() == Marker.X) {
      marker = Marker.O;
    } else {
      marker = Marker.X;
    }
    board.getGrid()[row][col] = marker;
    board.getFreeCells()[randomElement] = player.getMarker();
  }

  public boolean isWin() {
    return checkRows() || checkColumns() || checkDiagonals();
  }

  public boolean checkRows() {
    for (int i = 0; i < 3; i++) {
      Marker[] row = board.getGrid()[i];
      if (row[0] == row[1] && row[1] == row[2] && row[2] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  public boolean checkColumns() { // TODO Fix this
    for (int i = 0; i < 3; i++) {
      if (board.getGrid()[0][i] == board.getGrid()[1][i]
          && board.getGrid()[1][i] == board.getGrid()[2][i]
          && board.getGrid()[2][i] != Marker.EMPTY) {
        return true;
      }
    }
    return false;
  }

  public boolean checkDiagonals() {
    Marker[][] grid = board.getGrid();
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
    board.showBoard();
  }

  public Player getPlayer() {
    return player;
  }

  public boolean isOver() {
    return end;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
  
  @Override
  public int getPrice() {
    return price;
  }
  
  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public Result result() {
    // TODO Auto-generated method stub
    return result;
  }

  
}
