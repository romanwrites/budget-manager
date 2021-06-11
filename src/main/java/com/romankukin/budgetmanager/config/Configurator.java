package com.romankukin.budgetmanager.config;

import com.romankukin.budgetmanager.controller.BeanContainer;
import java.util.Scanner;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.controller.commands.AddIncomeCommand;
import com.romankukin.budgetmanager.controller.commands.ShowBalanceCommand;
import com.romankukin.budgetmanager.controller.menu.impl.MainMenu;
import com.romankukin.budgetmanager.dao.BudgetDao;
import com.romankukin.budgetmanager.service.impl.BudgetService;

public class Configurator {

  public static MainMenu configure(Scanner scanner) {
    // init dao
    BudgetDao budgetDao = new BudgetDao();

    // init services
    BudgetService budgetService = new BudgetService(scanner, budgetDao);

    // create commands
    Handler addIncome = new AddIncomeCommand(scanner, budgetService, null);
    Handler showBalance = new ShowBalanceCommand(scanner, budgetService, null);

    // create bean container
    BeanContainer beanContainer = new BeanContainer(addIncome, showBalance);

    // create menus
    MainMenu mainMenu = new MainMenu(scanner, beanContainer);

    return mainMenu;
  }
}
