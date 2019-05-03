package by.it.mazniou.agregator_links_jsoup.vo;

public class Vacancy {
    private String title;//название вакансии
    private String salary;//зарплата
    private String city;
    private String companyName;//название компании ,которая размещает вакансию
    private String siteName;//сайт на котором найдена вакансия
    private String url;//сылка на этот сайт

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
