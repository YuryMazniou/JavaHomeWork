package by.it.mazniou.restaurant_auto.kitchen;

import by.it.mazniou.restaurant_auto.Tablet;

import java.io.IOException;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
        this.initDishes();
    }

    @Override
    protected void initDishes() throws IOException {
        int random= (int) ((Math.random()*10+1)/2);
        for (int i = 0; i <random; i++) {
            this.dishes.add(Dish.values()[i]);
        }
    }
}
