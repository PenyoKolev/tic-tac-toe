package com.egtinteractive.io;

import java.util.Scanner;

public class ConsoleIO implements InputOutput {
  private final Scanner scanner;

  public ConsoleIO() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public void close() throws Exception {
    scanner.close();
  }

  @Override
  public void write(final String string) {
    System.out.println(string);
  }

  @Override
  public void writeSameLine(final String string) {
    System.out.print(string);
  }

  @Override
  public String read() {
    return scanner.nextLine();
  }

  @Override
  public int readNextInt() {
    return scanner.nextInt();
  }
}
