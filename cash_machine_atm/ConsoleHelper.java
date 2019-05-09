package by.it.mazniou.cash_machine_atm;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static BufferedReader bis=new BufferedReader(new InputStreamReader(System.in));
    private static Pattern pattern=Pattern.compile("^\\d+ \\d+$");
    private static Pattern patternCurrency=Pattern.compile("^[a-zA-Z]{3}$");
    private static Pattern patternNumberOperation=Pattern.compile("^[\\d]+$");

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws InterruptOperationException {
        String message=null;
        try {
            message=bis.readLine();
            if(message.toLowerCase().equals("exit"))throw new InterruptOperationException();
        } catch (IOException e) {}
        return message;
    }
    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage("Please,enter currency code:");
            String code = readString();
            Matcher m=patternCurrency.matcher(code);
            if(m.find())return code.toUpperCase();
            writeMessage("-------------------------------");
            writeMessage("Error when entering currency!!!");
            writeMessage("-------------------------------");
        }
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage("Please,enter two positive integers:");
            String code = readString();
            Matcher m=pattern.matcher(code);
            if(m.find()){
                return code.split(" ");
            }
            writeMessage("-------------------------------");
            writeMessage("Error when entering two positive integers!!!");
            writeMessage("-------------------------------");
        }
    }
    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(
                    "\t1 - INFO\n" +
                    "\t2 - DEPOSIT\n" +
                    "\t3 - WITHDRAW\n" +
                    "\t4 - EXIT\n");
            writeMessage("Please, enter number operation:");
            String number=readString();
            Matcher m=patternNumberOperation.matcher(number);
            Operation operation=null;
            if(m.find()){
                try{
                    operation=Operation.getAllowableOperationByOrdinal(Integer.parseInt(number));
                    return operation;
                }
                catch (IllegalArgumentException e){}
            }
            writeMessage("-------------------------------");
            writeMessage("Error when entering number operation!!!");
            writeMessage("-------------------------------");
        }
    }
}
