package by.it.mazniou.cash_machine_atm.command;

import by.it.mazniou.cash_machine_atm.CashMachine;
import by.it.mazniou.cash_machine_atm.ConsoleHelper;
import by.it.mazniou.cash_machine_atm.CurrencyManipulator;
import by.it.mazniou.cash_machine_atm.CurrencyManipulatorFactory;
import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res= PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode= ConsoleHelper.askCurrencyCode();
        String[]denominationAndCount=ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulator manipulator= CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int denomination=Integer.parseInt(denominationAndCount[0]);
        int count=Integer.parseInt(denominationAndCount[1]);
        manipulator.addAmount(denomination,count);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),denomination*count,currencyCode));
    }
}
