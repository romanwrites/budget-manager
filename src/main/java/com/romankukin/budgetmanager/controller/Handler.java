package com.romankukin.budgetmanager.controller;

public interface Handler {

  void handle();

  String getCommandName();
}
