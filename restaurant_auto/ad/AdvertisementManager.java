package by.it.mazniou.restaurant_auto.ad;
/* у каждого планшета будет свой объект менеджера, который будет подбирать оптимальный набор роликов и их последовательность для каждого заказа.
Он также будет взаимодействовать с плеером и отображать ролики.*/

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private int timeSeconds;
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private List<Advertisement> bestCollection = new ArrayList<>();
    private long bestPrice = 0;
    private int bestDuration = 0;
    private StatisticManager statisticManager = StatisticManager.getInstance();


    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void processVideos(){
        /*подобрать список  Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду.*/
        if (storage.list().isEmpty()){
            throw new NoVideoAvailableException();
        }
        List<Advertisement> list = new ArrayList<>();
        for(Advertisement advertisement : storage.list()){
            if(advertisement.getHits() > 0)
                list.add(advertisement);
        }
        recurse(list);
        Collections.sort(bestCollection, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int)(o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            }
        }.thenComparing(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int)((o1.getAmountPerOneDisplaying()*1000)/o1.getDuration() - (o2.getAmountPerOneDisplaying()*1000)/o2.getDuration());
            }
        }));
        statisticManager.register(new VideoSelectedEventDataRow(bestCollection,bestPrice,bestDuration));
        for(Advertisement advertisement : bestCollection){

            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... " + advertisement.getAmountPerOneDisplaying() + ", " + (advertisement.getAmountPerOneDisplaying()*1000)/advertisement.getDuration());
            advertisement.revalidate();
        }
    }
    private void recurse(List<Advertisement> list){

        if(!list.isEmpty()){
            int resultDuration = 0;
            long resultPrice = 0;
            for(Advertisement advertisement : list){
                resultDuration = resultDuration + advertisement.getDuration();
                resultPrice = resultPrice + advertisement.getAmountPerOneDisplaying();
            }
            if(resultDuration <=timeSeconds && bestPrice <= resultPrice){
                if(resultPrice == bestPrice && bestDuration <= resultDuration){
                    if(bestDuration == resultDuration){
                        if(list.size() < bestCollection.size()){
                            bestCollection = list;
                            bestPrice = resultPrice;
                            bestDuration = resultDuration;
                        }
                    }else{
                        bestCollection = list;
                        bestPrice = resultPrice;
                        bestDuration = resultDuration;
                    }
                }
                else{
                    bestCollection = list;
                    bestPrice = resultPrice;
                    bestDuration = resultDuration;
                }



            }
            for(int i = 0; i < list.size(); i++){
                List<Advertisement> newList = new ArrayList<>(list);
                newList.remove(i);
                recurse(newList);
            }

        }
    }
}

/*2.2. Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду. (Пока делать не нужно, сделаем позже).
2.3. Если нет рекламных видео, которые можно показать посетителю, то бросить NoVideoAvailableException, которое перехватить в
оптимальном месте (подумать, где это место) и с уровнем Level.INFO логировать фразу "No video is available for the order " + order
2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа одного рекламного ролика в копейках.
 Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки.
Используйте метод Collections.sort
(Тоже пока делать не нужно, сделаем позже).

Пример для заказа [Water]:
First Video is displaying... 50, 277
где First Video - название рекламного ролика
где 50 - стоимость показа одного рекламного ролика в копейках
где 277 - стоимость показа одной секунды рекламного ролика в тысячных частях копейки (равно 0.277 коп)
Используйте методы из класса Advertisement.*/