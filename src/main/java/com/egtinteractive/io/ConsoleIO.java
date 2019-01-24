package com.egtinteractive.io;

import java.util.Scanner;

public class ConsoleIO implements InputOutput {
  Scanner scanner;

  public ConsoleIO() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public void whrite(String string) {
    System.out.println(string);
  }

  @Override
  public String read() {
    return scanner.nextLine();
  }
  
  @Override
  public void close() {
    scanner.close();
  }
}
