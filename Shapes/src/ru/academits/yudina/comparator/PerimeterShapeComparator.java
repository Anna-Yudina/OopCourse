package ru.academits.yudina.comparator;

import ru.academits.yudina.shapes.Shape;

import java.util.Comparator;

public class PerimeterShapeComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}
