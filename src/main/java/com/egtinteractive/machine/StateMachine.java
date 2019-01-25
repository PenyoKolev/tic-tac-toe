package com.egtinteractive.machine;

import com.egtinteractive.game.Game;

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
    public Game selectGame(ArcadeMachine machine, Game game) {
      if (machine.getBalance() < game.getPrice()) {
        System.out.printf(
            "The price of %s is %d.\nPlease add %d.\n",
            game, game.getPrice(), game.getPrice() - machine.getBalance());
        machine.setState(SELECT_GAME);
      } else {
        machine.setGame(game);
        machine.setState(PLAY_GAME);
      }
      return game;
    }
  },

  PLAY_GAME {

    @Override
    public void playGame(ArcadeMachine machine) {
      System.out.println("The balance here is: " + machine.getGame().getPrice() ); 

      boolean isOver = machine.getGame().startGame(machine.getGame());
      if (isOver) {
        machine.setState(SELECT_GAME);
      }
    }
  };

  public int putCoins(ArcadeMachine machine, int coins) {
    throw new IllegalStateException("Coins are not accepted in current state!");
  }

  public Game selectGame(ArcadeMachine machine, Game game) {
    throw new IllegalStateException("Cannot select game in current state!");
  }

  public void playGame(ArcadeMachine machine) {
    throw new IllegalStateException("Cannot play in current state!");
  }
}
