package by.it.mazniou.jsoup_agregator;


import by.it.mazniou.jsoup_agregator.model.Model;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
        if(model==null)throw new IllegalArgumentException();
    }
    public void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
}
