package by.it.mazniou.shortener.tests;

import by.it.mazniou.shortener.Helper;
import by.it.mazniou.shortener.Shortener;
import by.it.mazniou.shortener.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy s=new HashMapStorageStrategy();
        Shortener shortener=new Shortener(s);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy(){
        OurHashMapStorageStrategy st=new OurHashMapStorageStrategy();
        Shortener shortener=new Shortener(st);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy(){
        FileStorageStrategy s=new FileStorageStrategy();
        Shortener shortener=new Shortener(s);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy(){
        HashBiMapStorageStrategy st=new HashBiMapStorageStrategy();
        Shortener shortener=new Shortener(st);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy(){
        DualHashBidiMapStorageStrategy st=new DualHashBidiMapStorageStrategy();
        Shortener shortener=new Shortener(st);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy(){
        OurHashBiMapStorageStrategy st=new OurHashBiMapStorageStrategy();
        Shortener shortener=new Shortener(st);
        testStorage(shortener);
    }

    public void testStorage(Shortener shortener){
        String lineOne= Helper.generateRandomString();
        String lineTwo=Helper.generateRandomString();
        String lineThree=lineOne;
        Long indOne=shortener.getId(lineOne);
        Long indTwo=shortener.getId(lineTwo);
        Long indThree=shortener.getId(lineThree);
        Assert.assertNotEquals(indTwo,indOne);
        Assert.assertNotEquals(indTwo,indThree);
        Assert.assertEquals(indOne,indThree);
        String getLineOne=shortener.getString(indOne);
        String getLineTwo=shortener.getString(indTwo);
        String getLineThree=shortener.getString(indThree);
        Assert.assertEquals(lineOne,getLineOne);
        Assert.assertEquals(lineTwo,getLineTwo);
        Assert.assertEquals(lineThree,getLineThree);
    }
}
