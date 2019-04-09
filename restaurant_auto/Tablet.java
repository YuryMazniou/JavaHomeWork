package by.it.mazniou.restaurant_auto;


import by.it.mazniou.restaurant_auto.ad.AdvertisementManager;
import by.it.mazniou.restaurant_auto.ad.NoVideoAvailableException;
import by.it.mazniou.restaurant_auto.kitchen.Order;
import by.it.mazniou.restaurant_auto.kitchen.TestOrder;
import by.it.mazniou.restaurant_auto.statistic.StatisticManager;
import by.it.mazniou.restaurant_auto.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet{
    final int number;
    private LinkedBlockingQueue<Order> queue;
    private static Logger logger=Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }
    // будет создавать заказ из тех блюд, которые выберет пользователь.
    public Order createOrder(){
        try {
            Order order=new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            if(!order.isEmpty()){
                queue.add(order);
                try{new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();}
                catch (NoVideoAvailableException e){
                    logger.log(Level.INFO,String.format("No video is available for the order %s",order));
                    StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()*60));
                }
            }
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Console is unavailable.");
            return null;
        }
    }
    public void createTestOrder(){
        try {
            TestOrder testOrder=new TestOrder(this);
            ConsoleHelper.writeMessage(testOrder.toString());
            if(!testOrder.isEmpty()){
                queue.add(testOrder);
                try{new AdvertisementManager(testOrder.getTotalCookingTime()*60).processVideos();}
                catch (NoVideoAvailableException e){
                    logger.log(Level.INFO,String.format("No video is available for the order %s",testOrder));
                    StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(testOrder.getTotalCookingTime()*60));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Console is unavailable.");
        }
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public int getNumber() {
        return number;
    }
    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
