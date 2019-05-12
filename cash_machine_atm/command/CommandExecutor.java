package by.it.mazniou.cash_machine_atm.command;

import by.it.mazniou.cash_machine_atm.Operation;
import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap=new HashMap<>();

    private CommandExecutor() {}

    static {
        allKnownCommandsMap.put(Operation.LOGIN,new LoginCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT,new DepositCommand());
        allKnownCommandsMap.put(Operation.EXIT,new ExitCommand());
        allKnownCommandsMap.put(Operation.INFO,new InfoCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW,new WithdrawCommand());
    }

    public static final  void execute(Operation operation) throws InterruptOperationException {
        allKnownCommandsMap.get(operation).execute();
    }
}
