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
  }

  @Override
  public void write(String string) {
    System.out.println(string);
  }

  @Override
  public String read() {
    return "";
  }

  @Override
  public int readNextInt() {
    return ThreadLocalRandom.current().nextInt(0, 9);
  }

  @Override
  public void writeSameLine(String string) {
    // TODO Auto-generated method stub
    
  }
}
