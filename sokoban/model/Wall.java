package by.it.mazniou.sokoban.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(getX(),getY(),Model.FIELD_CELL_SIZE,Model.FIELD_CELL_SIZE);
    }
}
