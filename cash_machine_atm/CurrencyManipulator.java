package by.it.mazniou.cash_machine_atm;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations=new HashMap<>();
    }
    public boolean hasMoney(){
        return denominations.size()>0;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if(denominations.containsKey(denomination))denominations.put(denomination,denominations.get(denomination)+count);
        else denominations.put(denomination,count);
    }

    public int getTotalAmount(){
        int result=0;
        for (Integer i:denominations.keySet()) {
            result+=(i*denominations.get(i));
        }
        return result;
    }
    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount>=getTotalAmount();
    }
    public Map<Integer, Integer> withdrawAmount(int expectedAmount){

    }
    @Override
    public String toString() {
        return "CurrencyManipulator{" +
                "currencyCode='" + currencyCode + '\'' +
                ", denominations=" + denominations +
                '}';
    }

}
