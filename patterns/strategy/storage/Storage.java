package by.it.mazniou.pattern.strategy.storage;

public interface Storage {
    void add(Object storedObject);
    Object get(long id);
}
