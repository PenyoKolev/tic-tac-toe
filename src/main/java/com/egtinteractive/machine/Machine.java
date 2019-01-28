package com.egtinteractive.machine;

import com.egtinteractive.game.Game;

public interface Machine {
  
  public int putCoins(final int coins);
  
  public Game selectGame(final Game game);
    
  public void playGame();  
  
}
