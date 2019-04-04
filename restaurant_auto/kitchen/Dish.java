package by.it.mazniou.restaurant_auto.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);
    private int duration;

    Dish(int duration) {
        this.duration=duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString(){
        Dish[]list=Dish.values();
        StringBuilder str=new StringBuilder();
        for (int i = 0; i <list.length; i++) {
            str.append(list[i]);
            if(i!=list.length-1)str.append(", ");
        }
        return str.toString();
    }
}
