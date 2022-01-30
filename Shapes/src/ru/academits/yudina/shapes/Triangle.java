package ru.academits.yudina.shapes;

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

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
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

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    @Override
    public String toString() {
        return "Треугольник: [(" + x1 + "," + y1 + "),(" + x2 + "," + y2 + "),(" + x3 + "," + y3 + ")]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        double epsilon = 1.0e-10;
        Triangle triangle = (Triangle) o;
        return Math.abs(x1 - triangle.x1) <= epsilon && Math.abs(y1 - triangle.y1) <= epsilon
                && Math.abs(x2 - triangle.x2) <= epsilon && Math.abs(y2 - triangle.y2) <= epsilon
                && Math.abs(x3 - triangle.x3) <= epsilon && Math.abs(y3 - triangle.y3) <= epsilon;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + (int) x1 * 1000;
        hash = prime * hash + (int) y1 * 1000;
        hash = prime * hash + (int) x2 * 1000;
        hash = prime * hash + (int) y2 * 1000;
        hash = prime * hash + (int) x3 * 1000;
        hash = prime * hash + (int) y3 * 1000;
        return hash;
    }
}