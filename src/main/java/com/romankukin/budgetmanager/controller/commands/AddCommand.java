package com.romankukin.budgetmanager.controller.commands;

import java.util.Scanner;
import com.romankukin.budgetmanager.controller.BaseHandler;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.service.impl.PersonService;

public class AddCommand extends BaseHandler {

  private final static String COMMAND_NAME = "add";
  private final PersonService service;

  public AddCommand(Scanner scanner, PersonService service, Handler next) {
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
