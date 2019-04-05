package by.it.mazniou.restaurant_auto.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

//С его помощью будем регистрировать события в хранилище.
public class StatisticManager {
    private static volatile StatisticManager instance;
    private StatisticStorage statisticStorage=new StatisticStorage();
    private Set<Cook>cooks=new HashSet<>();

    private StatisticManager(){}

    public static StatisticManager getInstance(){
        if(instance==null){
            synchronized (StatisticManager.class){
                if(instance==null){
                    instance=new StatisticManager();
                }
            }
        }
        return instance;
    }

    public Map<Date,Long> getVideoToDatesInfo(){
        Map<Date,Long>map=new HashMap<>();
        List<EventDataRow> list=statisticStorage.get(EventType.SELECTED_VIDEOS);
        for (int i = 0; i <list.size(); i++) {
            VideoSelectedEventDataRow video= (VideoSelectedEventDataRow) list.get(i);
            map.put(video.getDate(),video.getAmount());
        }
        return map;
    }

    public void register(Cook cook){
        cooks.add(cook);
    }
    //будет регистрировать событие в хранилище.
    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    //хранилище
    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            storage=new HashMap<>();
            for (EventType e :EventType.values()) {
                storage.put(e,new ArrayList<EventDataRow>());
            }
        }
        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }
        private List<EventDataRow> get(EventType type){
            return storage.get(type);
        }
    }
}
