package com.egtinteractive.machine;

import com.egtinteractive.game.Game;
import com.egtinteractive.io.InputOutput;

public class ArcadeMachine implements Machine {
  private StateMachine state;
  private int balance;
  private Game game;
  private InputOutput io;

  public ArcadeMachine(InputOutput io) {
    this.setState(StateMachine.SELECT_GAME);
    this.setBalance(0);
    this.setIo(io);
  }

  @Override
  public int putCoins(int coins) {
    return this.state.putCoins(this, coins);
  }

  @Override
  public Game selectGame(Game game) {
    return this.state.selectGame(this, game);
  }

  @Override
  public void playGame() {
    this.state.playGame(this);
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

  public void setGame(Game game) {
    this.game = game;
  }

  public InputOutput getIo() {
    return io;
  }

  public void setIo(InputOutput io) {
    this.io = io;
  }
}
