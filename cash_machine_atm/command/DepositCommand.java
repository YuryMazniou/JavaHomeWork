package by.it.mazniou.cash_machine_atm.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode= ConsoleHelper.askCurrencyCode();
        String[]denominationAndCount=ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulator manipulator= CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int denomination=Integer.parseInt(denominationAndCount[0]);
        int count=Integer.parseInt(denominationAndCount[1]);
        manipulator.addAmount(denomination,count);
    }
}
