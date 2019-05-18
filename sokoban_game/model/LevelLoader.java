package by.it.mazniou.sokoban_game.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class LevelLoader {
    private Path levels;
    private List<String> cache;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }
    /*public GameObjects getLevel(int level){
        Set<Box>setBox=new HashSet<>();
        Set<Wall>setWall=new HashSet<>();
        Set<Home>setHome=new HashSet<>();
        Player player=null;
        if(cache==null)readFile();
        int startCheck=cache.indexOf("Maze: "+level);
        int x0=Model.FIELD_CELL_SIZE/2;
        int y0=Model.FIELD_CELL_SIZE/2;
        for (int i = startCheck+7; i <cache.size(); i++) {
            if(!cache.get(i).equals("*************************************")) {
                char[] line = cache.get(i).toCharArray();
                for (int j = 0; j <line.length; j++) {
                    System.out.print("|"+line[j]+"|");
                }
                System.out.println(line);
                for (int j = 0; j <line.length; j++) {
                    if(line[j]==' ')x0+=Model.FIELD_CELL_SIZE;
                    if(line[j]=='X'){
                        setWall.add(new Wall(x0,y0));
                        x0+=Model.FIELD_CELL_SIZE;
                    }
                    if(line[j]=='*'){
                        setBox.add(new Box(x0,y0));
                        x0+=Model.FIELD_CELL_SIZE;
                    }
                    if(line[j]=='.'){
                        setHome.add(new Home(x0,y0));
                        x0+=Model.FIELD_CELL_SIZE;
                    }
                    if(line[j]=='&'){
                        setBox.add(new Box(x0,y0));
                        setHome.add(new Home(x0,y0));
                        x0+=Model.FIELD_CELL_SIZE;
                    }
                    if(line[j]=='@'){
                        player=new Player(x0,y0);
                        x0+=Model.FIELD_CELL_SIZE;
                    }
                }
                x0=0;
                y0+=Model.FIELD_CELL_SIZE;
            }
            else break;
        }
        return new GameObjects(setWall,setBox,setHome,player);
    }
    private void readFile(){
        try {
            cache=Files.readAllLines(levels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public GameObjects getLevel(int level) {
        {
            Set<Wall> walls = new HashSet<>();
            Set<Box> boxes = new HashSet<>();
            Set<Home> homes = new HashSet<>();
            Player player = null;

            int loopLevel;
            if (level > 60) {
                loopLevel = level % 60;
            } else {
                loopLevel = level;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
                int readLevel = 0;
                int x;
                int y = Model.FIELD_CELL_SIZE / 2;
                boolean isLevelMap = false;

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Maze:")) {
                        readLevel = Integer.valueOf(line.split(" ")[1]);
                        continue;
                    }
                    if (readLevel == loopLevel) {
                        if (line.length() == 0) {
                            boolean isEnd = isLevelMap;

                            isLevelMap = !isLevelMap;

                            if (isEnd && !isLevelMap) {
                                break;
                            } else {
                                continue;
                            }
                        }
                        if (isLevelMap) {
                            x = Model.FIELD_CELL_SIZE / 2;

                            char[] chars = line.toCharArray();
                            for (char c : chars) {
                                switch (c) {
                                    case 'X':
                                        walls.add(new Wall(x, y));
                                        break;
                                    case '*':
                                        boxes.add(new Box(x, y));
                                        break;
                                    case '.':
                                        homes.add(new Home(x, y));
                                        break;
                                    case '&':
                                        boxes.add(new Box(x, y));
                                        homes.add(new Home(x, y));
                                        break;
                                    case '@':
                                        player = new Player(x, y);
                                }
                                x += Model.FIELD_CELL_SIZE;
                            }
                            y += Model.FIELD_CELL_SIZE;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new GameObjects(walls, boxes, homes, player);
        }
    }
}
