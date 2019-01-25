package com.egtinteractive;

import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.Machine;

public class App {

  public static void main(String[] args) {
    
    Machine machine = new ArcadeMachine();
    machine.putCoins(40);
    machine.selectGame(new TicTacToeGame());
    machine.playGame();
    
  }
}
