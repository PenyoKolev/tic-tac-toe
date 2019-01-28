package com.egtinteractive.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class GameTest {
  @DataProvider(name = "arcadeMachineConsoleIO")
  public Object[][] getMachine() {
    return Provider.arcadeMachineConsoleIO();
  }

  @Test(dataProvider = "arcadeMachineConsoleIO")
  public void f(final ArcadeMachine machine) {
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
    
  }

}
