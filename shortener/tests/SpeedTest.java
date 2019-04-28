package by.it.mazniou.shortener.tests;

import by.it.mazniou.shortener.Helper;
import by.it.mazniou.shortener.Shortener;
import by.it.mazniou.shortener.strategy.HashBiMapStorageStrategy;
import by.it.mazniou.shortener.strategy.HashMapStorageStrategy;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date before=new Date();
        if(strings!=null&&ids!=null){
            for (String s:strings) {
                ids.add(shortener.getId(s));
            }
        }
        Date after=new Date();
        return after.getTime()-before.getTime();
    }
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date before=new Date();
        if(strings!=null&&ids!=null){
            for (Long l:ids) {
                strings.add(shortener.getString(l));
            }
        }
        Date after=new Date();
        return after.getTime()-before.getTime();
    }
    @Test
    public void testHashMapStorage(){
        Shortener shortener1=new Shortener(new HashMapStorageStrategy());
        Shortener shortener2=new Shortener(new HashBiMapStorageStrategy());
        Set<String>origStrings=new HashSet<>();
        for (int i = 0; i <10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long>testIdsShortener1=new HashSet<>();
        long timeShortener1=getTimeToGetIds(shortener1,origStrings,testIdsShortener1);
        Set<Long>testIdsShortener2=new HashSet<>();
        long timeShortener2=getTimeToGetIds(shortener2,origStrings,testIdsShortener2);
        boolean result=timeShortener1>timeShortener2;
        assertTrue(result);
        Set<String>testString1=new HashSet<>();
        long temeShortener1GetString=getTimeToGetStrings(shortener1,testIdsShortener1,testString1);
        Set<String>testString2=new HashSet<>();
        long temeShortener2GetString=getTimeToGetStrings(shortener2,testIdsShortener2,testString2);
        assertEquals(temeShortener1GetString, temeShortener2GetString,30);
    }
}
