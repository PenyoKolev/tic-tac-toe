package com.egtinteractive.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.provider.Provider;

public class PlayGameTest {

  @DataProvider(name = "readyToPlay")
  public Object[][] getMachine() {
    return Provider.readyToPlay();
  }

  @Test(dataProvider = "readyToPlay")
  public void f(final ArcadeMachine machine) {
    // Arrange
    machine.playGame();
    
    // Act
    
    

    // Assert

  }
}
