package by.it.mazniou.cash_machine_atm.command;

import by.it.mazniou.cash_machine_atm.CashMachine;
import by.it.mazniou.cash_machine_atm.ConsoleHelper;
import by.it.mazniou.cash_machine_atm.CurrencyManipulator;
import by.it.mazniou.cash_machine_atm.CurrencyManipulatorFactory;
import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;
import by.it.mazniou.cash_machine_atm.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command {
    //private Pattern pattern=Pattern.compile("^\\d+$");
    private ResourceBundle res=PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));//
        String currencyCode= ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator= CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int sum;
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String s = ConsoleHelper.readString();
            try
            {
                sum = Integer.parseInt(s);
            } catch (NumberFormatException e)
            {
                continue;
            }
            if (sum <= 0)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(sum))
            {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
            try
            {
                manipulator.withdrawAmount(sum);
            } catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currencyCode));
            break;
        }
    }
}
