package by.it.mazniou.sokoban.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        view=new View(this);
        view.setEventListener(this);
        model=new Model();
        model.setEventListener(this);
        model.restart();
        view.init();
        //view.update();
    }
    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }
    public static void main(String[] args) {
        Controller controller=new Controller();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
