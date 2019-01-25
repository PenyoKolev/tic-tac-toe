package com.egtinteractive.machine;

import com.egtinteractive.game.Game;
import com.egtinteractive.io.InputOutput;

public enum StateMachine {
  SELECT_GAME {

    @Override
    public int putCoins(ArcadeMachine machine, int coins) {
      if (coins < 0) {
        System.out.println("Negative coins are not accepted!");
        machine.setState(SELECT_GAME);
      }
      machine.setBalance(machine.getBalance() + coins);

      return coins;
    }

    @Override
    public Game selectGame(ArcadeMachine machine, Game game, InputOutput io) {
      if (machine.getBalance() < game.getPrice()) {

        System.out.printf(
            "The price of %s is %d.\nPlease add %d.\n",
            game, game.getPrice(), game.getPrice() - machine.getBalance());
        machine.setState(SELECT_GAME);
      } else {
        machine.setGame(game, io);
        machine.setState(PLAY_GAME);
      }
      return game;
    }
  },

  PLAY_GAME {

    @Override
    public void playGame(ArcadeMachine machine, InputOutput io) {
      boolean isOver = machine.getGame().startGame(machine.getGame(), io);
      if (isOver) {
        machine.setState(SELECT_GAME);
      }
    }
  };

  public int putCoins(ArcadeMachine machine, int coins) {
    throw new IllegalStateException("Coins are not accepted in current state!");
  }

  public Game selectGame(ArcadeMachine machine, Game game, InputOutput io) {
    throw new IllegalStateException("Cannot select game in current state!");
  }

  public void playGame(ArcadeMachine machine, InputOutput io) {
    throw new IllegalStateException("Cannot play in current state!");
  }
}
