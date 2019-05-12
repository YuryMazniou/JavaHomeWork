package by.it.mazniou.cash_machine_atm.command;

import by.it.mazniou.cash_machine_atm.CashMachine;
import by.it.mazniou.cash_machine_atm.ConsoleHelper;
import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res= PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer=ConsoleHelper.readString();
        if(answer.toLowerCase().equals("y"))ConsoleHelper.writeMessage(res.getString("thank.message"));//ConsoleHelper.writeMessage("GoodBye,See you soon!!!");
    }
}
