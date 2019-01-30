package com.egtinteractive.machine;

import com.egtinteractive.game.Game;
import com.egtinteractive.io.InputOutput;

public class ArcadeMachine implements Machine {
  private StateMachine state;
  private int balance;

  /*
   * [WARNING] author ivailozd
   *
   * Why are these not final fields?
   *
   */       
  private final InputOutput io;
  private Game game;        //TODO check if this should be final after all

  public ArcadeMachine(final InputOutput io) {
    this.io = io;
    this.setState(StateMachine.SELECT_GAME);
    this.setBalance(0);
  }

  @Override
  public void putCoins(final int coins) {
    this.state.putCoins(this, coins);
  }

  @Override
  public void selectGame(final Game game) {
    this.state.selectGame(this, game);
  }

  @Override
  public void playGame() {
    this.state.playGame(this);
  }
  
  public String getStateName() {
    return state.name();
  }

  StateMachine getState() {
    return state;
  }

  void setState(final StateMachine state) {
    this.state = state;
  }

  public int getBalance() {
    return balance;
  }

  void setBalance(final int balance) {
    this.balance = balance;
  }

  public Game getGame() {
    return game;
  }

  void setGame(final Game game) {
    this.game = game;
  }

  public InputOutput getIo() {
    return io;
  }
}
