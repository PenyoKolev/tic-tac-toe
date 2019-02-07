package com.egtinteractive.provider;

import com.egtinteractive.io.IO;

public class FakeIO implements IO {
  
  private String[] indices;
  
  public FakeIO(String[] indices) {
    this.indices = indices;
  }
  
  @Override
  public void close() {
    // TODO Auto-generated method stub
  }

  @Override
  public void write(String string) {
    System.out.println(string);
  }

  int index = 0;
  
  @Override
  public String read() {    
    return indices[index++];
  }
}
