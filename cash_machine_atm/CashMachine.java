package by.it.mazniou.cash_machine_atm;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation=null;
            do {
                operation=ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation!=Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("GoodBye,See you soon!!!");
        }
    }
}
