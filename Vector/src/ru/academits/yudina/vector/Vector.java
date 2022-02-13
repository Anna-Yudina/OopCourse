package ru.academits.yudina.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Введенный размер " + size + "не верный. Размерность вектора должна быть > 0");
        }

        double[] vectorArray = new double[size];

        for (int i = 0; i < size; i++) {
            vectorArray[i] = 0;
        }

        this.elements = vectorArray;
    }

    public Vector(Vector copyVector) {
        this.elements = copyVector.elements;
    }

    public Vector(double[] vectorArray) {
        this.elements = vectorArray;
    }

    public Vector(int size, double[] vectorArray) {
        if (size <= 0) {
            throw new IllegalArgumentException("Введенный размер " + size + "не верный. Размерность вектора должна быть > 0");
        }

        double[] tempArray = new double[size];
        int temp = Math.min(size, vectorArray.length);

        for (int i = 0; i < temp; i++) {
            tempArray[i] = vectorArray[i];
        }

        this.elements = tempArray;
    }

    public int getSize() {
        return elements.length;
    }

    public double[] getElements() {
        return elements;
    }

    public void setElements(double[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String string = "{";

        for (int i = 0; i < elements.length - 1; i++) {
            string += elements[i] + ", ";
        }

        string += elements[elements.length - 1] + "}";
        return string;
    }

    public void add(Vector vector) {
        int min = Math.min(elements.length, vector.elements.length);

        for (int i = 0; i < min; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        int min = Math.min(elements.length, vector.elements.length);

        for (int i = 0; i < min; i++) {
            elements[i] = elements[i] - vector.elements[i];
        }
    }

    public void multiplicationByScalar(int number) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = number * elements[i];
        }
    }

    public void reverse() {
        multiplicationByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (int i = 0; i < elements.length; i++) {
            sum += elements[i] * elements[i];
        }

        return Math.sqrt(sum);
    }

    public double getElementByIndex(int index) {
        if (index >= elements.length || index < 0) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размерности вектора;\n" +
                    "допустимые значения должны быть в диапазоне [0, " + (elements.length - 1) + "].");
        }

        return elements[index];
    }

    public void setComponentByIndex(int index, double number) {
        if (index >= elements.length || index < 0) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размерности вектора;\n" +
                    "допустимые значения должны быть в диапазоне [0, " + (elements.length - 1) + "].");
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
        return elements.length == vector.elements.length && Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        int result = elements.length;
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int max = Math.max(vector1.elements.length, vector2.elements.length);
        int min = Math.min(vector1.elements.length, vector2.elements.length);
        double[] sumArray = new double[max];

        if (vector1.elements.length > vector2.elements.length) {
            vector1.add(vector2);
            return new Vector(vector1);
        }

        for (int i = min; i < max; i++) {
            sumArray[i] = vector2.elements[i];
        }

        for (int i = 0; i < min; i++) {
            sumArray[i] = vector1.elements[i] + vector2.elements[i];
        }

        return new Vector(sumArray);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int max = Math.max(vector1.elements.length, vector2.elements.length);
        int min = Math.min(vector1.elements.length, vector2.elements.length);
        double[] differenceArray = new double[max];

        if (vector1.elements.length > vector2.elements.length) {
            vector1.subtract(vector2);
            return new Vector(vector1);
        }

        for (int i = min; i < max; i++) {
            differenceArray[i] = -vector2.elements[i];
        }

        for (int i = 0; i < min; i++) {
            differenceArray[i] = vector1.elements[i] - vector2.elements[i];
        }

        return new Vector(differenceArray);
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