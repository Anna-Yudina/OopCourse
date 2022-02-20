package ru.academits.yudina.shapes_main;

import ru.academits.yudina.shapes.*;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShape(Shape[] array) {
        if (array.length == 0) {
            return null;
        }

        Arrays.sort(array, new ShapeAreaComparator());
        return array[array.length - 1];
    }

    public static Shape getSecondSizePerimeterShape(Shape[] array) {
        if (array.length <= 1) {
            return null;
        }

        Arrays.sort(array, new ShapePerimeterComparator());
        return array[array.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapesArray = {
                new Circle(5.5),
                new Circle(5.6),
                new Rectangle(4, 2),
                new Rectangle(3, 4),
                new Square(3),
                new Square(5),
                new Square(5),
                new Triangle(1.1, 1, 2, 4, 6, 3),
                new Triangle(1.1, 1, 2, 4, 6, 3)
        };

        System.out.println("Представлен список фигур:");

        for (Shape shape : shapesArray) {
            System.out.printf(shape + "; площадь %.2f ; периметр %.2f %n", shape.getArea(), shape.getPerimeter());
        }

        System.out.println();
        System.out.println("Фигура с максимальной площадью: " + getMaxAreaShape(shapesArray));
        System.out.println("Фигура со вторым по величине периметром: " + getSecondSizePerimeterShape(shapesArray));
    }
}