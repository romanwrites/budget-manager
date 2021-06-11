package com.romankukin.budgetmanager.controller.commands;

import java.util.Scanner;
import com.romankukin.budgetmanager.controller.BaseHandler;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.service.impl.BudgetService;

public class ShowBalanceCommand extends BaseHandler {

  private final static String COMMAND_NAME = "showBalance";
  private final BudgetService service;

  public ShowBalanceCommand(Scanner scanner, BudgetService service, Handler next) {
    super(scanner, next);
    this.service = service;
  }

  @Override
  public void handle() {
    service.showBalance();
  }

  @Override
  public String getCommandName() {
    return COMMAND_NAME;
  }
}
