package com.romankukin.budgetmanager;

public enum Category {
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