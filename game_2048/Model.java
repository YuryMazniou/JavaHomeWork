package by.it.mazniou.game_2048;

import java.util.*;

//будет содержать игровую логику и хранить игровое поле.
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][]gameTiles;
    protected int score;
    protected int maxTile;
    private Stack<Tile[][]>previousStates=new Stack<>();
    private Stack<Integer>previousScores=new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }
    public void autoMove(){
        PriorityQueue<MoveEfficiency>priorityQueue=new PriorityQueue<>(4,Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));
        if(!priorityQueue.isEmpty())priorityQueue.peek().getMove().move();
    }
    public boolean hasBoardChanged(){
        int resultGameTiles=0;
        int resultLastGameTiles=0;
        if(gameTiles!=null) {
            for (int i = 0; i < gameTiles.length; i++) {
                for (int j = 0; j < gameTiles[i].length; j++) {
                    resultGameTiles += gameTiles[i][j].value;
                }
            }
        }
        if(!previousStates.isEmpty()) {
            Tile[][] tile = previousStates.peek();
            for (int i = 0; i <tile.length; i++) {
                for (int j = 0; j <tile[i].length; j++) {
                    resultLastGameTiles+=tile[i][j].value;
                }
            }
        }
        return resultGameTiles!=resultLastGameTiles;
    }
    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency back=new MoveEfficiency(getEmptyTiles().size(),score,move);
        move.move();
        MoveEfficiency now=new MoveEfficiency(getEmptyTiles().size(),score,move);
        if(hasBoardChanged()) {
            rollback();
            return now;
        }
        else {
            rollback();
            return new MoveEfficiency(-1,0,move);
        }
    }
    private void saveState(Tile[][] tile){
        Tile[][]tileNew=new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i <tile.length; i++) {
            for (int j = 0; j <tile[i].length; j++) {
                Tile t=new Tile();
                t.value=tile[i][j].value;
                tileNew[i][j]=t;
            }
        }
        previousStates.push(tileNew);
        previousScores.push(score);
        isSaveNeeded=false;
    }
    public void rollback(){
        if(!previousScores.isEmpty()&&!previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }
    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        if(n==0){left();}
        if(n==1){right();}
        if(n==2){up();}
        if(n==3){down();}
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
    private boolean compressTiles(Tile[] tiles) {
        boolean result=false;
        for (int j = 0; j < tiles.length - 1; j++) {
            for (int i = 0; i < tiles.length - 1; i++) {
                if (tiles[i].value == 0&&tiles[i + 1].value!=0) {
                    tiles[i].value = tiles[i + 1].value;
                    tiles[i + 1].value = 0;
                    result=true;
                }
            }
        }
        return result;
    }
    private boolean mergeTiles(Tile[] tiles){
        boolean result=false;
        for (int i = 0; i <tiles.length-1; i++) {
            if(tiles[i].value!=0&&tiles[i+1].value!=0){
                if(tiles[i].value==tiles[i+1].value){
                    tiles[i].value+=tiles[i+1].value;
                    tiles[i+1].value=0;
                    if(tiles[i].value>maxTile){
                        maxTile=tiles[i].value;
                    }
                    score+=tiles[i].value;
                    result=true;
                }
            }
        }
        compressTiles(tiles);
        return result;
    }
    public void left(){
        if(isSaveNeeded)saveState(gameTiles);
        boolean superResult=false;
        for (int i = 0; i <gameTiles.length; i++) {
            boolean result1=compressTiles(gameTiles[i]);
            boolean result2=mergeTiles(gameTiles[i]);
            if(result1||result2)superResult=true;
        }
        isSaveNeeded=true;
        if(superResult)addTile();
    }
    public void right(){
        saveState(gameTiles);
        quarterPastMove();
        quarterPastMove();
        left();
        quarterPastMove();
        quarterPastMove();
    }
    public void up(){
        saveState(gameTiles);
        quarterPastMove();
        quarterPastMove();
        quarterPastMove();
        left();
        quarterPastMove();
    }
    public void down(){
        saveState(gameTiles);
        quarterPastMove();
        left();
        quarterPastMove();
        quarterPastMove();
        quarterPastMove();
    }
    private void quarterPastMove(){
        int M = gameTiles.length;
        int N = gameTiles[0].length;
        Tile[][] ret = new Tile[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M-1-r] = gameTiles[r][c];
            }
        }
        gameTiles=ret;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
    public boolean canMove(){
        if(!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[j][i].value == gameTiles[j-1][i]. value)return true;
            }
        }
        return false;
    }
}
