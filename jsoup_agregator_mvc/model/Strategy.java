package by.it.mazniou.jsoup_agregator.model;

import by.it.mazniou.jsoup_agregator.vo.Vacancy;

import java.util.List;

public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
