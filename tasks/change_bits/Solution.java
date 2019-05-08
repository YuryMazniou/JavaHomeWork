package by.it.mazniou.tasks.change_bits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        if(i!=j&&i>=0&&j>=0){
            long num=number;
            long numI=num & (1L<<i);
            long numJ=num & (1L<<j);
            if((numI==0&&numJ==0)||(numI>0&&numJ>0))return number;
            if(numI>0&&numJ==0){
                num=num | (1L<<j);
                num=num ^ numI;
                return num;
            }
            if(numI==0&&numJ>0){
                num=num | (1L<<i);
                num=num ^ numJ;
                return num;
            }
        }
        return number;
    }
}
