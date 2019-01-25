package com.egtinteractive.machine;

import com.egtinteractive.game.Game;
import com.egtinteractive.io.InputOutput;

public class ArcadeMachine implements Machine {
  private StateMachine state;
  private int balance;
  private Game game;

  public ArcadeMachine() {
    this.setState(StateMachine.SELECT_GAME);
    this.setBalance(0);
  }

  @Override
  public int putCoins(int coins) {
    return this.state.putCoins(this, coins);
  }

  @Override
  public Game selectGame(Game game, InputOutput io) {
    return this.state.selectGame(this, game, io);
  }

  @Override
  public void playGame(InputOutput io) {
    this.state.playGame(this, io);
  }

  public StateMachine getState() {
    return state;
  }

  public void setState(StateMachine state) {
    this.state = state;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game, InputOutput io) {
    this.game = game;
  }
}
