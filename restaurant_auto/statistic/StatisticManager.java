package by.it.mazniou.restaurant_auto.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

//С его помощью будем регистрировать события в хранилище.
public class StatisticManager {
    private static volatile StatisticManager instance;
    private StatisticStorage statisticStorage=new StatisticStorage();
    private Set<Cook>cooks=new HashSet<>();

    /*private static ArrayList<EventDataRow>listik=new ArrayList<>();
    {
        listik.add(new VideoSelectedEventDataRow(null,1105,1));
        listik.add(new VideoSelectedEventDataRow(null,1200,2));
        listik.add(new VideoSelectedEventDataRow(null,1303,3));
        listik.add(new VideoSelectedEventDataRow(null,1450,4));
        listik.add(new VideoSelectedEventDataRow(null,55,5));
        listik.add(new VideoSelectedEventDataRow(null,34,6));
        listik.add(new VideoSelectedEventDataRow(null,19,7));

    }*/
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
    public Map<Date, Double> getVideoToDatesInfo(){
        TreeMap<Date,Double>map=new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> list=statisticStorage.get(EventType.SELECTED_VIDEOS);
        //List<EventDataRow> list=listik;
        for (EventDataRow e:list) {
            VideoSelectedEventDataRow videoRow= (VideoSelectedEventDataRow) e;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(videoRow.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();
            map.merge(date,videoRow.getAmount()/100.00,(a,b)->a+b);
            //System.out.println(date+" - "+videoRow.getAmount()/100.00);
        }
        return map;
    }
    public Map<Date,TreeMap<String,Integer>> getCookWorkDateAndTime(){
        TreeMap<Date,TreeMap<String, Integer>>map=new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> list=statisticStorage.get(EventType.COOKED_ORDER);
        for (EventDataRow e:list) {
            TreeMap<String,Integer>cookMap;
            CookedOrderEventDataRow cook= (CookedOrderEventDataRow) e;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cook.getDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            Date date = gregorianCalendar.getTime();
            String cookName=cook.getCookName();
            int cookWork=cook.getTime();
            if(map.containsKey(date)){
                cookMap=map.get(date);
                cookMap.merge(cookName,cookWork,(a,b)->a+b);
            }
            else{
                cookMap=new TreeMap<>();
                cookMap.put(cookName,cookWork);
                map.put(date,cookMap);
            }
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
