package by.it.mazniou.task_about_weight;

/* 
Получи заданное число
Вариант на тему "Задача о гирях" и троичную семитричную систему
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task {
    public static void main(String[] args) {
        Task solution = new Task();
        solution.createExpression(74);//или любое другое число
    }

    public void createExpression(int number) {
        int[]line={ 1, 3, 9, 27, 81, 243, 729, 2187};
        //StringBuilder str=new StringBuilder(getThreeSystemNoSimetr(number));
        String itog= getThreeSystemNoSimetr(number);//str.reverse().toString();
        //System.out.println(itog);
        List<String>list=getThreeSystemSimetr(itog);
        //System.out.println(list);
        List<String>finalList=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            if(list.get(i).equals("+"))finalList.add("+ "+line[list.size()-1-i]);
            if(list.get(i).equals("-"))finalList.add("- "+line[list.size()-1-i]);
        }
        Collections.reverse(finalList);
        //System.out.println(finalList);
        //System.out.println("1"+finalList.get(3)+"1");
        StringBuilder str=new StringBuilder(number+" = ");
        for (int i = 0; i <finalList.size(); i++) {
            if(i!=finalList.size()-1)str.append(finalList.get(i)).append(" ");
            else str.append(finalList.get(i));
        }
        System.out.println(str.toString());
    }
    public String getThreeSystemNoSimetr(int number){
        StringBuilder str=new StringBuilder();
        int celoe=number/3;
        int ostatok=number%3;
        if(celoe>0)return str.append(ostatok).append(getThreeSystemNoSimetr(celoe)).toString();
        if(celoe==0&&ostatok%3==2)return "2";
        return "1";
    }
    public List<String> getThreeSystemSimetr(String text){
        List<String>list=new ArrayList<>();
        char[]chars=text.toCharArray();
        for (int i = 0; i <chars.length; i++) {
            list.add(String.valueOf(chars[i]));
        }
        //System.out.println(list);
        List<String>itogList=new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            if(list.get(i).equals("0")){
                itogList.add("0");
            }
            if(list.get(i).equals("1")){
                itogList.add("+");
            }
            if(list.get(i).equals("2")){
                itogList.add("-");
                if(i+1<=list.size()-1)list.set(i+1,String.valueOf(Integer.parseInt(list.get(i+1))+1));
                else itogList.add("+");
            }
            if(list.get(i).equals("3")){
                itogList.add("0");
                if(i+1<=list.size()-1)list.set(i+1,String.valueOf(Integer.parseInt(list.get(i+1))+1));
                else itogList.add("+");
            }
        }
        Collections.reverse(itogList);
        return itogList;
    }
}