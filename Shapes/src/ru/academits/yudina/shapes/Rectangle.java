package ru.academits.yudina.shapes;

import java.util.Objects;

public class Rectangle implements Shape {
    private double sideLength1;
    private double sideLength2;

    public Rectangle(double sideLength1, double sideLength2) {
        this.sideLength1 = sideLength1;
        this.sideLength2 = sideLength2;
    }

    public double getWidth() {
        return Math.min(sideLength1, sideLength2);
    }

    public double getHeight() {
        return Math.max(sideLength1, sideLength2);
    }

    public double getArea() {
        return sideLength1 * sideLength2;
    }

    public double getPerimeter() {
        return sideLength1 * 2 + sideLength2 * 2;
    }

    public String toString() {
        return "Shape rectangle: area = " + getArea() + ", " + "perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.sideLength1, sideLength1) == 0 && Double.compare(rectangle.sideLength2, sideLength2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideLength1, sideLength2);
    }
}
