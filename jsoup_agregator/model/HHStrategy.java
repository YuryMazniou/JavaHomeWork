package by.it.mazniou.jsoup_agregator.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер (http://hh.ua/ и http://hh.ru/).
public class HHStrategy implements Strategy{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy>list=new ArrayList<>();
        int count=0;
        try {
            Document document = getDocument(searchString, count);
            while (true) {
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if(elements.size()!=0) {
                    for (Element e : elements) {
                        if(e!=null) {
                            Vacancy vacancy = new Vacancy();
                            String title = e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text().trim();
                            vacancy.setTitle(title);
                            String salary = e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text().trim();
                            vacancy.setSalary(salary);
                            String city = e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text().trim();
                            vacancy.setCity(city);
                            String companyName = e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text().trim();
                            vacancy.setCompanyName(companyName);
                            String siteName = URL_FORMAT;
                            vacancy.setSiteName(siteName);
                            String url = e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href");
                            vacancy.setUrl(url);
                            list.add(vacancy);
                        }
                    }
                }
                else{
                    count=0;
                    break;
                }
                document = getDocument(searchString, ++count);
            }
        } catch (IOException e) {}
        return list;
    }
    protected Document getDocument(String searchString, int page) throws IOException{
        return Jsoup.connect(String.format(URL_FORMAT,searchString,page))
                .userAgent("Chrome/73.0.3683.103 Safari/537.36")
                .referrer("https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2").get();
    }
}
