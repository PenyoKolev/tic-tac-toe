package com.egtinteractive.provider;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import com.egtinteractive.io.InputOutput;

public class FakeInputIO implements InputOutput {

  /*
   * [WARNING] author ivailozd
   *
   * It is not used???
   *
   */
  private final Scanner scanner;

  public FakeInputIO() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public void close() throws Exception {
    scanner.close();
    throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

  @Override
  public void write(String string) {
    System.out.println(string);
  }

  @Override
  public String read() {
    return "";
  }

  int playerOneMove = 0;
  
  @Override
  public int readNextInt() {
    return playerOneMove++;
  }

  @Override
  public void writeSameLine(String string) {
    System.out.print(string);
  }
}
