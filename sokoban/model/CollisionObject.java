package by.it.mazniou.sokoban.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }
    public boolean isCollision(GameObject gameObject, Direction direction){
        boolean result=false;
        if(direction==Direction.DOWN){
            if(getY()+Model.FIELD_CELL_SIZE==gameObject.getY()&&getX()==gameObject.getX())result=true;
        }
        if(direction==Direction.UP){
            if(getY()-Model.FIELD_CELL_SIZE==gameObject.getY()&&getX()==gameObject.getX())result=true;
        }
        if(direction==Direction.LEFT){
            if(getX()-Model.FIELD_CELL_SIZE==gameObject.getX()&&getY()==gameObject.getY())result=true;
        }
        if(direction==Direction.RIGHT){
            if(getX()+Model.FIELD_CELL_SIZE==gameObject.getX()&&getY()==gameObject.getY())result=true;
        }
        return result;
    }
}
