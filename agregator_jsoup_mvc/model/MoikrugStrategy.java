package by.it.mazniou.jsoup_agregator.model;
import by.it.mazniou.jsoup_agregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s=all";
    private static final String VACANCIES="https://moikrug.ru";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy>list=new ArrayList<>();
        int count=0;
        try {
            Document document = getDocument(searchString, count);
            while (true) {
                Elements elements = document.getElementsByClass("job");
                if(elements.size()!=0) {
                    for (Element e : elements) {
                        if(e!=null) {
                            Vacancy vacancy = new Vacancy();
                            String title = e.getElementsByAttributeValue("class","title").get(0).getElementsByTag("a").text().trim();
                            vacancy.setTitle(title);
                            String salary = e.getElementsByClass("salary").get(0).text().trim();
                            vacancy.setSalary(salary);
                            Elements location=e.getElementsByClass("location");
                            String city="";
                            if(location!=null&&!location.isEmpty())city = e.getElementsByClass("location").get(0).text().trim();
                            vacancy.setCity(city);
                            String companyName = e.getElementsByClass("company_name").get(0).text().trim();
                            vacancy.setCompanyName(companyName);
                            String siteName = "https://moikrug.ru";
                            vacancy.setSiteName(siteName);
                            String url = VACANCIES+e.getElementsByAttributeValue("class","title").get(0).getElementsByTag("a").attr("href");
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
    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT,page,searchString))
                .userAgent("Chrome/73.0.3683.103 Safari/537.36")
                .referrer("https://moikrug.ru/vacancies?page=1&q=java+Dnepropetrovsk&type=all").get();
    }
}
/*private static final String URL_FORMAT= "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> Vacancies = new ArrayList<>();
        int pageNum = 0;
        Document doc = null;
        while(true)
        {
            try {
                doc = getDocument(searchString, pageNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements vacancies = doc.getElementsByClass("job");
            if (vacancies.size()==0) break;
            for (Element element: vacancies)
            {
                if (element != null)
                {
                    Vacancy vac = new Vacancy();
                    vac.setTitle(element.getElementsByAttributeValue("class", "title").text());
                    vac.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vac.setSiteName(URL_FORMAT);
                    vac.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                    String salary = element.getElementsByAttributeValue("class", "salary").text();
                    String city = element.getElementsByAttributeValue("class", "location").text();
                    vac.setSalary(salary.length()==0 ? "" : salary);
                    vac.setCity(city.length()==0 ? "" : city);
                    Vacancies.add(vac);
                }
            }
            pageNum++;
        }
        return Vacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
        //String url = String.format("%s?q==%s&page=%s",URL_FORMAT, searchString, page);
        String url = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                .timeout(5000)
                .referrer("http://google.ru")
                .get();
    }*/