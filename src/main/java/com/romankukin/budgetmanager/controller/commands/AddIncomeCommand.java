package com.romankukin.budgetmanager.controller.commands;

import java.util.Scanner;
import com.romankukin.budgetmanager.controller.BaseHandler;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.service.impl.BudgetService;

public class AddIncomeCommand extends BaseHandler {

  private final static String COMMAND_NAME = "add";
  private final BudgetService service;

  public AddIncomeCommand(Scanner scanner, BudgetService service, Handler next) {
    super(scanner, next);
    this.service = service;
  }

  @Override
  public void handle() {
    service.addNewPerson(scanner);
  }

  @Override
  public String getCommandName() {
    return COMMAND_NAME;
  }
}
