package ru.academits.yudina.shapes_main;

import ru.academits.yudina.shapes.*;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShapes(Shape[] array) {
        Arrays.sort(array, (shape1, shape2) -> (int) (shape2.getArea() - shape1.getArea()));
        return array[0];
    }

    public static Shape getSecondSizePerimeterShapes(Shape[] array) {
        Arrays.sort(array, (shape1, shape2) -> (int) (shape2.getPerimeter() - shape1.getPerimeter()));
        return array[1];
    }

    public static void main(String[] args) {
        Shape circle1 = new Circle(5);
        Shape circle2 = new Circle(6);
        Shape rectangle1 = new Rectangle(4, 2);
        Shape rectangle2 = new Rectangle(3, 4);
        Shape square1 = new Square(3);
        Shape square2 = new Square(5);
        Shape square3 = new Square(5);
        Shape triangle1 = new Triangle(1, 1, 2, 4, 6, 3);
        Shape triangle2 = new Triangle(2, 2, 3, 7, 3, 3);

        Shape[] arrayShapes = {circle1, circle2, rectangle1, rectangle2, square1, square2, square3, triangle1, triangle2};

        System.out.println("Представлен список фигур:");

        for (Shape elem : arrayShapes) {
            System.out.println(elem);
        }

        System.out.println();
        System.out.println("Фигура с максимальной площадью: " + getMaxAreaShapes(arrayShapes));
        System.out.println("Фигура со вторым по величине периметром: " + getSecondSizePerimeterShapes(arrayShapes));
    }
}
