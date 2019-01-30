package com.egtinteractive.game;

import com.egtinteractive.io.InputOutput;

public interface Game {

  public int getPrice();

  public void move(final int position);

  public boolean isOver();

  public Result result();

  public void showGame();

  /*
   * [WARNING] author ivailozd
   *
   * Why is a game object passed here?
   *
   */
  public boolean startGame(final Game game, final InputOutput io);
}
