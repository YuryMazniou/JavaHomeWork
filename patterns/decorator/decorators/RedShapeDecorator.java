package by.it.mazniou.pattern.decorator.decorators;


import by.it.mazniou.pattern.decorator.shapes.Shape;

public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        setBorderColor(decoratedShape);
        super.draw();
    }
    private void setBorderColor(Shape decoratedShape){
        System.out.println(String.format("Setting border color for %s to red.",decoratedShape.getClass().getSimpleName()));
    }
}
