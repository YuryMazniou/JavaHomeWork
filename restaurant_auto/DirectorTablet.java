package by.it.mazniou.restaurant_auto;

import by.it.mazniou.restaurant_auto.ad.StatisticAdvertisementManager;
import by.it.mazniou.restaurant_auto.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private SimpleDateFormat sim=new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
    /*какую сумму заработали на рекламе, сгруппировать по дням;*/
    public void printAdvertisementProfit(){
        ConsoleHelper.writeMessage("Сумма заработка на рекламе по дням");
        TreeMap<Date,Double>map= (TreeMap<Date, Double>) StatisticManager.getInstance().getVideoToDatesInfo();
        map.descendingMap();
        double total=0.0;
        for (Map.Entry<Date,Double>pair:map.entrySet()) {
            if(pair.getValue()>0){
                total+=pair.getValue();
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH,"%s - %.2f",sim.format(pair.getKey()),pair.getValue()));
            }
        }
        ConsoleHelper.writeMessage("Total - " + String.format(Locale.ENGLISH,"%.2f",total));
    }
    /*загрузка (рабочее время) повара, сгруппировать по дням;*/
    public void printCookWorkloading(){
        ConsoleHelper.writeMessage("Рабочее время поваров по дням:");
        TreeMap<Date,TreeMap<String,Integer>>map= (TreeMap<Date, TreeMap<String, Integer>>) StatisticManager.getInstance().getCookWorkDateAndTime();
        map.descendingMap();
        for (Map.Entry<Date,TreeMap<String,Integer>>pair:map.entrySet()) {
            ConsoleHelper.writeMessage(sim.format(pair.getKey()));
            for (Map.Entry<String,Integer>both:pair.getValue().entrySet()) {
                int work=0;
                if(both.getValue()%60!=0)work=both.getValue()/60+1;
                else work=both.getValue()/60;
                ConsoleHelper.writeMessage(String.format("%s - %d min",both.getKey(),work));
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet(){
        ConsoleHelper.writeMessage("Список оставшихся активных видео:");
        Map<String,Integer>map= StatisticAdvertisementManager.getInstance().getActiveVideoSet();
        Map<String,Integer>mapEng=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Map<String,Integer>mapRus=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Map.Entry<String,Integer>pair:map.entrySet()) {
            char c=pair.getKey().charAt(0);
            if(((c >= 'a')&&(c <= 'z')) || ((c >= 'A')&&(c <= 'Z')))mapEng.put(pair.getKey(),pair.getValue());
            else mapRus.put(pair.getKey(),pair.getValue());
        }
        for (Map.Entry<String,Integer>pair:mapEng.entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %d",pair.getKey(),pair.getValue()));
        }
        for (Map.Entry<String,Integer>pair:mapRus.entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %d",pair.getKey(),pair.getValue()));
        }
    }
    public void printArchivedVideoSet(){
        ConsoleHelper.writeMessage("Список неактивных видео:");
        List<String>list=StatisticAdvertisementManager.getInstance().getArchivedVideoSet();
        List<String>listEng=new ArrayList<>();
        List<String>listRus=new ArrayList<>();
        for (String s:list) {
            char c=s.charAt(0);
            if(((c >= 'a')&&(c <= 'z')) || ((c >= 'A')&&(c <= 'Z')))listEng.add(s);
            else listRus.add(s);
        }
        Collections.sort(listEng,String.CASE_INSENSITIVE_ORDER);
        Collections.sort(listRus,String.CASE_INSENSITIVE_ORDER);
        for (String s:listEng) {
            ConsoleHelper.writeMessage(s.trim());
        }
        for (String s:listRus) {
            ConsoleHelper.writeMessage(s.trim());
        }
    }

    /*public static void main(String[] args) {
        //new DirectorTablet().printAdvertisementProfit();
        new DirectorTablet().printActiveVideoSet();
        new DirectorTablet().printArchivedVideoSet();
    }*/
}
/*Давай подумаем что нужно сделать, чтобы директор мог посмотреть:
1. какую сумму заработали на рекламе, сгруппировать по дням;
2. загрузка (рабочее время) повара, сгруппировать по дням;
3. список активных роликов и оставшееся количество показов по каждому;
4. список неактивных роликов (с оставшемся количеством показов равным нулю).

Для каждого пункта добавим соответствующий метод в StatisticManager.
Директор будет вызывать метод, StatisticManager будет делать различные подсчеты.
Но директор должен из какого-то места вызвать эти методы. Дадим ему планшет, но с другим ПО.
Для этого создадим класс DirectorTablet, в котором будут дружелюбный интерфейс и возможность обращения к статистике.*/