package com.egtinteractive.gametest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class GameTest {

  @DataProvider(name = "playGameHP")
  public Object[][] machine() {
    return Provider.playGameHP();
  }
  

  @Test(dataProvider = "playGameHP")
  public void playGameWhenFinishShouldChangeStateToSELECT_GAME(ArcadeMachine machine) {
    // Arrange
    
    
    // Act
  

    // Assert

    
  }
}
