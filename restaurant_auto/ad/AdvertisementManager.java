package by.it.mazniou.restaurant_auto.ad;
/* у каждого планшета будет свой объект менеджера, который будет подбирать оптимальный набор роликов и их последовательность для каждого заказа.
Он также будет взаимодействовать с плеером и отображать ролики.*/

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage=AdvertisementStorage.getInstance();
    private int timeSeconds; //время приготовления блюда

    private List<Advertisement> bestItems = null;
    private long bestPrice;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void processVideos(){
        if(storage.list().isEmpty())throw new NoVideoAvailableException();
        List<Advertisement> list = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0)
                list.add(advertisement);
        }
        getRecurs(list);
        bestItems.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                if (result != 0)
                    return -result;

                long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

                return Long.compare(oneSecondCost1, oneSecondCost2);
            }
        });
        if(!list.isEmpty()){
            StatisticManager.getInstance().register(new VideoSelectedEventDataRow(list,calculatePriceVideos(list),calculateTimeVideos(list)));
        }
        for (int i = 0; i <bestItems.size(); i++) {
            long a=bestItems.get(i).getAmountPerOneDisplaying();
            int b= (int) (bestItems.get(i).getAmountPerOneDisplaying()*1.0/bestItems.get(i).getDuration()*1000);
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",bestItems.get(i).getName(),a,b));
            bestItems.get(i).revalidate();
        }
    }

    public void getRecurs(List<Advertisement> items)
    {
        if (items.size() > 0)
            checkBestTimeItems(items);

        for (int i = 0; i < items.size(); i++)
        {
            List<Advertisement> newList = new ArrayList<>(items);

            newList.remove(i);

            getRecurs(newList);
        }

    }
    public void checkBestTimeItems(List<Advertisement>list){
        if(bestItems==null){
            if(calculateTimeVideos(list)<=timeSeconds){
                bestItems=list;
                bestPrice=calculatePriceVideos(list);
            }
        }
        else{
            if(calculateTimeVideos(list)<=timeSeconds&&calculatePriceVideos(list)>bestPrice){
                bestItems=list;
                bestPrice=calculatePriceVideos(list);
            }
            if(calculateTimeVideos(list)<=timeSeconds&&calculatePriceVideos(list)==bestPrice){
                if(calculateTimeVideos(list)>calculateTimeVideos(bestItems)){
                    bestItems=list;
                    bestPrice=calculatePriceVideos(list);
                }
                if(calculateTimeVideos(list)==calculateTimeVideos(bestItems)){
                    if(calculateAllHits(list)<calculateAllHits(bestItems)){
                        bestItems=list;
                        bestPrice=calculatePriceVideos(list);
                    }
                    else return;
                }
            }
        }
    }
    public int calculateTimeVideos(List<Advertisement>list){
        int allTime=0;
        for (int i = 0; i <list.size(); i++) {
            allTime+=list.get(i).getDuration();
        }
        return allTime;
    }
    public long calculatePriceVideos(List<Advertisement>list){
        long allSumPrice=0;
        for (int i = 0; i <list.size(); i++) {
            allSumPrice+=list.get(i).getAmountPerOneDisplaying();
        }
        return allSumPrice;
    }
    public int calculateAllHits(List<Advertisement>list){
        int allHitsSum=0;
        for (int i = 0; i <list.size(); i++) {
            allHitsSum+=list.get(i).getHits();
        }
        return allHitsSum;
    }

    /*public static void main(String[] args) {
        AdvertisementManager m=new AdvertisementManager(1000);
        m.processVideos();
    }*/
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