package by.it.mazniou.cash_machine_atm.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command{
    @Override
    public void execute() {
        boolean flag=false;
        Collection<CurrencyManipulator> list=CurrencyManipulatorFactory.getAllCurrencyManipulators();
        ConsoleHelper.writeMessage("---------------------");
        for (CurrencyManipulator manipulator:list) {
            if(manipulator.hasMoney()){
                ConsoleHelper.writeMessage(String.format("%s - %d",manipulator.getCurrencyCode(),manipulator.getTotalAmount()));
                flag=true;
            }
        }
        if(!flag){
            ConsoleHelper.writeMessage("No money available.");
        }
        ConsoleHelper.writeMessage("----------------------");
    }
}
