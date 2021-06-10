package com.romankukin.budgetmanager.config;

import java.util.Scanner;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.controller.commands.AddCommand;
import com.romankukin.budgetmanager.controller.commands.ShowAllHandler;
import com.romankukin.budgetmanager.controller.menu.impl.MainMenu;
import com.romankukin.budgetmanager.dao.PersonDao;
import com.romankukin.budgetmanager.service.impl.PersonService;

public class Configurator {

  public static MainMenu configure(Scanner scanner) {
    // init dao
    PersonDao personDao = new PersonDao();

    // init services
    PersonService personService = new PersonService(personDao);

    // create commands
    Handler add = new AddCommand(scanner, personService, null);
    Handler showAll = new ShowAllHandler(scanner, personService, null);

    // create menus
    MainMenu mainMenu = new MainMenu(scanner, add, showAll);

    return mainMenu;
  }
}
