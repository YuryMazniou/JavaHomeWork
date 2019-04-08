package by.it.mazniou.restaurant_auto.ad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticAdvertisementManager {
    private AdvertisementStorage storage=AdvertisementStorage.getInstance();
    private static volatile StatisticAdvertisementManager instance;

    private StatisticAdvertisementManager(){}

    public static StatisticAdvertisementManager getInstance(){
        if(instance==null){
            synchronized (StatisticAdvertisementManager.class){
                if(instance==null){
                    instance=new StatisticAdvertisementManager();
                }
            }
        }
        return instance;
    }
    public Map<String,Integer> getActiveVideoSet(){
        Map<String,Integer>map=new HashMap<>();
        List<Advertisement>list=storage.list();
        for (Advertisement a:list) {
            if(a.getHits()>0)map.put(a.getName(),a.getHits());
        }
        return map;
    }
    public List<String> getArchivedVideoSet(){
        List<String>listFinal=new ArrayList<>();
        List<Advertisement>list=storage.list();
        for (Advertisement a:list) {
            if(a.getHits()<=0)listFinal.add(a.getName());
        }
        return listFinal;
    }
}
