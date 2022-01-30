package ru.academits.yudina.comparator;

import ru.academits.yudina.shapes.Shape;

import java.util.Comparator;

public class MaxPerimeterShape implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) ((shape2.getPerimeter() - shape1.getPerimeter()) * 100);
    }
}
