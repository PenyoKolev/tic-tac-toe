package com.egtinteractive.gametest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class WinByColTest {

  @DataProvider(name = "playGameHPWinByCol")
  public Object[][] machine() {
    return Provider.playGameHPWinByCol();
  }
  

  @Test(dataProvider = "playGameHPWinByCol")
  public void playGameWinByCol(ArcadeMachine machine) {
    // Act
    machine.playGame();
    
    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
    
  }
}
