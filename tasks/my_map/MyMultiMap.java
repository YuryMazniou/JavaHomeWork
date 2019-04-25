package by.it.mazniou.my_map;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int count=0;
        if(!map.isEmpty()){
            for (K k:map.keySet()) {
                count+=map.get(k).size();
            }
            return count;
        }
        return 0;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        if(map.containsKey(key)){
            if(map.get(key).size()<repeatCount){
                int last=map.get(key).size()-1;
                List<V>list=map.get(key);
                V v=list.get(last);
                map.get(key).add(value);
                return v;
            }
            else{
                int last=map.get(key).size()-1;
                List<V>list=map.get(key);
                V v=list.get(last);
                remove(key);
                map.get(key).add(value);
                return v;
            }
        }
        else {
            List<V>list=new ArrayList<V>();
            list.add(value);
            map.put(key,list);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        if(map.containsKey(key)){
            if(!map.get(key).isEmpty()){
                V v=map.get(key).get(0);
                map.get(key).remove(0);
                if(map.get(key).isEmpty()){
                    map.remove(key,map.get(key));
                }
                return v;
            }
        }
        else
            return null;
        return null;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        List<V>list=new ArrayList<>();
        if(map!=null&&!map.isEmpty()){
            for (K k:map.keySet()) {
                list.addAll(map.get(k));
            }
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (K k:map.keySet()) {
            if(map.get(k).contains(value))return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}