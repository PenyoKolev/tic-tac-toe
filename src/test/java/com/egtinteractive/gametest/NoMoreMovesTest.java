package com.egtinteractive.gametest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class NoMoreMovesTest {
  @DataProvider(name = "playGameHPNoMoreMoves")
  public Object[][] machine() {
    return Provider.playGameHPNoMoreMoves();
  }
  

  @Test(dataProvider = "playGameHPNoMoreMoves")
  public void playGameNoMoreMovesTest(ArcadeMachine machine) {
    // Act
    machine.playGame();
    
    // Assert
    assertEquals(machine.getStateName(), "SELECT_GAME");
    
  }
}
