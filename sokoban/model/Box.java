package by.it.mazniou.sokoban.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(getX(),getY(),Model.FIELD_CELL_SIZE,Model.FIELD_CELL_SIZE);
    }

    @Override
    public void move(int x, int y) {
        setX(getX()+x);
        setY(getY()+y);
    }
}
