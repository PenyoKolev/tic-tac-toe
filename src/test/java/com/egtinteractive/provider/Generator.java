package com.egtinteractive.provider;

import java.util.ArrayList;
import java.util.List;
import com.egtinteractive.ai.AITicTacToe;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.player.AIPlayer;
import com.egtinteractive.player.HumanPlayer;
import com.egtinteractive.player.Opponent;

public class Generator {

  private static ArcadeMachine machine;

  public static ArcadeMachine selectGame() {
    machine = new ArcadeMachine();
    return machine;
  }

  public static ArcadeMachine playGameAI() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new AIPlayer(new AITicTacToe(), Marker.X));
    opponents.add(new AIPlayer(new AITicTacToe(), Marker.O));
    machine = new ArcadeMachine();
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
  
  public static ArcadeMachine playGameHP() {
    int size = 3;
    Board board = new TicTacToeBoard(size);
    List<Opponent> opponents = new ArrayList<>();
    opponents.add(new HumanPlayer(Marker.X));
    opponents.add(new HumanPlayer(Marker.O));
    machine = new ArcadeMachine();
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    return machine;
  }
}
