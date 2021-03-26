package com.romankukin.budgetmanager;

public class Option {

  private final String name;
  private final int number;

  private Option(String name, int number) {
    this.name = name;
    this.number = number;
  }

  public String name() {
    return name;
  }

  public int number() {
    return number;
  }

  public static Option of(String name, int number) {
    return new Option(name, number);
  }

  @Override
  public String toString() {
    return number + ") " + name;
  }
}
