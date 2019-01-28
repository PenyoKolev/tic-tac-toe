package com.egtinteractive.tests;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.game.TicTacToeGame;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class SelectGameTest {
  @DataProvider(name = "arcadeMachine")
  public Object[][] getMachine() {
    return Provider.arcadeMachine();
  }
  
  @Test(dataProvider = "arcadeMachine")
  public void selectGameShouldChangeStateToPLAY_GAME(final ArcadeMachine machine) {
    //Arrange
    machine.putCoins(10);
    
    //Act
    machine.selectGame(new TicTacToeGame());
    
    //Assert
    assertEquals(machine.getState().toString(), "PLAY_GAME");  
  }
  
  @Test(dataProvider = "arcadeMachine")
  public void selectGameShouldNotChangeStateIfCoinsNotEnough(final ArcadeMachine machine) {
    //Arrange
    machine.putCoins(10);
    
    //Act
    machine.selectGame(new TicTacToeGame());
    
    //Assert
    assertEquals(machine.getState().toString(), "PLAY_GAME");  
  }
  
  @Test(dataProvider = "arcadeMachine", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    // Act
    machine.playGame();
  }
}
