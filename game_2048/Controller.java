package by.it.mazniou.game_2048;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//будет следить за нажатием клавиш во время игры.
public class Controller extends KeyAdapter {
    private View view;
    private Model model;
    private static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        this.view=new View(this);
    }
    public void resetGame(){
        model.score=0;
        model.maxTile=0;
        view.isGameWon=false;
        view.isGameLost=false;
        model.resetGameTiles();
    }

    public View getView() {
        return view;
    }

    public Tile[][] getGameTiles(){
        return model.getGameTiles();
    }
    public int getScore(){
        return model.score;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //super.keyPressed(e);
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){resetGame();}
        if(!model.canMove())view.isGameLost=true;
        if(!view.isGameLost&&!view.isGameWon) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.down();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                model.up();
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                model.right();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                model.left();
            }
        }
        if(model.maxTile==WINNING_TILE)view.isGameWon=true;
        view.repaint();
    }
}
