package ru.academits.yudina.vector;

import java.util.Arrays;

public class Vector {
    private int vectorSize;
    private double[] vectorArray;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("размерность вектора должна быть > 0");
        }

        this.vectorSize = size;
        double[] vectorArray = new double[size];
        for (int i = 0; i < size; i++) {
            vectorArray[i] = 0;
        }

        this.vectorArray = vectorArray;
    }

    public Vector(Vector copyVector) {
        this.vectorSize = copyVector.vectorSize;
        this.vectorArray = copyVector.vectorArray;
    }

    public Vector(double[] vectorArray) {
        this.vectorSize = vectorArray.length;
        this.vectorArray = vectorArray;
    }

    public Vector(int size, double[] vectorArray) {
        if (size <= 0) {
            throw new IllegalArgumentException("размерность вектора должна быть > 0");
        }

        this.vectorSize = size;
        double[] tempArray = new double[size];
        int temp = Math.min(size, vectorArray.length);

        for (int i = 0; i < temp; i++) {
            tempArray[i] = vectorArray[i];
        }
        this.vectorArray = tempArray;
    }

    public int getVectorSize() {
        return vectorSize;
    }

    public double[] getVectorArray() {
        return vectorArray;
    }

    public void setVectorSize(int vectorSize) {
        this.vectorSize = vectorSize;
    }

    public void setVectorArray(double[] vectorArray) {
        this.vectorArray = vectorArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(vectorArray);
    }

    public double[] getSum(Vector vector) {
        int min = Math.min(vectorSize, vector.vectorSize);

        for (int i = 0; i < min; i++) {
            vectorArray[i] = vectorArray[i] + vector.vectorArray[i];
        }

        return vectorArray;
    }

    public double[] getDifference(Vector vector) {
        int min = Math.min(vectorSize, vector.vectorSize);

        for (int i = 0; i < min; i++) {
            vectorArray[i] = vectorArray[i] - vector.vectorArray[i];
        }

        return vectorArray;
    }

    public double[] getMultiplicationByScalar(int number) {
        double[] multiplicationByScalarArray = new double[vectorSize];

        for (int i = 0; i < vectorSize; i++) {
            multiplicationByScalarArray[i] = number * vectorArray[i];
        }

        return multiplicationByScalarArray;
    }

    public double[] getReverseVector() {
        double[] reverseArray = new double[vectorSize];

        for (int i = 0; i < vectorSize; i++) {
            reverseArray[i] = vectorArray[i] * (-1);
        }

        return reverseArray;
    }

    public double getVectorLength() {
        double sum = 0;

        for (int i = 0; i < vectorSize; i++) {
            sum = sum + vectorArray[i] * vectorArray[i];
        }

        return Math.sqrt(sum);
    }

    public double getComponentVectorByIndex(int index) {
        if (index > vectorSize) {
            throw new IllegalArgumentException("Индекс выходит за пределы размерности вектора");
        }

        return vectorArray[index];
    }

    public void setComponentVectorByIndex(int index, double number) {
        if (index > vectorSize) {
            throw new IllegalArgumentException("Индекс выходит за пределы размерности вектора");
        }

        vectorArray[index] = number;
        System.out.println("установка компоненты прошла успешно");
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
        return vectorSize == vector.vectorSize && Arrays.equals(vectorArray, vector.vectorArray);
    }

    @Override
    public int hashCode() {
        int result = vectorSize;
        result = 31 * result + Arrays.hashCode(vectorArray);
        return result;
    }

    public static double[] getSumTwoVectors(Vector vector1, Vector vector2) {
        int max = Math.max(vector1.vectorSize, vector2.vectorSize);
        int min = Math.min(vector1.vectorSize, vector2.vectorSize);
        double[] testArray = new double[max];

        if (vector1.vectorSize > vector2.vectorSize) {
            testArray = vector1.getSum(vector2);
            return testArray;
        }

        for (int i = min; i < max; i++) {
            testArray[i] = vector2.vectorArray[i];
        }

        for (int i = 0; i < min; i++) {
            testArray[i] = vector1.vectorArray[i] + vector2.vectorArray[i];
        }
        
        return testArray;
    }

    public static double[] getDifference(Vector vector1, Vector vector2) {
        int max = Math.max(vector1.vectorSize, vector2.vectorSize);
        int min = Math.min(vector1.vectorSize, vector2.vectorSize);
        double[] testArray = new double[max];

        if (vector1.vectorSize > vector2.vectorSize) {
            testArray = vector1.getDifference(vector2);
            return testArray;
        }

        for (int i = min; i < max; i++) {
            testArray[i] = vector2.vectorArray[i] * (-1);
        }

        for (int i = 0; i < min; i++) {
            testArray[i] = vector1.vectorArray[i] - vector2.vectorArray[i];
        }

        return testArray;
    }

    public static double[] getScalarMultiplication(Vector vector1, Vector vector2) {
        int max = Math.max(vector1.vectorSize, vector2.vectorSize);
        int min = Math.min(vector1.vectorSize, vector2.vectorSize);
        double[] testArray = new double[max];

        for (int i = 0; i < min; i++) {
            testArray[i] = vector1.vectorArray[i] * vector2.vectorArray[i];
        }

        return testArray;
    }
}