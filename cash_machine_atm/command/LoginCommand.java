package by.it.mazniou.cash_machine_atm.command;
import by.it.mazniou.cash_machine_atm.CashMachine;
import by.it.mazniou.cash_machine_atm.ConsoleHelper;
import by.it.mazniou.cash_machine_atm.exception.InterruptOperationException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


class LoginCommand implements Command {
    //private Pattern patternCard=Pattern.compile("^[\\d]{12}$");
    //private Pattern patternPin=Pattern.compile("^[\\d]{4}$");
    private ResourceBundle validCreditCards= PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res=PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String s1 = ConsoleHelper.readString();
            String s2 = ConsoleHelper.readString();
            if (validCreditCards.containsKey(s1)) {
                if (validCreditCards.getString(s1).equals(s2))
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), s1));
                else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            break;
        }
    }
}
