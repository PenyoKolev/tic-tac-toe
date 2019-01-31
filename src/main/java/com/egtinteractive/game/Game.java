package com.egtinteractive.game;

public interface Game {

  public int getPrice();

  public boolean isOver();

  public Result result();

  public void showGame();

  public boolean startGame();
}
