package com.romankukin.budgetmanager.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.romankukin.budgetmanager.model.Person;

public class BudgetDao {

  private final List<Person> people;
  private BigDecimal balance;
  private BigDecimal total;

  public BudgetDao() {
    this.people = new ArrayList<>();
  }

  public void addPerson(Person person) {
    people.add(person);
  }

  public List<Person> getAllPeople() {
    return people;
  }

  public void removePerson(Person person) {
    people.remove(person);
  }

  public void removeAllPeople() {
    people.clear();
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public BigDecimal getTotal() {
    return total;
  }
}
