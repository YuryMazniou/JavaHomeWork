package by.it.mazniou.cash_machine_atm.command;


import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
