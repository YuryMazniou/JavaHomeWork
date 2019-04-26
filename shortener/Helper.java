package by.it.mazniou.shortener;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    //генерировать случайную строку
    public static String generateRandomString(){
        SecureRandom random = new SecureRandom();
        BigInteger ss =  new BigInteger(130, random);
        return ss.toString(36);
    }
    public static void printMessage(String message){
        System.out.println(message);
    }
}
