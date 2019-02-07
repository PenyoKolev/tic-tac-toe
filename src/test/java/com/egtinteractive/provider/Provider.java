package com.egtinteractive.provider;

public class Provider {
  
  Generator generator = new Generator();

  public static Object[][] selectGame() {
    return new Object[][] {{Generator.selectGame()}};
  }

  public static Object[][] playGameAI() {
    return new Object[][] {{Generator.playGameAI()}};
  }
  
  public static Object[][] playGameHPWinByRow() {
    return new Object[][] {{Generator.playGameHPWinByRow()}};
  }
  
  public static Object[][] playGameHPWinByCol() {
    return new Object[][] {{Generator.playGameHPWinByCol()}};
  }
  
  public static Object[][] playGameHPWinByDiagonal() {
    return new Object[][] {{Generator.playGameHPWinByDiagonal()}};
  }
  
  public static Object[][] playGameHPInvalidMove() {
    return new Object[][] {{Generator.playGameHPInvalidMove()}};
  }
  
  public static Object[][] playGameHPNoMoreMoves() {
    return new Object[][] {{Generator.playGameHPNoMoreMoves()}};
  }
}
