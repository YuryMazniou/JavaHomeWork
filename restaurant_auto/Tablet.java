package by.it.mazniou.restaurant_auto;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    final int number;
    private static Logger logger=Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }
    // будет создавать заказ из тех блюд, которые выберет пользователь.
    public Order createOrder(){
        try {
            Order order=new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            setChanged();
            if(!order.isEmpty()){
                notifyObservers(order);
                try{new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();}
                catch (NoVideoAvailableException e){logger.log(Level.INFO,String.format("No video is available for the order %s",order));}
            }
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Console is unavailable.");
            return null;
        }
    }
    public int getNumber() {
        return number;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
