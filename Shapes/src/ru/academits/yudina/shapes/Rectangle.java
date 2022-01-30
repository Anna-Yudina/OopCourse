package ru.academits.yudina.shapes;

public class Rectangle implements Shape {
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getArea() {
        return height * width;
    }

    public double getPerimeter() {
        return 2 * (height + width);
    }

    @Override
    public String toString() {
        return "Прямоугольник: " + height + ", " + width;
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
        Rectangle rectangle = (Rectangle) o;
        return Math.abs(height - rectangle.height) <= epsilon && Math.abs(width = rectangle.width) <= epsilon;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + (int) height * 1000;
        hash = prime * hash + (int) width * 1000;
        return hash;
    }
}