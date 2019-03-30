package by.it.mazniou.HTML_editor;

import javax.swing.text.html.HTMLDocument;
import java.io.File;

public class Controller {
    private View view;
    private HTMLDocument document; //модель
    private File currentFile;  //поле которое отвечает за файл, который сейчас открыт в нашем редакторе (текущий файл)

    public Controller(View view) {
        this.view = view;
    }
    public void init(){}
    public void exit(){ System.exit(0);}
    public static void main(String[] args) {
        View view1=new View();
        Controller controller=new Controller(view1);
        view1.setController(controller);
        view1.init();
        controller.init();
    }
}
