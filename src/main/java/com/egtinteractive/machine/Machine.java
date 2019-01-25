package com.egtinteractive.machine;

import com.egtinteractive.game.Game;

public interface Machine {
  
  public int putCoins(int coins);
  
  public Game selectGame(Game game);
    
  public void playGame();  
  
}
