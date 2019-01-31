package com.egtinteractive.tests;

import org.testng.annotations.Test;
import com.egtinteractive.board.Marker;
import com.egtinteractive.game.Order;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;

public class PutCoinsTest {

  @DataProvider(name = "arcadeMachineFakeIO")
  public Object[][] getMachine() {
    return Provider.arcadeMachineFakeIO();
  }
  
  @Test(dataProvider = "arcadeMachineFakeIO")
  public void putCoinsShouldAddCoinsToBalance(final ArcadeMachine machine) {
    //Arrange
    final int oldBalance = machine.getBalance();
    
    //Act
    final int coins = 10;
    machine.putCoins(coins);
    
    //Assert
    assertEquals(machine.getBalance(), oldBalance + coins);
    
  }
  
  @Test(dataProvider = "arcadeMachineFakeIO")
  public void putCoinsShouldNotAcceptNegativeCoins(final ArcadeMachine machine) {
    //Arrange
    final int oldBalance = machine.getBalance();
    
    //Act
    final int coins = -10;
    machine.putCoins(coins);
    
    //Assert
    assertEquals(machine.getBalance(), oldBalance);
    
  }
  
  @Test(dataProvider = "arcadeMachineFakeIO", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    // Act
    machine.playGame(Order.PLAYER_ONE_FIRST, Marker.X);
  }

  
}
