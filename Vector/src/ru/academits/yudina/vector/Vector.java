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
            vectorArray[i] = 0.0;
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
        double[] testArray = new double[size];
        int temp = 0;
        if (size < vectorArray.length){
            temp = size;
        } else {
            temp = vectorArray.length;
        }
       for (int i = 0; i < temp; i++){
           testArray[i] = vectorArray[i];
       }
        this.vectorArray = testArray;
    }

    public int getVectorSize() {
        return vectorSize;
    }

    @Override
    public String toString() {
        return "Vector{" + "размерность = " + vectorSize + ", vector =" + Arrays.toString(vectorArray) + "}";
    }

    public void getSum(Vector vector){
        if (vectorSize > vector.vectorSize){
            double[] testArray = new double[vectorSize];
            for (int i = 0; i < vectorSize; i++){
                testArray[i] = vectorArray[i];
            }

        } else {
            double[] testArray = new double[vector.vectorSize];
            for (int i = 0; i < vector.vectorSize; i++){
                testArray[i] = vectorArray[i];
            }
        }
    }
}
