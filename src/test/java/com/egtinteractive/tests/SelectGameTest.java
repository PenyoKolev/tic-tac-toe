package com.egtinteractive.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.board.Marker;
import com.egtinteractive.game.Order;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class SelectGameTest {
  @DataProvider(name = "arcadeMachineFakeIO")
  public Object[][] getMachine() {
    return Provider.arcadeMachineFakeIO();
  }
  
  @Test(dataProvider = "arcadeMachineFakeIO")
  public void selectGameShouldChangeStateToPLAY_GAME(final ArcadeMachine machine) {
    //Arrange
    machine.putCoins(10);
    
    //Act
    machine.selectGame(new TicTacToeGame(new ConsoleIO(), Marker.X, Order.PLAYER_ONE_FIRST));
    
    //Assert
    assertEquals(machine.getStateName(), "PLAY_GAME");  
  }
  
  @Test(dataProvider = "arcadeMachineFakeIO")
  public void selectGameShouldNotChangeStateIfCoinsNotEnough(final ArcadeMachine machine) {
    //Arrange
    machine.putCoins(8);
    
    //Act
    machine.selectGame(new TicTacToeGame(new ConsoleIO(), Marker.X, Order.PLAYER_ONE_FIRST));
    
    //Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");  
  }
  
  @Test(dataProvider = "arcadeMachineFakeIO", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    // Act
    machine.playGame();
  }
}
