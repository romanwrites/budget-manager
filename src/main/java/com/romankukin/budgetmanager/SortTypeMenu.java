package com.romankukin.budgetmanager;

import java.util.Scanner;

class SortTypeMenu extends Menu {

//  private void

  private static final String PROMPT = "Choose the type of purchase";
  private static final String PATTERN = "^[1-4]$";

  protected SortTypeMenu(Scanner scanner) {
    super(PROMPT, PATTERN, scanner);
  }

  @Override
  public Option[] createOptions() {
    return new Option[]{
        Option.of("Food", 1),
        Option.of("Clothes", 2),
        Option.of("Entertainment", 3),
        Option.of("Other", 4),
    };
  }

  @Override
  protected void processOption(String read) {
//    switch (Integer.parseInt(read)) {
//      case 1 -> performSort(Category.FOOD);
//      case 2 -> performSort(Category.CLOTHES);
//      case 3 -> performSort(Category.ENTERTAINMENT);
//      case 4 -> performSort(Category.OTHER);
//    }
    System.out.println();
  }
}
