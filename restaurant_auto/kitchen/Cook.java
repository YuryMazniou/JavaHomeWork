package by.it.mazniou.restaurant_auto.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object arg) {
        Order order=(Order)arg;
        ConsoleHelper.writeMessage(String.format("Start cooking - %s",arg));
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(observable.toString(),name,order.getTotalCookingTime()*60,order.getDishes()));
        setChanged();
        notifyObservers(arg);
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
