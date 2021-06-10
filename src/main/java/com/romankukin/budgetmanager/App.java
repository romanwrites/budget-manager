package com.romankukin.budgetmanager;

import com.romankukin.budgetmanager.config.Configurator;
import com.romankukin.budgetmanager.controller.menu.impl.MainMenu;
import java.util.Scanner;

public class App {

  public void run() {
    try (Scanner scanner = new Scanner(System.in)) {
      MainMenu menu = Configurator.configure(scanner);
      menu.handle();
    }
  }
}
