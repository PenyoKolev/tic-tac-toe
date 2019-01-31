package com.egtinteractive;

import com.egtinteractive.board.Marker;
import com.egtinteractive.game.Order;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.Machine;

public class App {

  public static void main(String[] args) {      //TODO fix input output parameter
    InputOutput io = new ConsoleIO();
    Marker marker = Marker.O;
    Machine machine = new ArcadeMachine(io);
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(io, marker, Order.PLAYER_ONE_FIRST));
    machine.playGame();
    
  }
}
