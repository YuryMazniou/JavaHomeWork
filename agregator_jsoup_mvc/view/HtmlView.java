package by.it.mazniou.jsoup_agregator.view;

import by.it.mazniou.jsoup_agregator.Controller;
import by.it.mazniou.jsoup_agregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath="./src/"+this.getClass().getPackage().getName().replaceAll("\\.","/")+"/vacancies.html";
    @Override
    public void update(List<Vacancy> vacancies) {
        try{
            String result=getUpdatedFileContent(vacancies);
            updateFile(result);
        }catch(Exception e){e.printStackTrace();}
    }
    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Kiev");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){
        String result=null;
        try {
            Document html = getDocument();
            Element templateOriginal = html.getElementsByClass("template").first();
            Element copyTemplate = templateOriginal.clone();
            copyTemplate.removeAttr("style");
            copyTemplate.removeClass("template");
            for (Element elem : html.getElementsByTag("tr")) {
                if(elem.className().equals("vacancy"))elem.remove();
            }
            for (int i = 0; i <vacancies.size(); i++) {
                Element newElement=copyTemplate.clone();
                Elements newElementsList=newElement.getAllElements();
                for (Element e:newElementsList) {
                    if(e.className().equals("title")){
                        e.getElementsByTag("a").get(0).text(vacancies.get(i).getTitle());
                        e.getElementsByTag("a").get(0).attr("href",vacancies.get(i).getUrl());
                    }
                    if(e.className().equals("city"))e.text(vacancies.get(i).getCity());
                    if(e.className().equals("companyName"))e.text(vacancies.get(i).getCompanyName());
                    if(e.className().equals("salary"))e.text(vacancies.get(i).getSalary());
                }
                templateOriginal.before(newElement.outerHtml());
            }
            result=html.outerHtml();
            return result;
        }catch (IOException e){
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String fileResult){
        if(fileResult!=null){
            try(BufferedWriter writer= new BufferedWriter(new FileWriter(filePath))){
                writer.write(fileResult);
            }catch(IOException e){}
        }
    }
    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath),"UTF-8");
    }
}
