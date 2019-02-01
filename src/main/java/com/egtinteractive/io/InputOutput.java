package com.egtinteractive.io;

public interface InputOutput extends AutoCloseable {

  public void write(final String string);

  public void writeSameLine(final String string);

  public String read();

  public int readNextInt();
}
