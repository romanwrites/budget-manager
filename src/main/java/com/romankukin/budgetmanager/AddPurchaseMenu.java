package com.romankukin.budgetmanager;

import java.util.Scanner;

class AddPurchaseMenu extends Menu {

  private static final String PROMPT = "Enter purchase name:";
  private static final String PATTERN = "^\\d+(\\.\\d+)?$";

  private final ListOfPurchases purchases;

  protected AddPurchaseMenu(ListOfPurchases purchases, Scanner scanner) {
    super(PROMPT, PATTERN, scanner);
    this.purchases = purchases;
  }

  @Override
  protected void processOption(String read) {
  }

  @Override
  public Option[] createOptions() {
    return new Option[]{
        Option.of("Food", 1),
        Option.of("Clothes", 2),
        Option.of("Entertainment", 3),
        Option.of("Other", 4),
        Option.of("Back", 5),
    };
  }
}
