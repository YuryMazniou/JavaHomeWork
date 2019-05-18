package by.it.mazniou.sokoban_game.view;


import by.it.mazniou.sokoban_game.controller.EventListener;
import by.it.mazniou.sokoban_game.model.Direction;
import by.it.mazniou.sokoban_game.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;


public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler key=new KeyHandler();
        addKeyListener(key);
        setFocusable(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,500,500);
        for (GameObject game:view.getGameObjects().getAll()) {
            game.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener){
        this.eventListener=eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case VK_RIGHT:{eventListener.move(Direction.RIGHT);break;}
                case VK_UP:{eventListener.move(Direction.UP);break;}
                case VK_DOWN:{eventListener.move(Direction.DOWN);break;}
                case VK_LEFT:{eventListener.move(Direction.LEFT);break;}
                case VK_R:{eventListener.restart();break;}
            }
        }
    }
}
