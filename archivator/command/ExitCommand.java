package by.it.mazniou.archivator.command;


import by.it.mazniou.archivator.ConsoleHelper;

public class ExitCommand implements Command {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("До встречи!");
    }
}
