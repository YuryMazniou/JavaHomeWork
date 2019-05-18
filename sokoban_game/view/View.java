package by.it.mazniou.sokoban_game.view;


import by.it.mazniou.sokoban_game.controller.Controller;
import by.it.mazniou.sokoban_game.controller.EventListener;
import by.it.mazniou.sokoban_game.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void update(){
        field.repaint();
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }
    public void setEventListener(EventListener eventListener){
        field.setEventListener(eventListener);
    }
    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }
    public void completed(int level){
        update();
        JOptionPane.showMessageDialog(field,String.format("Поздарвляем!Вы закончили %d уровень!",level));
        controller.startNextLevel();
    }
}
