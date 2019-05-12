package by.it.mazniou.cash_machine_atm;

import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static BufferedReader bis=new BufferedReader(new InputStreamReader(System.in));
    private static Pattern pattern=Pattern.compile("^\\d+ \\d+$");
    private static Pattern patternCurrency=Pattern.compile("^[a-zA-Z]{3}$");
    private static Pattern patternNumberOperation=Pattern.compile("^[\\d]+$");
    private static ResourceBundle res= PropertyResourceBundle.getBundle(CashMachine.class.getPackage().getName()+".resources.common_en");

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws InterruptOperationException {
        String message=null;
        try {
            message=bis.readLine();
            if(message.toLowerCase().equals("exit")){
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        } catch (IOException e) {}
        return message;
    }
    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String code = readString();
            Matcher m=patternCurrency.matcher(code);
            if(m.find())return code.toUpperCase();
            writeMessage("-------------------------------");
            writeMessage(res.getString("invalid.data"));
            writeMessage("-------------------------------");
        }
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
            String code = readString();
            Matcher m=pattern.matcher(code);
            if(m.find()){
                return code.split(" ");
            }
            writeMessage("-------------------------------");
            writeMessage(res.getString("invalid.data"));
            writeMessage("-------------------------------");
        }
    }
    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(
                    "\t1 - LOGIN\n"+
                    "\t2 - INFO\n" +
                    "\t3 - DEPOSIT\n" +
                    "\t4 - WITHDRAW\n" +
                    "\t5 - EXIT\n");
            writeMessage(res.getString("choose.operation"));
            String number=readString();
            Matcher m=patternNumberOperation.matcher(number);
            Operation operation=null;
            if(m.find()){
                try{
                    operation=Operation.getAllowableOperationByOrdinal(Integer.parseInt(number));
                    if(operation==Operation.INFO){writeMessage(res.getString("operation.INFO"));}
                    if(operation==Operation.DEPOSIT){writeMessage(res.getString("operation.DEPOSIT"));}
                    if(operation==Operation.WITHDRAW){writeMessage(res.getString("operation.WITHDRAW"));}
                    if(operation==Operation.EXIT){writeMessage(res.getString("operation.EXIT"));}
                    return operation;
                }
                catch (IllegalArgumentException e){}
            }
            writeMessage("-------------------------------");
            writeMessage(res.getString("invalid.data"));
            writeMessage("-------------------------------");
        }
    }
    public static void printExitMessage(){
        writeMessage("GoodBye,See you soon!!!");
    }
}
