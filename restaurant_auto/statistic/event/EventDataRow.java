package by.it.mazniou.restaurant_auto.statistic.event;


import java.util.Date;

//по нему мы определяем, является ли переданный объект событием или нет.
public interface EventDataRow {
    EventType getType();
    Date getDate();
    int getTime();
}
