package com.egtinteractive.provider;

import com.egtinteractive.machine.ArcadeMachine;

public class Provider {

  public static Object[][] arcadeMachineFakeIO() {
    return new Object[][] {{new ArcadeMachine(new FakeInputIO())}};
  }
  
  public static Object[][] arcadeMachineConsoleIO() {
    return new Object[][] {{new ArcadeMachine(new FakeInputIO())}};
  }
}
