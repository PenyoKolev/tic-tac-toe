package com.egtinteractive.machine;

import com.egtinteractive.game.Game;

public interface Machine {

  public void putCoins(final int coins);

  public void selectGame(final Game game);

  public void playGame();
}
