package com.egtinteractive.provider;

public class Provider {
  
  Generator generator = new Generator();

  public static Object[][] selectGame() {
    return new Object[][] {{Generator.selectGame()}};
  }

  public static Object[][] playGameAI() {
    return new Object[][] {{Generator.playGameAI()}};
  }
  
  public static Object[][] playGameHP() {
    return new Object[][] {{Generator.playGameHP()}};
  }

}
