package com.egtinteractive;

import com.egtinteractive.ai.AI;
import com.egtinteractive.ai.AITicTacToe;
import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.game.Order;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.Machine;
import com.egtinteractive.player.AIPlayer;
import com.egtinteractive.player.Opponent;

public class App {

  public static void main(String[] args) {  
    InputOutput io = new ConsoleIO();
    Board board = new TicTacToeBoard(3);
    Order order = Order.PLAYER_ONE_FIRST;
    AI ai = new AITicTacToe();
    
    Opponent opponent = new AIPlayer(ai, Marker.O);

    Machine machine = new ArcadeMachine(io);
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(io, board, Marker.X, order, opponent));
    machine.playGame();
    
  }
}
