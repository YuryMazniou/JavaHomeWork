package by.it.mazniou.hippodrome;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse>horses;
    private Horse winner;
    static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void move(){
        for (int i = 0; i <horses.size(); i++) {
            horses.get(i).move();
        }
    }
    public void print(){
        for (int i = 0; i <horses.size(); i++) {
            horses.get(i).print();
        }
        for (int i = 0; i <10; i++) {
            System.out.println();
        }
    }
    public void run(){
        for (int i = 1; i <=100; i++) {
            move();
            print();
            try{ Thread.sleep(200);}
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void printWinner(){
        System.out.println("Winner is "+getWinner().getName()+"!");
    }
    public Horse getWinner(){
        double dis=0;
        Horse horse=null;
        for (int i = 0; i <horses.size(); i++) {
            if(horses.get(i).getDistance()>dis){
                dis=horses.get(i).getDistance();
                horse=horses.get(i);
            }
        }
        return horse;
    }

    public static void main(String[] args) {
        List<Horse>list=new ArrayList<>();
        list.add(new Horse("A",3,0));
        list.add(new Horse("B",3,0));
        list.add(new Horse("C",3,0));
        game=new Hippodrome(list);
        game.run();
        game.printWinner();
    }
}
