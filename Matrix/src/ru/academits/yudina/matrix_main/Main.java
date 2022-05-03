package ru.academits.yudina.matrix_main;


import ru.academits.yudina.matrix.Matrix;
import ru.academits.yudina.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(4, 7);
        System.out.println(matrix1);

        double[][] dimension = {
                {5, 7, 3, 17},
                {7, 0, 1, 12},
                {8, 1, 2, 3}
        };
        Matrix matrix2 = new Matrix(dimension);
        System.out.println(matrix2);

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println(matrix3);

        Vector[] vectors1 = new Vector[3];
        vectors1[0] = new Vector(new double[]{1, 2, 3, 4});
        vectors1[1] = new Vector(new double[]{5, 6, 7, 8});
        vectors1[2] = new Vector(new double[]{9, 10, 11, 12});

        Matrix matrix4 = new Matrix(vectors1);
        System.out.println(matrix4);

        System.out.println("Размеры матрицы: " + matrix2.getMatrixSize());

        Vector vector1 = matrix2.getVectorLine(1);
        System.out.println(vector1);

        Vector vector2 = matrix2.getVectorColumn(1);
        System.out.println(vector2);

        matrix2.multiplyByScalar(2);
        System.out.println(matrix2);

        Vector vector3 = new Vector(new double[]{5, 7, 8, 3});
        matrix2.setVectorLine(1, vector3);
        System.out.println(matrix2);

        Vector vector4 = new Vector(new double[]{2, 3, 4, 6});
        Vector vector5 = matrix2.multiplyMatrixAndVector(vector4);
        System.out.println(vector5);

        System.out.println(matrix2);
        System.out.println(matrix4);
        matrix2.subtract(matrix4);
        System.out.println(matrix2);

        System.out.println("___________________");
        System.out.println(matrix2);
        matrix2.transpose();
        System.out.println(matrix2);
    }
}
