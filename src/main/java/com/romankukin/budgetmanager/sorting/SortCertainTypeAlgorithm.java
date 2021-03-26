package com.romankukin.budgetmanager.sorting;

import com.romankukin.budgetmanager.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

interface Optionable {

  Option[] createOptions();
}

abstract class Menu implements Optionable {

  protected final Scanner scanner;
  protected Option[] options;
  private final String prompt;
  Pattern pattern;

  protected Menu(String prompt, String pattern, Scanner scanner) {
    this.scanner = scanner;
    this.options = createOptions();
    this.prompt = prompt;
    this.pattern = Pattern.compile(pattern);
  }

  private void outputOptions() {
    StringBuilder output = new StringBuilder(prompt + System.lineSeparator());
    for (Option option : options) {
      output.append(option).append(System.lineSeparator());
    }

    System.out.println(output.toString());
  }

  public void runMenu() {
    boolean isRunning = true;

    while (isRunning) {
      outputOptions();

      String read = scanner.nextLine();
      if (!pattern.matcher(read).matches()) {
        System.out.println("Enter only valid numbers");
        continue;
      }
      System.out.println();

      processOption(read);
      isRunning = false;
    }
  }

  protected abstract void processOption(String read);


}

class SortTypeMenu extends Menu {

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
    switch (Integer.parseInt(read)) {
      case 1 -> performSort(Category.FOOD);
      case 2 -> performSort(Category.CLOTHES);
      case 3 -> performSort(Category.ENTERTAINMENT);
      case 4 -> performSort(Category.OTHER);
    }
    System.out.println();
  }
}

public class SortCertainTypeAlgorithm implements SortingAlgorithm {

  private final Map<Category, List<Purchase>> map;
  private final Scanner scanner;

  public SortCertainTypeAlgorithm(Map<Category, List<Purchase>> map, Scanner scanner) {
    this.map = map;
    this.scanner = scanner;
  }

  private void printMenu() {
    System.out.println("Choose the type of purchase" + System.lineSeparator()
        + "1) Food" + System.lineSeparator()
        + "2) Clothes" + System.lineSeparator()
        + "3) Entertainment" + System.lineSeparator()
        + "4) Other");
  }

  private void menuOptions(String read) {
    switch (Integer.parseInt(read)) {
      case 1:
        performSort(Category.FOOD);
        break;
      case 2:
        performSort(Category.CLOTHES);
        break;
      case 3:
        performSort(Category.ENTERTAINMENT);
        break;
      case 4:
        performSort(Category.OTHER);
        break;
    }
    System.out.println();
  }

  private void performSort(Category category) {
    List<Purchase> list = map.getOrDefault(category, Collections.emptyList());
    list.sort(Comparator.reverseOrder());

    if (list.isEmpty()) {
      System.out.println("The purchase list is empty!");
      return;
    }

    double sum = 0;
    System.out.println(category + ":");
    for (Purchase purchase : list) {
      sum += purchase.getUnitPrice();
      System.out.println(purchase);
    }
    System.out.println(String.format("Total sum: $%.2f", sum));
  }

  @Override
  public void sort() {
    boolean isRunning = true;

    while (isRunning) {
      printMenu();
      String read = scanner.nextLine();
      if (!read.matches("^[1-4]$")) {
        System.out.println("Enter only numbers");
        continue;
      }
      System.out.println();
      menuOptions(read);
      isRunning = false;
    }
  }
}
