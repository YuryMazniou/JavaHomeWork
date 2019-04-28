package by.it.mazniou.shortener.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY=16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT=10000;
    private FileBucket[] table=new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize=DEFAULT_BUCKET_SIZE_LIMIT*DEFAULT_INITIAL_CAPACITY;
    public int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }
    public int indexFor(int hash, int length){
        return hash & (length - 1);
    }
    public Entry getEntry(Long key){
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }
    public void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
        maxBucketSize = (int)(newCapacity * DEFAULT_BUCKET_SIZE_LIMIT);
    }
    public void transfer(FileBucket[] newTable){
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j].getEntry();
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                    e = next;
                } while (e != null);
            }
        }
    }
    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex].getEntry();
        FileBucket b=new FileBucket();
        b.putEntry(new Entry(hash, key, value, e));
        table[bucketIndex].remove();
        table[bucketIndex]=b;
        size++;
        for (int i = 0; i <table.length; i++) {
            if(table[i].getFileSize()>bucketSizeLimit)
                resize(2 * table.length);
        }
    }
    public void createEntry(int hash, Long key, String value, int bucketIndex){

        //Entry e = table[bucketIndex].getEntry();
        FileBucket b=new FileBucket();
        b.putEntry(new Entry(hash, key, value, null));
        //table[bucketIndex].remove();
        table[bucketIndex]=b;
        size++;
    }
    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++) {
            if(tab[i]!=null) {
                for (Entry e = tab[i].getEntry(); e != null; e = e.next)
                    if (value.equals(e.value))
                        return true;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null){
            boolean flag=false;
            for (Entry e = table[0].getEntry(); e != null; e = e.next) {
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
        Entry e=null;
        if(table[i]!=null) e=table[i].getEntry();
        if(e==null){
            createEntry(hash,key,value,i);
        }
        else{
            boolean flag=false;
            for (e = table[i].getEntry(); e != null; e = e.next) {
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
            if(table[i]!=null) {
                for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                    if (e.value.equals(value)) key = e.key;
                }
            }
        }
        return key;
    }

    @Override
    public String getValue(Long key) {
        if (key == null){
            for (Entry e = table[0].getEntry(); e != null; e = e.next) {
                if (e.key == null)
                    return e.value;
            }
            return null;
        }
        int hash = hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
        }
        return null;
    }
}
