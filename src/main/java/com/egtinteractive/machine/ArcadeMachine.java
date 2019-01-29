package com.egtinteractive.machine;

import com.egtinteractive.game.Game;
import com.egtinteractive.io.InputOutput;

public class ArcadeMachine implements Machine {
  private StateMachine state;
  private int balance;
  private Game game;
  private InputOutput io;

  public ArcadeMachine(final InputOutput io) {
    this.setState(StateMachine.SELECT_GAME);
    this.setBalance(0);
    this.setIo(io);
  }

  @Override
  public int putCoins(final int coins) {
    return this.state.putCoins(this, coins);
  }

  @Override
  public Game selectGame(final Game game) {
    return this.state.selectGame(this, game);
  }

  @Override
  public void playGame() {
    this.state.playGame(this);
  }

  public StateMachine getState() {
    return state;
  }

  public void setState(final StateMachine state) {
    this.state = state;
  }
  
  public String getStateName() {
    return state.name();
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(final int balance) {
    this.balance = balance;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(final Game game) {
    this.game = game;
  }

  public InputOutput getIo() {
    return io;
  }

  public void setIo(final InputOutput io) {
    this.io = io;
  }
}
