package by.it.mazniou.shortener;

import by.it.mazniou.shortener.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    //должен для переданного множества строк возвращать множество идентификаторов.
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long>set= new HashSet<>();
        if(strings!=null&&shortener!=null){
            for (String s:strings) {
                set.add(shortener.getId(s));
            }
        }
        return set;
    }
    //будет возвращать множество строк, которое соответствует переданному множеству идентификаторов.
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String>set= new HashSet<>();
        if(keys!=null&&shortener!=null){
            for (Long l:keys) {
                set.add(shortener.getString(l));
            }
        }
        return set;
    }
    //будет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String>testSetString=new HashSet<>();
        if(elementsNumber > 0){
            for (long i = 0; i <elementsNumber; i++) {
                testSetString.add(Helper.generateRandomString());
            }
        }
        Shortener shortener=new Shortener(strategy);
        Date beforeGetIds=new Date();
        Set<Long>testGetIdsMethod=getIds(shortener,testSetString);
        Date afterGetIds=new Date();
        Helper.printMessage(String.format("Метод -getIds- отработал за %d милисекунд",afterGetIds.getTime()-beforeGetIds.getTime()));
        Date beforeGetString=new Date();
        Set<String>testGetStringMethod=getStrings(shortener,testGetIdsMethod);
        Date afterGetString=new Date();
        Helper.printMessage(String.format("Метод -getStrings- отработал за %d милисекунд",afterGetString.getTime()-beforeGetString.getTime()));
        if(testSetString.equals(testGetStringMethod))Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
    public static void main(String[] args) {
        HashMapStorageStrategy strategy=new HashMapStorageStrategy();
        testStrategy(strategy,10000);
        OurHashMapStorageStrategy ourHashMapStorageStrategy=new OurHashMapStorageStrategy();
        testStrategy(ourHashMapStorageStrategy,10000);
        FileStorageStrategy fileStorageStrategy=new FileStorageStrategy();
        testStrategy(fileStorageStrategy,5);
        HashBiMapStorageStrategy str=new HashBiMapStorageStrategy();
        testStrategy(str,10000);
    }
}
