package by.it.mazniou.restaurant_auto.kitchen;

import by.it.mazniou.restaurant_auto.ConsoleHelper;
import by.it.mazniou.restaurant_auto.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;   //список выбранных блюд

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes=new ArrayList<>();
        initDishes();
        //this.dishes=ConsoleHelper.getAllDishesForOrder();
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

    protected void initDishes() throws IOException {
        this.dishes= ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet() {
        return tablet;
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
