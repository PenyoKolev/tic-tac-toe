package com.egtinteractive.tests;

import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;

public class PutCoinsTest {

  @DataProvider(name = "arcadeMachine")
  public Object[][] getMachine() {
    return Provider.arcadeMachine();
  }
  
  @Test(dataProvider = "arcadeMachine")
  public void putCoinsShouldAddCoinsToBalance(final ArcadeMachine machine) {
    //Arrange
    final int oldBalance = machine.getBalance();
    
    //Act
    final int coins = 10;
    machine.putCoins(coins);
    
    //Assert
    assertEquals(machine.getBalance(), oldBalance + coins);
    
  }
  
  @Test(dataProvider = "arcadeMachine")
  public void putCoinsShouldNotAcceptNegativeCoins(final ArcadeMachine machine) {
    //Arrange
    final int oldBalance = machine.getBalance();
    
    //Act
    final int coins = -10;
    machine.putCoins(coins);
    
    //Assert
    assertEquals(machine.getBalance(), oldBalance);
    
  }
  
  @Test(dataProvider = "arcadeMachine", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    // Act
    machine.playGame();
  }

  
}
