package by.it.mazniou.cash_machine_atm.command;


import by.it.mazniou.cash_machine_atm.CashMachine;
import by.it.mazniou.cash_machine_atm.ConsoleHelper;
import by.it.mazniou.cash_machine_atm.CurrencyManipulator;
import by.it.mazniou.cash_machine_atm.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

class InfoCommand implements Command{
    private ResourceBundle res= PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean flag=false;
        Collection<CurrencyManipulator> list= CurrencyManipulatorFactory.getAllCurrencyManipulators();
        ConsoleHelper.writeMessage("---------------------");
        for (CurrencyManipulator manipulator:list) {
            if(manipulator.hasMoney()){
                ConsoleHelper.writeMessage(String.format("%s - %d",manipulator.getCurrencyCode(),manipulator.getTotalAmount()));
                flag=true;
            }
        }
        if(!flag){
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
        ConsoleHelper.writeMessage("----------------------");
    }
}
