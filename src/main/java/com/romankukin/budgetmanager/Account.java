package com.romankukin.budgetmanager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
  private double balance;
  private final Scanner scanner;

  public Account(Scanner scanner) {
    this.scanner = scanner;
  }

  public void balance() {
    System.out.printf("Balance: $%.2f%n", balance);
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void addIncome() {
    System.out.println("Enter income:");
    while (true) {
      try {
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
        scanner.nextLine();
        break;
      } catch (InputMismatchException e) {
        System.out.println("Enter only numbers");
      }
    }
  }
}
