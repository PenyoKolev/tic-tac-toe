package com.egtinteractive.gametest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class CheckInvalidPositionTest {
  @DataProvider(name = "playGameHPInvalidMove")
  public Object[][] machine() {
    return Provider.playGameHPInvalidMove();
  }
  

  @Test(dataProvider = "playGameHPInvalidMove")
  public void InvalidMoveTest(ArcadeMachine machine) {
    // Act
    machine.playGame();
    
    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
    
  }
}
