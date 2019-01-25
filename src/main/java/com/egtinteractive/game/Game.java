package com.egtinteractive.game;

import com.egtinteractive.io.InputOutput;

public interface Game {

  public int getPrice();

  public void move(int position);

  public boolean isOver();

  public Result result();

  public void showGame();

  public boolean startGame(Game game, InputOutput io);
  
}
