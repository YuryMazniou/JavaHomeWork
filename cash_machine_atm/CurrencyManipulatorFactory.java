package by.it.mazniou.cash_machine_atm;

import java.util.*;

public class CurrencyManipulatorFactory {
    private static Map<String,CurrencyManipulator>map=new HashMap<>();
    private  static volatile CurrencyManipulatorFactory instance;

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulatorFactory getInstance() {
        CurrencyManipulatorFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (CurrencyManipulatorFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CurrencyManipulatorFactory();
                }
            }
        }
        return localInstance;
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if(map.containsKey(currencyCode.toLowerCase()))return map.get(currencyCode.toLowerCase());
        CurrencyManipulator manipulator=new CurrencyManipulator(currencyCode);
        map.put(currencyCode.toLowerCase(),manipulator);
        return manipulator;
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        List<CurrencyManipulator>list=new ArrayList<>();
        for (String nameCurrency :map.keySet()) {
            list.add(map.get(nameCurrency));
        }
        return list;
    }
}
