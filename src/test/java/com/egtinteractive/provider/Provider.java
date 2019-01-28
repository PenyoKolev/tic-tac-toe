package com.egtinteractive.provider;

import com.egtinteractive.io.ConsoleIO;
import com.egtinteractive.machine.ArcadeMachine;

public class Provider {

  public static Object[][] arcadeMachine() {
    return new Object[][] {{new ArcadeMachine(new ConsoleIO())}};
  }
}
