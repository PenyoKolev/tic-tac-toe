package com.egtinteractive.ai;

import java.util.List;

public interface AI {

  public boolean move();
  
  public List<Integer> getFreeCells();
}
