package by.it.mazniou.restaurant_auto;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets=new ArrayList<>();
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval){
        this.tablets=tablets;
        this.interval=interval;
    }
    @Override
    public void run() {
        while (true){
            int randomTablet= (int) (Math.random()*10/2);
            Tablet tablet=tablets.get(randomTablet);
            tablet.createTestOrder();
            try {
                Thread.sleep(interval);
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
}
