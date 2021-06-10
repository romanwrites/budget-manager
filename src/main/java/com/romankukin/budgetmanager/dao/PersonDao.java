package com.romankukin.budgetmanager.dao;

import java.util.ArrayList;
import java.util.List;
import com.romankukin.budgetmanager.model.Person;

public class PersonDao {

  private final List<Person> people;

  public PersonDao() {
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
}
