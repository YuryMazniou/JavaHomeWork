package by.it.mazniou.cash_machine_atm;


import by.it.mazniou.cash_machine_atm.command.CommandExecutor;
import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH=CashMachine.class.getPackage().getName()+".resources.";
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation=null;
            do {
                operation=ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation!=Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
