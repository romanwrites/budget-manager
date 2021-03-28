package com.romankukin.budgetmanager;

public class AccountAddIncomeCommand implements Command {

  Account account;

  public AccountAddIncomeCommand(Account account) {
    this.account = account;
  }

  @Override
  public void execute() {
    account.addIncome();
  }
}
