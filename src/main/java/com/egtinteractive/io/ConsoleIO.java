package com.egtinteractive.io;

import java.util.Scanner;

public class ConsoleIO implements IO {

  private final Scanner scanner;

  public ConsoleIO() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public void close() {
    scanner.close();
  }

  @Override
  public void write(String string) {
    System.out.println(string);
  }

  @Override
  public String read() {
    return scanner.nextLine();
  }
}
