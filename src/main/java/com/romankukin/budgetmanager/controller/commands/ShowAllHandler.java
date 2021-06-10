package com.romankukin.budgetmanager.controller.commands;

import java.util.Scanner;
import com.romankukin.budgetmanager.controller.BaseHandler;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.service.impl.PersonService;

public class ShowAllHandler extends BaseHandler {

  private final static String COMMAND_NAME = "showAll";
  private final PersonService service;

  public ShowAllHandler(Scanner scanner, PersonService service, Handler next) {
    super(scanner, next);
    this.service = service;
  }

  @Override
  public void handle() {
    service.showAll();
  }

  @Override
  public String getCommandName() {
    return COMMAND_NAME;
  }
}
