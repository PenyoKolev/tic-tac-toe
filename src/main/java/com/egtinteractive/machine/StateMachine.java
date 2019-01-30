package com.egtinteractive.machine;

import com.egtinteractive.game.Game;

public enum StateMachine {
  SELECT_GAME {

    @Override
    public int putCoins(final ArcadeMachine machine, final int coins) {
      if (coins < 0) {
        machine.getIo().write("Negative coins are not accepted!");
        machine.setState(SELECT_GAME);
        return coins;
      }
      machine.setBalance(machine.getBalance() + coins);

      /*
       * [WARNING] author ivailozd
       *
       * Why is the input returned?
       *
       */
      return coins;
    }

    @Override
    public Game selectGame(final ArcadeMachine machine, final Game game) {
      if (machine.getBalance() < game.getPrice()) {
        machine
            .getIo()
            .write(
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
      return game;
    }
  },

  PLAY_GAME {

    @Override
    public void playGame(final ArcadeMachine machine) {
      boolean isOver = machine.getGame().startGame(machine.getGame(), machine.getIo());
      if (isOver) {
        machine.setState(SELECT_GAME);
      }
    }
  };

  public int putCoins(final ArcadeMachine machine, final int coins) {
    throw new IllegalStateException("Coins are not accepted in current state!");
  }

  public Game selectGame(final ArcadeMachine machine, final Game game) {
    throw new IllegalStateException("Cannot select game in current state!");
  }

  public void playGame(final ArcadeMachine machine) {
    throw new IllegalStateException("Cannot play in current state!");
  }
}
