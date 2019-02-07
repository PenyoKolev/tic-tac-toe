package com.egtinteractive.machine;

import com.egtinteractive.game.Game;
import com.egtinteractive.io.IO;

public interface Machine {

  public void putCoins(final int coins);

  public void selectGame(final Game game);

  public void playGame();
  
  public IO getIO();
}
