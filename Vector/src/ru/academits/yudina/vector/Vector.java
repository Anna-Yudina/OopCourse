package ru.academits.yudina.vector;

import java.util.Arrays;

public class Vector {
    private int size;
    private  double[] vector;

    public Vector(int size){
        if (size <= 0){
            throw new IllegalArgumentException("размерность вектора должна быть > 0");
            }

        this.size = size;
        double[] vectorArray = new double[size];
        for (int i = 0; i < size; i++){
            vectorArray [i] = 0.0;
        }

        this.vector = vectorArray;
    }

    public Vector(Vector copyVector) {
        this.size = copyVector.size;
        this.vector = copyVector.vector;
    }

    public Vector(double[] vectorArray){
            this.size = vectorArray.length;
            this.vector = vectorArray;
        }


    @Override
    public String toString() {
        return "Vector{" + "размерность = " + size + ", vector =" + Arrays.toString(vector) + "}";
    }
}
