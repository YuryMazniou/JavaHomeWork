package by.it.mazniou.shortener.strategy;

import java.util.HashMap;

public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data=new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key,value);
    }

    @Override
    public Long getKey(String value) {
        Long result=0L;
        for (Long l:data.keySet()) {
            if(data.get(l).equals(value)){
                result=l;
                break;
            }
        }
        return result;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
