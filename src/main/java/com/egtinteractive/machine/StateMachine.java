package com.egtinteractive.machine;

import com.egtinteractive.game.Game;

public enum StateMachine {
  SELECT_GAME {

    @Override
    public void putCoins(final ArcadeMachine machine, final int coins) {
      if (coins < 0) {
        machine.getIO().write("Negative coins are not accepted!");
        machine.setState(SELECT_GAME);
        return;
      }
      machine.setBalance(machine.getBalance() + coins);
    }

    @Override
    public void selectGame(final ArcadeMachine machine, final Game game) {
      if (machine.getBalance() < game.getPrice()) {
        machine.getIO().write(
                "The price of "
                    + game
                    + "is "
                    + game.getPrice()
                    + "\nPlease add "
                    + (game.getPrice() - machine.getBalance())
                    + "\n");
        machine.setState(SELECT_GAME);
      } else {
        machine.setGame(game);
        machine.setState(PLAY_GAME);
      }
    }
  },

  PLAY_GAME {

    @Override
    public void playGame(final ArcadeMachine machine) {
      boolean isOver = machine.getGame().startGame(machine.getIO());
      if (isOver) {
        machine.setState(SELECT_GAME);
      }
    }
  };

  void putCoins(final ArcadeMachine machine, final int coins) {
    throw new IllegalStateException("Coins are not accepted in current state!");
  }

  void selectGame(final ArcadeMachine machine, final Game game) {
    throw new IllegalStateException("Cannot select game in current state!");
  }

  void playGame(final ArcadeMachine machine) {
    throw new IllegalStateException("Cannot play in current state!");
  }
}
