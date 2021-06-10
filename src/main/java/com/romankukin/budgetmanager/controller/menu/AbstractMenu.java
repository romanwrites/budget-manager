package com.romankukin.budgetmanager.controller.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.romankukin.budgetmanager.controller.Handler;

public abstract class AbstractMenu implements Menu {
  protected final Map<String, Handler> handlers;
  protected final Scanner scanner;

  public AbstractMenu(Scanner scanner, Handler... handlers) {
    this.scanner = scanner;
    this.handlers = new HashMap<>();
    for (Handler handler : handlers) {
      this.handlers.put(handler.getCommandName(), handler);
    }
  }

  protected void printBadInputMessage() {
    System.out.println("Bad input, try again");
  }

  protected abstract void printMenu();
}
