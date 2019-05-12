package by.it.mazniou.cash_machine_atm;

import by.it.mazniou.cash_machine_atm.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;
    private List<Integer>listAllCurrency;
    private int amountToIssue;
    private List<Integer>bestListCurrencyToIssue;
    private int bestCountList=0;
    private int bestOneBankNote=0;

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
        return expectedAmount<=getTotalAmount();
    }
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer,Integer>map=new HashMap<>();
        if(denominations!=null&&!denominations.isEmpty()){
            listAllCurrency=new ArrayList<>();
            for (Map.Entry<Integer,Integer>m:denominations.entrySet()) {
                for (int i = 0; i <m.getValue(); i++) {
                    listAllCurrency.add(m.getKey());
                }
            }
            amountToIssue=expectedAmount;
            Collections.sort(listAllCurrency);
            Collections.reverse(listAllCurrency);
            makeListBestCurrencyToIssue(listAllCurrency);
            if(bestListCurrencyToIssue==null)throw new NotEnoughMoneyException();
            for (int i = 0; i <bestListCurrencyToIssue.size(); i++) {
                denominations.put(bestListCurrencyToIssue.get(i),denominations.get(bestListCurrencyToIssue.get(i))-1);
                if(denominations.get(bestListCurrencyToIssue.get(i))==0)denominations.remove(bestListCurrencyToIssue.get(i));
            }
            for (Integer i:bestListCurrencyToIssue) {
                if(!map.containsKey(i))map.put(i, Collections.frequency(bestListCurrencyToIssue,i));
            }
            return map;
        }
        else throw new NotEnoughMoneyException();
    }
    @Override
    public String toString() {
        return "CurrencyManipulator{" +
                "currencyCode='" + currencyCode + '\'' +
                ", denominations=" + denominations +
                '}';
    }
    private void makeListBestCurrencyToIssue(List<Integer> listForSelection){
        int sumValue=0;
        List<Integer>list=new ArrayList<>();
        if (listForSelection.size() > 0) {
            for (int i = 0; i <listForSelection.size(); i++) {
                list.add(listForSelection.get(i));
                sumValue+=listForSelection.get(i);
                if(sumValue==amountToIssue){
                    checkList(list);
                    break;
                }
                if(sumValue>amountToIssue){
                    List<Integer> newList = new ArrayList<>(listForSelection);
                    newList.remove(i);
                    makeListBestCurrencyToIssue(newList);
                    break;
                }
            }
        }
    }
    private void checkList(List<Integer> listForSelection) {
        if(bestListCurrencyToIssue==null){
            bestListCurrencyToIssue=listForSelection;
            bestCountList =listForSelection.size();
            bestOneBankNote= checkMaxBestBanknote(listForSelection);
        }
        else{
            if(listForSelection.size()<bestCountList){
                bestListCurrencyToIssue=listForSelection;
                bestCountList =listForSelection.size();
                bestOneBankNote= checkMaxBestBanknote(listForSelection);
            }
            if(listForSelection.size()==bestCountList){
                int maxBanknote=checkMaxBestBanknote(listForSelection);
                if(maxBanknote>bestOneBankNote){
                    bestListCurrencyToIssue=listForSelection;
                    bestCountList =listForSelection.size();
                    bestOneBankNote= maxBanknote;
                }
            }
        }
    }
    private int checkMaxBestBanknote(List<Integer> listForSelection) {
        Collections.sort(listForSelection);
        return listForSelection.get(listForSelection.size()-1);
    }

}
