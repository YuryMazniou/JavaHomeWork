package by.it.mazniou.pattern.decorator;

import by.it.mazniou.pattern.decorator.decorators.RedShapeDecorator;
import by.it.mazniou.pattern.decorator.shapes.Circle;
import by.it.mazniou.pattern.decorator.shapes.Rectangle;
import by.it.mazniou.pattern.decorator.shapes.Shape;

/*
Decorator
*/
public class Solution {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("Simple circle");
        circle.draw();

        System.out.println("\nApplied RedShapeDecorator to the circle");
        redCircle.draw();

        System.out.println("\nApplied RedShapeDecorator to the rectangle");
        redRectangle.draw();
    }
}
