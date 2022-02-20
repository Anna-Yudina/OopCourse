package ru.academits.yudina.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Введенный размер " + size + " не верный. Размер вектора должен быть > 0");
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(double[] array) {
        elements = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Введенный размер " + size + " не верный. Размер вектора должен быть > 0");
        }

        elements = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < elements.length - 1; i++) {
            stringBuilder.append(elements[i]).append(", ");
        }

        stringBuilder.append(elements[elements.length - 1]).append("}");
        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (elements.length > vector.elements.length) {
            for (int i = 0; i < vector.elements.length; i++) {
                elements[i] += vector.elements[i];
            }
        } else {
            double[] tempArray = new double[vector.elements.length];

            for (int i = 0; i < elements.length; i++) {
                tempArray[i] = elements[i] + vector.elements[i];
            }

            System.arraycopy(vector.elements, elements.length, tempArray, elements.length, vector.elements.length - elements.length);
            elements = tempArray;
        }
    }

    public void subtract(Vector vector) {
        if (elements.length > vector.elements.length) {
            for (int i = 0; i < vector.elements.length; i++) {
                elements[i] -= vector.elements[i];
            }
        } else {
            double[] tempArray = new double[vector.elements.length];

            for (int i = 0; i < elements.length; i++) {
                tempArray[i] = elements[i] - vector.elements[i];
            }

            System.arraycopy(vector.elements, elements.length, tempArray, elements.length, vector.elements.length - elements.length);
            elements = tempArray;
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= number;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double element : elements) {
            sum += element * element;
        }

        return Math.sqrt(sum);
    }

    public double getElement(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размерности вектора. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (elements.length - 1) + "].");
        }

        return elements[index];
    }

    public void setElement(int index, double number) {
        if (index < 0 || index >= elements.length) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размерности вектора. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (elements.length - 1) + "].");
        }

        elements[index] = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        return 31 + Arrays.hashCode(elements);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        vector1.add(vector2);
        return new Vector(vector1);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        vector1.subtract(vector2);
        return new Vector(vector1);
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int min = Math.min(vector1.elements.length, vector2.elements.length);
        double scalarProduct = 0;

        for (int i = 0; i < min; i++) {
            scalarProduct += vector1.elements[i] * vector2.elements[i];
        }

        return scalarProduct;
    }
}