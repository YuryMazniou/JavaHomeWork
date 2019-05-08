package by.it.mazniou.jsoup_agregator;

import by.it.mazniou.jsoup_agregator.model.HHStrategy;
import by.it.mazniou.jsoup_agregator.model.Model;
import by.it.mazniou.jsoup_agregator.model.MoikrugStrategy;
import by.it.mazniou.jsoup_agregator.model.Provider;
import by.it.mazniou.jsoup_agregator.view.HtmlView;

public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Provider hhProvider = new Provider(new HHStrategy());
        Provider moikrugProvider = new Provider(new MoikrugStrategy());
        Model model = new Model(view, hhProvider, moikrugProvider);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod ();
    }
}
