package com.egtinteractive.gametest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class WinByRowTest {

  @DataProvider(name = "playGameHPWinByRow")
  public Object[][] machine() {
    return Provider.playGameHPWinByRow();
  }
  

  @Test(dataProvider = "playGameHPWinByRow")
  public void playGameWinByRow(ArcadeMachine machine) {
    // Act
    machine.playGame();
    
    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
    
  }
}
