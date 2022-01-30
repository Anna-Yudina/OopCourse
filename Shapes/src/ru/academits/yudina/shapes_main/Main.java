package ru.academits.yudina.shapes_main;

import ru.academits.yudina.comparator.MaxAreaShape;
import ru.academits.yudina.comparator.MaxPerimeterShape;
import ru.academits.yudina.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static Shape getMaxAreaShape(Shape[] array) {
        if (array.length == 0) {
            return null;
        }

        Comparator maxAreaShape = new MaxAreaShape();
        Arrays.sort(array, maxAreaShape);
        return array[0];
    }

    public static Shape getSecondSizePerimeterShape(Shape[] array) {
        if (array.length == 0) {
            return null;
        }

        Comparator maxPerimeterShape = new MaxPerimeterShape();
        Arrays.sort(array, maxPerimeterShape);
        return array[1];
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
                new Triangle(1.1, 1, 2, 4, 6, 3),
        };

        System.out.println("Представлен список фигур:");

        for (Shape shape : shapesArray) {
            System.out.print(shape);
            System.out.printf("; площадь %.2f ; периметр %.2f %n", shape.getArea(), shape.getPerimeter());
        }

        System.out.println();
        System.out.println("Фигура с максимальной площадью: " + getMaxAreaShape(shapesArray));
        System.out.println("Фигура со вторым по величине периметром: " + getSecondSizePerimeterShape(shapesArray));
    }
}