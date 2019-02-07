package com.egtinteractive;

import java.util.ArrayList;
import java.util.List;
import com.egtinteractive.ai.AITicTacToe;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.IO;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.Machine;
import com.egtinteractive.player.AIPlayer;
import com.egtinteractive.player.HumanPlayer;
import com.egtinteractive.player.Opponent;

public class App {

  public static void main(String[] args) {
    IO io = new ConsoleIO();
    Board board = new TicTacToeBoard(5);
    List<Opponent> opponents = new ArrayList<>();
    //    Opponent player1 = new HumanPlayer(Marker.X);
    //    Opponent player2 = new HumanPlayer(Marker.O);
    Opponent computer1 = new AIPlayer(new AITicTacToe(), Marker.A);
    Opponent computer2 = new AIPlayer(new AITicTacToe(), Marker.B);
    Opponent computer3 = new AIPlayer(new AITicTacToe(), Marker.C);
    Opponent computer4 = new AIPlayer(new AITicTacToe(), Marker.D);
    Opponent computer5 = new AIPlayer(new AITicTacToe(), Marker.E);

    //    opponents.add(player1);
    //    opponents.add(player2);
    opponents.add(computer1);
    opponents.add(computer2);
    opponents.add(computer3);
    opponents.add(computer4);
    opponents.add(computer5);

    Machine machine = new ArcadeMachine(io);
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(board, opponents));
    machine.playGame();
  }
}
