package by.it.mazniou.mvc.view;


import by.it.mazniou.mvc.controller.Controller;
import by.it.mazniou.mvc.model.ModelData;

public interface View {
    void refresh(ModelData modelData);
    void setController(Controller controller);
}
