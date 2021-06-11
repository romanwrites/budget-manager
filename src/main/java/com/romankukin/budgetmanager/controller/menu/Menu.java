package com.romankukin.budgetmanager.controller.menu;

import com.romankukin.budgetmanager.controller.Handler;

public interface Menu extends Handler {

  static String createMenu(String... strings) {
    return String.join(System.lineSeparator(), strings);
  }

  static void printMenu(String menuPrint) {
    System.out.println(menuPrint);
  }


  static void printBadInputMessage() {
    System.out.println("Bad input, try again");
  }
}
