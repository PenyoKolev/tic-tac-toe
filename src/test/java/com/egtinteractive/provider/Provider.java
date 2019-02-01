package com.egtinteractive.provider;

import com.egtinteractive.board.Board;
import com.egtinteractive.board.Marker;
import com.egtinteractive.board.TicTacToeBoard;
import com.egtinteractive.game.Order;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.Machine;
import com.egtinteractive.player.HumanPlayer;
import com.egtinteractive.player.Opponent;

public class Provider {

  public static Object[][] arcadeMachineFakeIO() {
    return new Object[][] {{new ArcadeMachine(new FakeInputIO())}};
  }

  public static Object[][] arcadeMachineConsoleIO() {
    return new Object[][] {{new ArcadeMachine(new ConsoleIO())}};
  }

  public static Object[][] readyToPlay() {
    InputOutput io = new FakeInputIO();
    Board board = new TicTacToeBoard(3);
    Marker marker = Marker.X;
    Order order = Order.PLAYER_ONE_FIRST;
    Opponent opponent = new HumanPlayer(io);
    Machine machine = new ArcadeMachine(io);
    machine.putCoins(10);
    machine.selectGame(new TicTacToeGame(io, board, marker, order, opponent));
    return new Object[][] {{machine}};
  }
}
