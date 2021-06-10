package com.romankukin.budgetmanager.service.impl;

import java.util.Scanner;
import com.romankukin.budgetmanager.dao.PersonDao;
import com.romankukin.budgetmanager.model.Person;
import com.romankukin.budgetmanager.service.Service;

public class PersonService implements Service {

  private PersonDao dao;

  public PersonService(PersonDao dao) {
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


}
