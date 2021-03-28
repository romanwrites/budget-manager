package com.romankukin.budgetmanager;

class AccountBalanceCommand implements Command {

  Account account;

  public AccountBalanceCommand(Account account) {
    this.account = account;
  }

  @Override
  public void execute() {
    account.balance();
  }
}
