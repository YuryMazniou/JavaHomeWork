package by.it.mazniou.jsoup_agregator.model;


import by.it.mazniou.jsoup_agregator.view.View;
import by.it.mazniou.jsoup_agregator.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider...providers) {
        this.view = view;
        this.providers = providers;
        if(this.providers==null||this.providers.length==0||view==null)throw new IllegalArgumentException();
    }
    public void selectCity(String city){
        List<Vacancy>vacancies=new ArrayList<>();
        try {
            for (Provider p : providers) {
                vacancies.addAll(p.getJavaVacancies(city));
            }
        }catch (NullPointerException e){}
        view.update(vacancies);
    }
}
