package by.it.mazniou.cash_machine_atm.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Could you want to exit?");
        ConsoleHelper.writeMessage("Enter please Y or N");
        String answer=ConsoleHelper.readString();
        if(answer.toLowerCase().equals("y"))ConsoleHelper.writeMessage("GoodBye,See you soon!!!");
    }
}
