package com.egtinteractive.tests;

import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PutCoinsTest {

  @DataProvider(name = "arcadeMachineConsoleIO")
  public Object[][] getMachine() {
    return Provider.arcadeMachineConsoleIO();
  }

  @Test(dataProvider = "arcadeMachineConsoleIO")
  public void putCoinsShouldAddCoinsToBalance(final ArcadeMachine machine) {
    // Arrange
    final int oldBalance = machine.getBalance();

    // Act
    final int coins = 10;
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getBalance(), oldBalance + coins);
  }

  @Test(dataProvider = "arcadeMachineConsoleIO")
  public void putCoinsShouldNotAcceptNegativeCoins(final ArcadeMachine machine) {
    // Arrange
    final int oldBalance = machine.getBalance();

    // Act
    final int coins = -10;
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getBalance(), oldBalance);
  }

  @Test(dataProvider = "arcadeMachineConsoleIO", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    // Act
    machine.playGame();
  }
}
