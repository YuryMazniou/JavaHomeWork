package by.it.mazniou.sokoban.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }
    public GameObjects getLevel(int level){
        Set<Wall> walls=new HashSet<Wall>();
        walls.add(new Wall(100,100));
        walls.add(new Wall(150,150));
        Set<Box> boxes=new HashSet<>();
        boxes.add(new Box(200,200));
        Set<Home> homes=new HashSet<>();
        homes.add(new Home(300,300));
        Player player=new Player(50,50);
        return new GameObjects(walls,boxes,homes,player);
    }
}
