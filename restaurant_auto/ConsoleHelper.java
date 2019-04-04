package by.it.mazniou.restaurant_auto;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
        return reader.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException{
        writeMessage("Hello !!!\nПредлогаем вам следующие блюда:");
        writeMessage(Dish.allDishesToString());
        writeMessage("Выберите блюдо из списка и введите его в консоль..");
        String result=null;
        List<Dish>list=new ArrayList<>();
        while (true){
            result=readString();
            if(result.equals("exit")){
                writeMessage("Заказ получен.Спасибо.");
                break;}
            if(!Dish.allDishesToString().contains(result))writeMessage("Такого блюда нет,повторите заказ..");
            else {
                list.add(Dish.valueOf(result));
                writeMessage("Записали..Может еще чего-нибудь? Если вы закончили выбор введите \"exit\" ");
            }
        }
        return list;
    }
}
