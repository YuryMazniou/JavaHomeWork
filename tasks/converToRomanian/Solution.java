package by.it.mazniou.tasks.converToRomanian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        if (s.isEmpty()) return 0;
        if (s.startsWith("M")) return 1000 + romanToInteger(s.substring(1));
        if (s.startsWith("CM")) return 900 + romanToInteger(s.substring(2));
        if (s.startsWith("D")) return 500 + romanToInteger(s.substring(1));
        if (s.startsWith("CD")) return 400 + romanToInteger(s.substring(2));
        if (s.startsWith("C")) return 100 + romanToInteger(s.substring(1));
        if (s.startsWith("XC")) return 90 + romanToInteger(s.substring(2));
        if (s.startsWith("L")) return 50 + romanToInteger(s.substring(1));
        if (s.startsWith("XL")) return 40 + romanToInteger(s.substring(2));
        if (s.startsWith("X")) return 10 + romanToInteger(s.substring(1));
        if (s.startsWith("IX")) return 9 + romanToInteger(s.substring(2));
        if (s.startsWith("V")) return 5 + romanToInteger(s.substring(1));
        if (s.startsWith("IV")) return 4 + romanToInteger(s.substring(2));
        if (s.startsWith("I")) return 1 + romanToInteger(s.substring(1));
        return 0;
    }
}
