package com.romankukin.budgetmanager;

import com.romankukin.budgetmanager.Main.Category;
import com.romankukin.budgetmanager.Main.Purchase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

//todo
// menu class
// printmenu items to print String[]
// save to json
// remove ALL category
// make scanner a field?

interface SortingAlgorithm {
  public abstract void sort();
}

class SortAllAlgorithm implements SortingAlgorithm {

  List<Purchase> list;

  SortAllAlgorithm(Map<Category, List<Purchase>> map) {
    this.list = new ArrayList<>();
    for (var m : map.entrySet()) {
      list.addAll(m.getValue());
    }
    list.sort(Comparator.reverseOrder());
  }

  @Override
  public void sort() {
    double total = 0;

    System.out.println("All:");
    for (Purchase purchase : list) {
      total += purchase.getUnitPrice();
      System.out.println(purchase);
    }
    System.out.println(String.format("Total: $%.2f", total));
  }
}

class SortCertainTypeAlgorithm implements SortingAlgorithm {

  private final Map<Category, List<Purchase>> map;
  private final Scanner scanner;

  public SortCertainTypeAlgorithm(Map<Category, List<Purchase>> map, Scanner scanner) {
    this.map = map;
    this.scanner = scanner;
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
      return ;
    }

    double sum = 0;
    System.out.println(category + ":");
    for (Purchase purchase : list) {
      sum += purchase.getUnitPrice();
      System.out.println(purchase);
    }
    System.out.println(String.format("Total sum: $%.2f", sum));
    return ;
  }

  private void printMenu() {
    System.out.println("Choose the type of purchase" + System.lineSeparator()
        + "1) Food" + System.lineSeparator()
        + "2) Clothes" + System.lineSeparator()
        + "3) Entertainment" + System.lineSeparator()
        + "4) Other");
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

class SortByTypeAlgorithm implements SortingAlgorithm {
  Map<Category, List<Purchase>> map;

  public SortByTypeAlgorithm(
      Map<Category, List<Purchase>> map) {
    this.map = map;
  }

  class TypeOfPurchase implements Comparable<TypeOfPurchase> {
    String type;
    double sum;

    public TypeOfPurchase(String type, double sum) {
      this.type = type;
      this.sum = sum;
    }

    public String getType() {
      return type;
    }

    public double getSum() {
      return sum;
    }

    @Override
    public String toString() {
      return String.format("%s - $%.2f", type, sum);
    }

    @Override
    public int compareTo(TypeOfPurchase typeOfPurchase) {
      return Double.compare(sum, typeOfPurchase.sum);
    }
  }

  @Override
  public void sort() {
    List<TypeOfPurchase> list = new ArrayList<>();

    double totalSum = 0;

    for (Category category : Category.values()) {
      if (category == Category.ALL) {
        continue;
      }
      List<Purchase> purchases = map.getOrDefault(category, Collections.emptyList());
      double sum = 0;
      for (Purchase purchase : purchases) {
        sum += purchase.getUnitPrice();
      }
      totalSum += sum;
      list.add(new TypeOfPurchase(category.toString(), sum));
    }
    list.sort(Comparator.reverseOrder());

    System.out.println("Types:");
    for (TypeOfPurchase typeOfPurchase : list) {
      System.out.println(typeOfPurchase);
    }
    System.out.println("Total sum: " + totalSum);
  }
}

class Sorter {
  SortingAlgorithm sortingAlgorithm;

  public Sorter(SortingAlgorithm sortingAlgorithm) {
    this.sortingAlgorithm = sortingAlgorithm;
  }

  public Sorter() {
    this.sortingAlgorithm = null;
  }

  public void performSort() {
    if (sortingAlgorithm != null) {
      sortingAlgorithm.sort();
    }
  }
}

public class Main {

  private static final String FILENAME = "purchases.txt";

  enum Category {
    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other"),
    ALL("All");

    String category;

    Category(String category) {
      this.category = category;
    }

    @Override
    public String toString() {
      return category;
    }
  }

  class Purchase implements Comparable<Purchase> {
    private String unit;
    private double unitPrice;
    private Category category;

    public Purchase(Category category, String unit, double unitPrice) {
      this.category = category;
      this.unit = unit;
      this.unitPrice = unitPrice;
    }

    public String getUnit() {
      return unit;
    }

    public void setUnit(String unit) {
      this.unit = unit;
    }

    public double getUnitPrice() {
      return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
      this.unitPrice = unitPrice;
    }

    public Category getCategory() {
      return category;
    }

    public void setCategory(Category category) {
      this.category = category;
    }

    @Override
    public String toString() {
      return String.format("%s $%.2f", this.unit, this.unitPrice);
    }


    @Override
    public int compareTo(Purchase purchase) {
      int comp = Double.compare(this.unitPrice, purchase.unitPrice);
      if (comp == 0) {
        return this.unit.compareTo(purchase.unit);
      }
      return comp;
    }
  }

  private final Map<Category, List<Purchase>> map;
  private double total;
  private double balance;

  Main() {
    this.map = new HashMap<>();
    this.total = 0;
    this.balance = 0;
  }

  private void addIncome(Scanner scanner) {
    System.out.println("Enter income:");
    while (true) {
      try {
        double income = scanner.nextDouble();
        balance += income;
        System.out.println("Income was added!");
        scanner.nextLine();
        break;
      } catch (InputMismatchException e) {
        System.out.println("Enter only numbers");
      }
    }

  }

  private void showListOfPurchases(Category category) {
    if (category == Category.ALL) {
      System.out.println(category + ":");
      for (var entry : map.entrySet()) {
        for (Purchase purchase : entry.getValue()) {
          System.out.println(purchase);
        }
      }
      System.out.println(String.format("Total: $%.2f", total));
    } else {
      List<Purchase> list = map.getOrDefault(category, Collections.emptyList());
      System.out.println(category + ":");
      if (list.isEmpty()) {
        System.out.println("The purchase list is empty!");
      } else {
        double categoryTotal = 0;
        for (Purchase purchase : list) {
          categoryTotal += purchase.getUnitPrice();
          System.out.println(purchase);
        }
        System.out.println(String.format("Total: $%.2f", categoryTotal));
      }
    }

  }

  private void showListPurchasesMenu(Scanner scanner) {
    if (map.isEmpty()) {
      System.out.println("The purchase list is empty!");
      return;
    }

    while (true) {
      printListPurchasesMenu();
      String read = scanner.nextLine();
      if (!read.matches("^[1-6]$")) {
        System.out.println("Enter only numbers from 1 to 6");
        continue;
      }
      System.out.println();
      switch (Integer.parseInt(read)) {
        case 1:
          showListOfPurchases(Category.FOOD);
          break;
        case 2:
          showListOfPurchases(Category.CLOTHES);
          break;
        case 3:
          showListOfPurchases(Category.ENTERTAINMENT);
          break;
        case 4:
          showListOfPurchases(Category.OTHER);
          break;
        case 5:
          showListOfPurchases(Category.ALL);
          break;
        case 6:
          return ;
      }
      System.out.println();
    }
  }

  private void showBalance() {
    System.out.println(String.format("Balance: $%.2f", balance));
  }

  private void printMainMenu() {
    System.out.println("Choose your action:" + System.lineSeparator()
        + "1) Add income" + System.lineSeparator()
        + "2) Add purchase" + System.lineSeparator()
        + "3) Show list of purchases" + System.lineSeparator()
        + "4) Balance" + System.lineSeparator()
        + "5) Save" + System.lineSeparator()
        + "6) Load" + System.lineSeparator()
        + "7) Analyze (Sort)" + System.lineSeparator()
        + "0) Exit");
  }

  private void printListPurchasesMenu() {
    System.out.println("Choose the type of purchases" + System.lineSeparator()
        + "1) Food" + System.lineSeparator()
        + "2) Clothes" + System.lineSeparator()
        + "3) Entertainment" + System.lineSeparator()
        + "4) Other" + System.lineSeparator()
        + "5) All" + System.lineSeparator()
        + "6) Back");
  }

  private void printPurchaseMenu() {
    System.out.println("Choose the type of purchase" + System.lineSeparator()
        + "1) Food" + System.lineSeparator()
        + "2) Clothes" + System.lineSeparator()
        + "3) Entertainment" + System.lineSeparator()
        + "4) Other" + System.lineSeparator()
        + "5) Back");
  }

  private void addPurchase(Category category, Scanner scanner) {
    System.out.println("Enter purchase name:");
    String unit = scanner.nextLine();
    while (true) {
      System.out.println("Enter its price:");
      String price = scanner.nextLine();
      if (!price.matches("^\\d+(\\.\\d+)?$")) {
        System.out.println("Enter only numbers");
        continue;
      }
      double priceUnit = Double.parseDouble(price);
      List<Purchase> list;
      if (map.containsKey(category)) {
        list = map.get(category);
        list.add(new Purchase(category, unit, priceUnit));
      } else {
        list = new ArrayList<>();
        list.add(new Purchase(category, unit, priceUnit));
        map.put(category, list);
      }
      total += priceUnit;
      balance -= priceUnit;
      System.out.println("Purchase was added!");
      break;
    }
    System.out.println();
  }

  private void showPurchaseMenu(Scanner scanner) {
    while (true) {
      printPurchaseMenu();
      String read = scanner.nextLine();
      if (!read.matches("^[1-5]$")) {
        System.out.println("Enter only numbers from 1 to 5");
        continue;
      }
      System.out.println();
      switch (Integer.parseInt(read)) {
        case 1:
          addPurchase(Category.FOOD, scanner);
          break;
        case 2:
          addPurchase(Category.CLOTHES, scanner);
          break;
        case 3:
          addPurchase(Category.ENTERTAINMENT, scanner);
          break;
        case 4:
          addPurchase(Category.OTHER, scanner);
          break;
        case 5:
          return;
      }
    }
  }

  private void writeToFile(File file) {
    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write("Balance=" + balance + System.lineSeparator());
      for (Category category : Category.values()) {
        List<Purchase> list = map.getOrDefault(category, Collections.emptyList());
        fileWriter.write(category + " " + list.size() + System.lineSeparator());

        for (Purchase purchase : list) {
          fileWriter.write(purchase.getUnit()
              + "="
              + purchase.getUnitPrice()
              + System.lineSeparator());
        }
      }
    } catch (IOException e) {
      System.out.println("Couldn't write to: " + FILENAME);
    }
  }

  private void save() {
    try {
      File file = new File(FILENAME);
      if (file.exists()) {
        file.delete();
      }
      boolean isCreated = file.createNewFile();
      if (isCreated) {
        writeToFile(file);
        System.out.println("Purchases were saved!");
      }
    } catch (IOException e) {
      System.out.println("Error creating file");
    }
  }

  private void parceUnitFromArray(Category category, List<Purchase> list, String[] rec) {
    double unitPrice = Double.parseDouble(rec[1]);
    list.add(new Purchase(category, rec[0], unitPrice));
    total += unitPrice;
  }

  private void load() {
    try {
      File file = new File(FILENAME);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      map.clear();

      String line = bufferedReader.readLine();

      String[] s = line.split("=");
      balance = Double.parseDouble(s[1]);

      while ((line = bufferedReader.readLine()) != null) {
        s = line.split(" ");

        int count = Integer.parseInt(s[1]);
        if (count == 0) {
          continue;
        }
        Category category = Category.valueOf(s[0].toUpperCase(Locale.ROOT));
        List<Purchase> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
          String[] rec = bufferedReader.readLine().split("=");
          parceUnitFromArray(category, list, rec);
        }
        map.put(category, list);
      }

      bufferedReader.close();
      fileReader.close();

      System.out.println("Purchases were loaded!");
    } catch (IOException e) {
      System.out.println("Couldn't open the file: " + FILENAME);
    } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
      System.out.println("Not valid file content");
    }
  }

  private boolean sortMenuOptions(Scanner scanner, String read) {
    Sorter sorter = null;

    switch (Integer.parseInt(read)) {
      case 1:
        if (map.isEmpty()) {
          System.out.println("The purchase list is empty!" + System.lineSeparator());
          return true;
        }
        sorter = new Sorter(new SortAllAlgorithm(map));
        break;
      case 2:
        sorter = new Sorter(new SortByTypeAlgorithm(map));
        break;
      case 3:
        sorter = new Sorter(new SortCertainTypeAlgorithm(map, scanner));
        break;
      case 4:
        return false;
      default:
        sorter = new Sorter();
    }
    if (sorter != null) {
      sorter.performSort();
    }
    System.out.println();
    return true;
  }

  private void printSortMenu() {
    System.out.println("1) Sort all purchases" + System.lineSeparator()
        + "2) Sort by type" + System.lineSeparator()
        + "3) Sort certain type" + System.lineSeparator()
        + "4) Back");
  }

  private void sort(Scanner scanner) {
    boolean isRunning = true;

    while (isRunning) {
      printSortMenu();
      String read = scanner.nextLine();
      if (!read.matches("^[1-4]$")) {
        System.out.println("Enter only numbers from 1 to 4");
        continue;
      }
      System.out.println();
      isRunning = sortMenuOptions(scanner, read);
    }
  }

  private boolean mainMenuOptions(Scanner scanner, String read) {
    switch (Integer.parseInt(read)) {
      case 1:
        addIncome(scanner);
        break;
      case 2:
        showPurchaseMenu(scanner);
        return true;
      case 3:
        showListPurchasesMenu(scanner);
        return true;
      case 4:
        showBalance();
        break;
      case 5:
        save();
        break;
      case 6:
        load();
        break;
      case 7:
        sort(scanner);
        break;
      case 0:
        System.out.println("Bye!");
        return false;
    }
    System.out.println();
    return true;
  }

  private void showMainMenu() {
    try(Scanner scanner = new Scanner(System.in)) {
      boolean isRunning = true;
      while (isRunning) {
        printMainMenu();
        String read = scanner.nextLine();
        if (!read.matches("^[0-7]$")) {
          System.out.println("Enter only numbers");
          continue;
        }
        System.out.println();
        isRunning = mainMenuOptions(scanner, read);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  public static void main(String[] args) throws FileNotFoundException {
    Main main = new Main();
    main.showMainMenu();
  }
}
