package com.romankukin.budgetmanager.controller.menu.impl;

import com.romankukin.budgetmanager.controller.BeanContainer;
import com.romankukin.budgetmanager.controller.menu.Menu;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.romankukin.budgetmanager.controller.Handler;
import com.romankukin.budgetmanager.controller.menu.AbstractMenu;

public class MainMenu extends AbstractMenu implements Menu {

  private static final String MENU_NAME = "Main menu";
  private static final String MENU_PRINT = Menu.createMenu(
                                          "Choose your action:",
                                          "1) Add income",
                                          "2) Add purchase",
                                          "3) Show list of purchases",
                                          "4) Balance",
                                          "5) Save",
                                          "6) Load",
                                          "7) Analyze (Sort)",
                                          "0) Exit");

  private final Map<String, Handler> handlerMap;

  private Handler handler;

  public MainMenu(Scanner scanner, BeanContainer beanContainer) {
    super(scanner, beanContainer);
    this.handlerMap = new HashMap<>();
  }

  private void setHandler(Handler handler) {
    this.handler = handler;
  }

  protected String[] getCommandsList() {
    return new String[]{"1", "2", "0"};
  }

  @Override
  public void handle() {
    String[] commandsList = getCommandsList();

    while (true) {
      Menu.printMenu(MENU_PRINT);
      String input = scanner.next();

      for (String command : commandsList) {

      }

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
          Menu.printBadInputMessage();
        }
      }
      if (handler != null) {
        handler.handle();
      }
      System.out.println();
    }
  }

  @Override
  public String getCommandName() {
    return MENU_NAME;
  }
}
