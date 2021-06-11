package com.romankukin.budgetmanager.controller;

import java.util.HashMap;
import java.util.Map;

public class BeanContainer {
  private final Map<String, Handler> commands;

  public BeanContainer(Handler... handlers) {
    this.commands = new HashMap<>();
    for (Handler handler : handlers) {
      commands.put(handler.getCommandName(), handler);
    }
  }

  public Handler getCommand(String commandName) {
    return commands.get(commandName);
  }
}
