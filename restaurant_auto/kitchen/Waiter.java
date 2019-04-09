package by.it.mazniou.restaurant_auto.kitchen;

import by.it.mazniou.restaurant_auto.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(String.format("%s was cooked by %s",arg,o));
    }
}
