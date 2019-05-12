package by.it.mazniou.cash_machine_atm;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    //1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT;
    public static Operation getAllowableOperationByOrdinal(Integer i){
        Operation[]operations=Operation.values();
        if(i>4||i<2)throw new IllegalArgumentException();
        return operations[i-1];
    }
}
