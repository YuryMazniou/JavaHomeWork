package by.it.mazniou.cash_machine_atm.command;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
