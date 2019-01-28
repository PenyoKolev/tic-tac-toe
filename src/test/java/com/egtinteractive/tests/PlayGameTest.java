package com.egtinteractive.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.ArcadeMachine;
import com.egtinteractive.machine.StateMachine;
import com.egtinteractive.provider.Provider;

public class PlayGameTest {

  @DataProvider(name = "arcadeMachine")
  public Object[][] getMachine() {
    return Provider.arcadeMachine();
  }

  @Test(dataProvider = "arcadeMachine")
  public void playGame(final ArcadeMachine machine) {
    // TODO
    
  }

  @Test(dataProvider = "arcadeMachine", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(ArcadeMachine machine) {
    machine.setState(StateMachine.PLAY_GAME);
    int coins = 10;
    //machine.putCoins(coins);
  }
}
