package com.egtinteractive.io;

public interface InputOutput extends AutoCloseable {
  
  public void write(String string);
  
  public String read();
  
  public int readNextInt();
}
