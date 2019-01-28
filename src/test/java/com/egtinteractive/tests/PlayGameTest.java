package com.egtinteractive.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.StateMachine;
import com.egtinteractive.provider.Provider;

public class PlayGameTest {

  @DataProvider(name = "arcadeMachineFakeIO")
  public Object[][] getMachine() {
    return Provider.arcadeMachineFakeIO();
  }

  @Test(dataProvider = "arcadeMachineFakeIO")
  public void playGameWhenFinishGameStateShouldBeSELECT_GAME(final ArcadeMachine machine) {
    // Arrange
    int coins = 10;
    machine.putCoins(coins);
    machine.selectGame(new TicTacToeGame());

    // Act
    machine.playGame();
    while (!machine.getGame().isOver()) {
      machine.getIo().readNextInt();
    }

    // Assert
    assertEquals(machine.getState().toString(), "SELECT_GAME");
  }

  @Test(dataProvider = "arcadeMachineFakeIO", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    // Arrange
    machine.setState(StateMachine.PLAY_GAME);

    // Act
    int coins = 10;
    machine.putCoins(coins);
  }
}
