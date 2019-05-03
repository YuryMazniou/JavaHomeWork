package by.it.mazniou.agregator_links_jsoup.model;

//Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер (http://hh.ua/ и http://hh.ru/).
public class HHStrategy implements Strategy{
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
}
