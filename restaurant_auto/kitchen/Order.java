package by.it.mazniou.restaurant_auto.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;   //список выбранных блюд

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes=ConsoleHelper.getAllDishesForOrder();
    }
    public int getTotalCookingTime(){
        int result=0;
        for (int i = 0; i <dishes.size(); i++) {
            result+=dishes.get(i).getDuration();
        }
        return result;
    }
    public boolean isEmpty(){
        return dishes.size()==0;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        if(dishes==null||dishes.size()==0)return str.toString();
        else {
            for (int i = 0; i <dishes.size(); i++) {
                str.append(dishes.get(i));
                if(i!=dishes.size()-1)str.append(", ");
            }
            return String.format("Your order: [%s] of Tablet{number=%d}, cooking time %dmin",str,tablet.getNumber(),getTotalCookingTime());
        }
    }
}
