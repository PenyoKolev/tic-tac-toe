package com.egtinteractive.gametest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class WinByDiagonalTest {

  @DataProvider(name = "playGameHPWinByDiagonal")
  public Object[][] machine() {
    return Provider.playGameHPWinByDiagonal();
  }
  

  @Test(dataProvider = "playGameHPWinByDiagonal")
  public void playGameWinByDiagonal(ArcadeMachine machine) {
    // Act
    machine.playGame();
    
    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
    
  }
}