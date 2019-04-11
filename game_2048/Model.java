package by.it.mazniou.game_2048;

import java.util.ArrayList;
import java.util.List;

//будет содержать игровую логику и хранить игровое поле.
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][]gameTiles;
    protected int score;
    protected int maxTile;

    public Model() {
        resetGameTiles();
    }
    private void addTile(){
        List<Tile> list=getEmptyTiles();
        if(!list.isEmpty()){
            int random= (int) (list.size()*Math.random());
            list.get(random).setValue(Math.random() < 0.9 ? 2 : 4);
        }
    }

    private List<Tile> getEmptyTiles(){
        List<Tile>list=new ArrayList<>();
        for (int i = 0; i <gameTiles.length; i++) {
            for (int j = 0; j <gameTiles[i].length ; j++) {
                if(gameTiles[i][j].isEmpty())list.add(gameTiles[i][j]);
            }
        }
        return list;
    }
    protected void resetGameTiles(){
        gameTiles=new Tile[FIELD_WIDTH][FIELD_WIDTH];
        this.score=0;
        this.maxTile=0;
        for (int i = 0; i <gameTiles.length; i++) {
            for (int j = 0; j <gameTiles[i].length ; j++) {
                gameTiles[i][j]=new Tile();
            }
        }
        addTile();
        addTile();
    }
    private void compressTiles(Tile[] tiles) {
        for (int j = 0; j < tiles.length - 1; j++)
            for (int i = 0; i < tiles.length - 1; i++) {
                if (tiles[i].value == 0) {
                    tiles[i].value = tiles[i + 1].value;
                    tiles[i + 1].value = 0;
                }
            }
    }
    private void mergeTiles(Tile[] tiles){
        for (int i = 0; i <tiles.length; i++) {

        }
    }
}
