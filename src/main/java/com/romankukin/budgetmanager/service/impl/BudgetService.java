package com.romankukin.budgetmanager.service.impl;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.romankukin.budgetmanager.dao.BudgetDao;
import com.romankukin.budgetmanager.model.Person;
import com.romankukin.budgetmanager.service.Service;

public class BudgetService implements Service {

  private final BudgetDao dao;
  private final Scanner scanner;

  public BudgetService(Scanner scanner, BudgetDao dao) {
    this.scanner = scanner;
    this.dao = dao;
  }

  public void showAll() {
    int i = 0;

    for (Person person : dao.getAllPeople()) {
      System.out.println(++i + " " + person);
    }
  }

  public Person addNewPerson(Scanner scanner) {
    scanner.nextLine();
    System.out.print("Enter first name: ");
    String firstName = scanner.nextLine();
    System.out.print("Enter last name: ");
    String lastName = scanner.nextLine();
    Person person = new Person(firstName, lastName);
    dao.addPerson(person);
    return person;
  }

  public BigDecimal showBalance() {
    return dao.getBalance();
  }

  public BigDecimal addIncome() {
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
