package com.egtinteractive.provider;

import java.util.ArrayList;
import java.util.List;
import com.egtinteractive.ai.AITicTacToe;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.player.AIPlayer;
import com.egtinteractive.player.HumanPlayer;
import com.egtinteractive.player.Opponent;

public class Generator {

  private static ArcadeMachine machine;

  public static ArcadeMachine selectGame() {
    machine = new ArcadeMachine(new ConsoleIO());
    return machine;
  }

  public static ArcadeMachine playGameAI() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new AIPlayer(new AITicTacToe(), Marker.X));
    opponents.add(new AIPlayer(new AITicTacToe(), Marker.O));
    machine = new ArcadeMachine(new ConsoleIO());
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
  
  public static ArcadeMachine playGameHPWinByRow() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));
    String[] indices = {"0", "3", "1", "4", "2", ""};
    machine = new ArcadeMachine(new FakeIO(indices));
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
  
  public static ArcadeMachine playGameHPWinByCol() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));
    String[] indices = {"0", "1", "3", "2", "6", ""};
    machine = new ArcadeMachine(new FakeIO(indices));
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
  
  public static ArcadeMachine playGameHPWinByDiagonal() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));
    String[] indices = {"0", "1", "4", "2", "8", ""};
    machine = new ArcadeMachine(new FakeIO(indices));
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
  
  public static ArcadeMachine playGameHPInvalidMove() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));
    String[] indices = {"0", "3", "0", "-9", "1", "4", "2", ""};
    machine = new ArcadeMachine(new FakeIO(indices));
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
  
  public static ArcadeMachine playGameHPNoMoreMoves() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));
    String[] indices = {"0", "2", "1", "3", "5", "4", "6", "8", "7"};
    machine = new ArcadeMachine(new FakeIO(indices));
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
}
