package by.it.mazniou.pattern.strategy.retrievers;

import by.it.mazniou.pattern.strategy.cache.LRUCache;
import by.it.mazniou.pattern.strategy.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private Storage storage;
    private LRUCache lruCache=new LRUCache(15);
    private OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        originalRetriever=new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object o=lruCache.find(id);
        if(o!=null)return o;
        o=originalRetriever.retrieve(id);
        lruCache.set(id,o);
        return o;
    }
}
