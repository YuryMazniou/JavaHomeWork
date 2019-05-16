package by.it.mazniou.sokoban.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Model {
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel=1;
    private LevelLoader levelLoader=new LevelLoader(Paths.get("D:\\programIT\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    public static final int FIELD_CELL_SIZE = 20;

    public void setEventListener(EventListener eventListener){
        this.eventListener=eventListener;
    }
    public GameObjects getGameObjects(){
        return gameObjects;
    }
    public void restartLevel(int level){
        gameObjects=levelLoader.getLevel(level);
    }
    public void restart(){
        restartLevel(currentLevel);
    }
    public void startNextLevel(){
        currentLevel++;
        restartLevel(currentLevel);
    }
    public void move(Direction direction){
        boolean flag=false;
        Set<CollisionObject>set=new HashSet<>();
        set.addAll(gameObjects.getBoxes());
        set.add(gameObjects.getPlayer());
        for (CollisionObject c:set) {
            if(checkWallCollision(c,direction)){
                flag=true;
                break;
            }
        }
        if(flag)return;
        if(checkBoxCollisionAndMoveIfAvaliable(direction)){
            return;
        }
        switch (direction){
            case RIGHT:{gameObjects.getPlayer().move(FIELD_CELL_SIZE,0);break;}
            case LEFT:{gameObjects.getPlayer().move(-FIELD_CELL_SIZE,0);break;}
            case DOWN:{gameObjects.getPlayer().move(0,FIELD_CELL_SIZE);break;}
            case UP:{gameObjects.getPlayer().move(0,-FIELD_CELL_SIZE);break;}
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        if(gameObjects.getWalls()!=null) {
            for (Wall w : gameObjects.getWalls()) {
                if(w.isCollision(gameObject,direction))return true;
            }
        }
        return false;
    }
    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){

        return false;
    }
    public void checkCompletion(){
        boolean flag=true;
        Set<Box>boxes=gameObjects.getBoxes();
        Set<Home>homes=gameObjects.getHomes();
        for (Box b:gameObjects.getBoxes()) {
            boolean flagSecond=false;
            for (Home h:gameObjects.getHomes()) {
                if(b.getX()==h.getX()&&b.getY()==h.getY()){
                    flagSecond=true;
                    break;
                }
            }
            if(!flagSecond){
                flag=false;
                break;
            }
        }
        if(flag)eventListener.levelCompleted(currentLevel);
    }
}
