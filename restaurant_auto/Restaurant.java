package by.it.mazniou.restaurant_auto;

import by.it.mazniou.restaurant_auto.kitchen.Cook;
import by.it.mazniou.restaurant_auto.kitchen.Order;
import by.it.mazniou.restaurant_auto.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue=new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Cook cook1 =  new Cook("Cook");
        cook1.setQueue(orderQueue);
        Cook cook2 =  new Cook("Amigo");
        cook2.setQueue(orderQueue);
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        Thread thread1=new Thread(cook1);
        Thread thread2=new Thread(cook2);
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        List<Tablet>list=new ArrayList<>();
        for (int i = 1; i <=5; i++) {
            Tablet tablet=new Tablet(i);
            tablet.setQueue(orderQueue);
            list.add(tablet);
        }
        RandomOrderGeneratorTask random=new RandomOrderGeneratorTask(list,ORDER_CREATING_INTERVAL);
        Thread thread=new Thread(random);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        thread.interrupt();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();
    }
}
