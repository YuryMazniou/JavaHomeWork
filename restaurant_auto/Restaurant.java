package by.it.mazniou.restaurant_auto;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet=new Tablet(1);
        /*tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();*/
        Cook cook=new Cook("Amigo");
        tablet.addObserver(cook);
        tablet.createOrder();
        Waiter waiter=new Waiter();
        cook.addObserver(waiter);
        DirectorTablet d=new DirectorTablet();
        d.printAdvertisementProfit();
        d.printCookWorkloading();
        d.printActiveVideoSet();
        d.printArchivedVideoSet();
        /*Tablet tablet = new Tablet(0);
        Cook cook =  new Cook("Cook");
        Waiter waiter = new Waiter();
        tablet.addObserver(cook);
        cook.addObserver(waiter);
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();*/
    }
}
