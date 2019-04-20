package by.it.mazniou.my_collections;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable,Cloneable, Set<E> {
    private static final Object PRESENT=new Object(); //это будет наша заглушка.
    private transient HashMap<E,Object> map; //Список ключей будет нашим сэтом, а вместо значений будем пихать в мапу заглушку PRESENT.
    private static final long serialVersionUID = 1L;

    public AmigoSet() {
        map=new HashMap<>();
    }
    public AmigoSet(Collection<? extends E> collection){
        int result=(((int)(collection.size()/.75f))+1);
        map=new HashMap<E,Object>(16>result?16:result);
        for (E e:collection) {
            add(e);
        }
    }

    @Override
    public boolean add(E e) {
        boolean result=false;
        if(!map.containsKey(e))result=true;
        map.put(e,PRESENT);
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o,map.get(o));
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        }catch(Exception e){
            throw  new InternalError();
        }
    }

}
