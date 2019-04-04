package by.it.mazniou.restaurant_auto;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet=new Tablet(1);
        tablet.createOrder();
        /*tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();*/
        Cook cook=new Cook("Amigo");
        tablet.addObserver(cook);
        Waiter waiter=new Waiter();
        cook.addObserver(waiter);
    }
}
