package com.romankukin.budgetmanager.controller;

import java.util.Scanner;

public abstract class BaseHandler implements Handler {

  protected final Scanner scanner;
  protected Handler next;

  public BaseHandler(Scanner scanner, Handler next) {
    this.scanner = scanner;
    this.next = next;
  }

  public Handler setNext(Handler handler) {
    this.next = handler;
    return next;
  }

  @Override
  public String getCommandName() {
    return "undefined command name";
  }
}
