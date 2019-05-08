package by.it.mazniou.tasks.calculate_Levenshtein_Distance;

import java.util.Arrays;

/*
будет возвращать true, если возможно изменить/добавить/удалить один символ в одной из строк и получить другую.
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("qwertyui","qwertyu"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if(first.equals(second))return true;
        if(Math.abs(first.length()-second.length())>=2)return false;
        return calculate(first,second)<=1?true:false;
    }
    static int calculate(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }

        int substitution = calculate(x.substring(1), y.substring(1))
                + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = calculate(x, y.substring(1)) + 1;
        int deletion = calculate(x.substring(1), y) + 1;
        return min(substitution, insertion, deletion);
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
}
