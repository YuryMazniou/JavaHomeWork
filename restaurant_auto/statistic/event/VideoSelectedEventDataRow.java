package by.it.mazniou.restaurant_auto.statistic.event;

import by.it.mazniou.restaurant_auto.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow {
    private List<Advertisement> optimalVideoSet; //список видео-роликов, отобранных для показа
    private long amount;  //сумма денег в копейках
    private int totalDuration;  //общая продолжительность показа отобранных рекламных роликов
    private Date currentDate;//текущая дата

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate=new Date();//((long) Math.abs(System.currentTimeMillis() - (Math.random()*10*100000000)));
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }
    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
