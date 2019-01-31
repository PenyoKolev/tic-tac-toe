package com.egtinteractive;

import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.io.InputOutput;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.Machine;

public class App {

  public static void main(String[] args) {
    
    Machine machine = new ArcadeMachine(new ConsoleIO());
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame(new ConsoleIO()));
    machine.playGame();
    
  }
}
