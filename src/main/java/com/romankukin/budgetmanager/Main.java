package com.romankukin.budgetmanager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

  class Purchase {
    String unit;
    double unitPrice;

    public Purchase(String unit, double unitPrice) {
      this.unit = unit;
      this.unitPrice = unitPrice;
    }

    public String getUnit() {
      return unit;
    }

    public double getUnitPrice() {
      return unitPrice;
    }
  }

  private final List<Purchase> list;
  private double total;
  private double balance;

  Main() {
    this.list = new ArrayList<>();
    this.total = 0;
    this.balance = 0;
  }

  private void read() {
    try(Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        String unit = line.substring(0, line.indexOf('$')).strip();
        double priceUnit = Double.parseDouble(line.substring(line.indexOf('$') + 1));
        list.add(new Purchase(unit, priceUnit));
        total += priceUnit;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void addIncome(Scanner scanner) {
    System.out.println("Enter income:");
    while (true) {
      try {
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
        break;
      } catch (InputMismatchException e) {
        System.out.println("Enter only numbers");
      }
    }

  }

  private void addPurchase(Scanner scanner) {
    System.out.println("Enter purchase name:");
    while (true) {
      String unit = scanner.nextLine();
      System.out.println("Enter its price:");
      String price = scanner.nextLine();
      if (!price.matches("^\\d+(\\.\\d+)?$")) {
        System.out.println("Enter only numbers");
        continue;
      }
      double priceUnit = Double.parseDouble(price);
      list.add(new Purchase(unit, priceUnit));
      total += priceUnit;
      balance -= priceUnit;
      break;
    }
  }

  private void showListOfPurchases() {
    if (list.isEmpty()) {
      System.out.println("The purchase list is empty");
    } else {
      for (Purchase purchase : list) {
        System.out.printf("%s $%.2f", purchase.getUnit(), purchase.getUnitPrice());
        System.out.println();
      }

      System.out.println("Total: $" + total);
    }
  }

  private void showBalance() {
    System.out.println(String.format("Balance: $%.2f", balance));
  }

  private void printMenu() {
    System.out.println("Choose your action:" + System.lineSeparator()
        + "1) Add income" + System.lineSeparator()
        + "2) Add purchase" + System.lineSeparator()
        + "3) Show list of purchases" + System.lineSeparator()
        + "4) Balance" + System.lineSeparator()
        + "0) Exit");
  }

  private void showMenu() {
    try(Scanner scanner = new Scanner(System.in)) {
      while (true) {
        printMenu();
        String read = scanner.nextLine();
        if (!read.matches("^[0-4]$")) {
          System.out.println("Enter only numbers");
          continue;
        }
        System.out.println();
        switch (Integer.parseInt(read)) {
          case 1 -> addIncome(scanner);
          case 2 -> addPurchase(scanner);
          case 3 -> showListOfPurchases();
          case 4 -> showBalance();
          case 0 -> {
            System.out.println("Bye!");
            return;
          }
        }
        System.out.println();
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  public static void main(String[] args) {
    Main main = new Main();
    main.showMenu();
  }
}
