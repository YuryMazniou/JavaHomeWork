package by.it.mazniou.cash_machine_atm.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WithdrawCommand implements Command {
    private Pattern pattern=Pattern.compile("^\\d+$");
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode= ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator= CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        while (true){
            ConsoleHelper.writeMessage("Please, enter the amount to issue");
            String amount=ConsoleHelper.readString();
            Matcher m=pattern.matcher(amount);
            if(m.find()){
                if(manipulator.isAmountAvailable(Integer.parseInt(amount))){
                    try {
                        Map<Integer, Integer> withdrawAmount=manipulator.withdrawAmount(Integer.parseInt(amount));
                        List<Integer> listWithDrawAmount=new ArrayList<>(withdrawAmount.keySet());
                        Collections.sort(listWithDrawAmount);
                        Collections.reverse(listWithDrawAmount);
                        for (Integer i:listWithDrawAmount) {
                            ConsoleHelper.writeMessage(String.format("\t%d - %d",i,withdrawAmount.get(i)));
                        }
                        ConsoleHelper.writeMessage("the transaction was successful");
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage("There are not enough banknotes to issue");
                    }
                }
            }
            else ConsoleHelper.writeMessage("Error when entering amount!!!");
        }
    }
}
