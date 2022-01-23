package ru.academits.yudina.shapes;

import java.util.Objects;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getWidth() {
        return Math.max((Math.max(x1, x2)), x3) - Math.min((Math.min(x1, x2)), x3);
    }

    public double getHeight() {
        return Math.max((Math.max(y1, y2)), y3) - Math.min((Math.min(y1, y2)), y3);
    }

    public double getArea() {
        double sideLength1 = getSideLength(x1, y1, x2, y2);
        double sideLength2 = getSideLength(x2, y2, x3, y3);
        double sideLength3 = getSideLength(x1, y1, x3, y3);
        double halfPerimeter = (sideLength1 + sideLength2 + sideLength3) / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - sideLength1) * (halfPerimeter - sideLength2) * (halfPerimeter - sideLength3));
    }

    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2) + getSideLength(x2, y2, x3, y3) + getSideLength(x1, y1, x3, y3);
    }

    public double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public String toString() {
        return "Shape triangle: area = " + getArea() + ", " + "perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x1, x1) == 0 && Double.compare(triangle.y1, y1) == 0 && Double.compare(triangle.x2, x2) == 0 && Double.compare(triangle.y2, y2) == 0 && Double.compare(triangle.x3, x3) == 0 && Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}
