package by.it.mazniou.sokoban_game.controller;


import by.it.mazniou.sokoban_game.model.Direction;
import by.it.mazniou.sokoban_game.model.GameObjects;
import by.it.mazniou.sokoban_game.model.Model;
import by.it.mazniou.sokoban_game.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        view=new View(this);
        model=new Model();
        model.restart();
        view.init();
        view.setEventListener(this);
        model.setEventListener(this);
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
