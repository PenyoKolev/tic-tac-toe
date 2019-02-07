package com.egtinteractive.io;

import java.util.Scanner;

public class FakeIO implements IO {
  private final Scanner scanner;
  private String[] input;

  public FakeIO(String[] input) {
    this.setInput(input);
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
    return "";
  }

  public String[] getInput() {
    return input;
  }

  public void setInput(String[] input) {
    this.input = input;
  }
}
