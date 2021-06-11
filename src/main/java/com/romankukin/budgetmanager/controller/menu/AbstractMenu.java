package com.romankukin.budgetmanager.controller.menu;

import com.romankukin.budgetmanager.controller.BeanContainer;
import java.util.Scanner;

public abstract class AbstractMenu {
  protected final BeanContainer beanContainer;
  protected final Scanner scanner;

  public AbstractMenu(Scanner scanner, BeanContainer beanContainer) {
    this.scanner = scanner;
    this.beanContainer = beanContainer;
  }

  abstract void mapOptionsToCommands();
}
