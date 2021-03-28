package com.romankukin.budgetmanager;

import java.util.Scanner;
import java.util.regex.Pattern;

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
