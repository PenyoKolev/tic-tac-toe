package com.egtinteractive.machine;

import com.egtinteractive.game.Game;
import com.egtinteractive.io.InputOutput;

public interface Machine {
  
  public int putCoins(int coins);
  
  public Game selectGame(Game game, InputOutput io);
    
  public void playGame(InputOutput io);  
  
}
