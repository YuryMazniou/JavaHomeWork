package by.it.mazniou.shortener.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }
    public int indexFor(int hash, int length){
        return hash & (length - 1);
    }
    public Entry getEntry(Long key){
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }
    public void resize(int newCapacity){
        if (table.length == Integer.MAX_VALUE/2)
        {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }
    public void transfer(Entry[] newTable){
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }
    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }
    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }
    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i] ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null){
            boolean flag=false;
            for (Entry e = table[0]; e != null; e = e.next) {
                if (e.key == null) {
                    e.value = value;
                    return;
                }
            }
            if(!flag) {
                addEntry(0, null, value, 0);
            }
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        Entry e=table[i];
        if(e==null){
            createEntry(hash,key,value,i);
        }
        else{
            boolean flag=false;
            for (e = table[i]; e != null; e = e.next) {
                Long k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    e.value = value;
                    flag=true;
                    return;
                }
            }
            if(!flag){
                addEntry(hash,key,value,i);
            }
        }
    }

    @Override
    public Long getKey(String value) {
        Long key=null;
        for (int i = 0; i <table.length; i++) {
            for (Entry e = table[i]; e != null; e = e.next) {
                if(e.value.equals(value))key=e.key;
            }
        }
        return key;
    }

    @Override
    public String getValue(Long key) {
        if (key == null){
            for (Entry e = table[0]; e != null; e = e.next) {
                if (e.key == null)
                    return e.value;
            }
            return null;
        }
        int hash = hash(key);
        for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }
}
