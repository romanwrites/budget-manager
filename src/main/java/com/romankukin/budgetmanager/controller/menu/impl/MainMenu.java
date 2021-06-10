package com.romankukin.budgetmanager.controller.menu.impl;

import java.util.Scanner;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.controller.menu.AbstractMenu;

public class MainMenu extends AbstractMenu {

  private static final String MENU_NAME = "Main menu";
  private Handler handler;

  public MainMenu(Scanner scanner, Handler... handlers) {
    super(scanner, handlers);
  }

  private void setHandler(Handler handler) {
    this.handler = handler;
  }

  @Override
  public void handle() {
    while (true) {
      printMenu();
      String input = scanner.next();
      switch (input) {
        case "1": {
          setHandler(handlers.get("add"));
          break;
        }
        case "2": {
          setHandler(handlers.get("showAll"));
          break;
        }
        case "0": {
          System.out.println("Bye!");
          return;
        }
        default: {
          handler = null;
          printBadInputMessage();
        }
      }
      if (handler != null) {
        handler.handle();
      }
      System.out.println();
    }
  }

  @Override
  protected void printMenu() {
    System.out.println(String.join(System.lineSeparator(),
        "1. Add person",
        "2. Show all",
        "0. Exit"));
  }

  @Override
  public String getCommandName() {
    return MENU_NAME;
  }
}
