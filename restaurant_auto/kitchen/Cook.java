package by.it.mazniou.restaurant_auto.kitchen;

import by.it.mazniou.restaurant_auto.ConsoleHelper;
import by.it.mazniou.restaurant_auto.statistic.StatisticManager;
import by.it.mazniou.restaurant_auto.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            if(!queue.isEmpty()){
                if(!isBusy()){
                    Order order=queue.poll();
                    if(order!=null)startCookingOrder(order);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }

    public void startCookingOrder(Order order){
        busy=true;
        ConsoleHelper.writeMessage(String.format("Start cooking - %s",order));
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),name,order.getTotalCookingTime()*60,order.getDishes()));
        setChanged();
        notifyObservers(order);
        try {
            Thread.sleep(order.getTotalCookingTime()*10);
        } catch (InterruptedException e) {}
        busy=false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
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
        return name;
    }
}
