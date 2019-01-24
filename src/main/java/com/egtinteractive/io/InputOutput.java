package com.egtinteractive.io;

public interface InputOutput extends AutoCloseable {
  
  public void whrite(String string);
  
  public String read();
}
