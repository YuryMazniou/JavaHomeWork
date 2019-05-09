package by.it.mazniou.tasks.numbersTribonachi;


/* 
Ребенок бежит по лестнице состоящей из N ступенек, за 1 шаг он может пройти одну, две или три ступеньки.
Реализуй метод countPossibleRunups(int n), который вернет количество способов которыми ребенок
может пробежать всю лестницу состоящую из n ступенек.
*/
public class Solution {
    private static int n = 70;
    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if(n==0)return 1L;
        if(n>0){
            if(n==2)return 2L;
            if(n==3)return 4L;
            if(n>3){
                long greatGreat=1L;
                long great=2L;
                long last=4L;
                for (int i = 0; i <n-3; i++) {
                    long prom=greatGreat;
                    greatGreat=great;
                    great=last;
                    last=prom+greatGreat+great;
                }
                return last;
            }
        }
        return 0;
    }
}

