package ru.academits.yudina.matrix_main;


import ru.academits.yudina.matrix.Matrix;
import ru.academits.yudina.vector.Vector;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создаем матрицу нулей размера 4*7:");
        Matrix matrix1 = new Matrix(4, 7);
        System.out.println(matrix1);

        System.out.println("_______________");
        System.out.println("Создаем матрицу из двумерного массива:");
        double[][] dimension1 = {
                {5, 7, 3, 17},
                {7, 0, 1, 12},
                {8, 1, 2, 3}
        };
        Matrix matrix2 = new Matrix(dimension1);
        System.out.println(matrix2);

        System.out.println("_______________");
        System.out.println("Создаем матрицу с помощью конструктора копирования:");
        Matrix matrix3 = new Matrix(matrix2);
        System.out.println(matrix3);

        System.out.println("_______________");
        System.out.println("Создаем матрицу из массива векторов-строк:");
        Vector[] vectors1 = new Vector[3];
        vectors1[0] = new Vector(new double[]{1, 2, 3, 4});
        vectors1[1] = new Vector(new double[]{5, 6, 7, 8});
        vectors1[2] = new Vector(new double[]{9, 10, 11, 12});

        Matrix matrix4 = new Matrix(vectors1);
        System.out.println(matrix4);

        System.out.println("_______________");
        System.out.println("Проверяем метод String getMatrixSize():");
        System.out.println("Размеры матрицы: " + matrix2.getMatrixSize());

        System.out.println("_______________");
        System.out.println("Проверяем метод Vector getVectorLine(int index):");
        System.out.println("Матрица: " + matrix2);
        Vector vector1 = matrix2.getVectorLine(2);
        System.out.println(vector1);

        System.out.println("_______________");
        System.out.println("Проверяем метод Vector getVectorColumn(int index):");
        System.out.println("Матрица: " + matrix2);
        Vector vector2 = matrix2.getVectorColumn(1);
        System.out.println("Получили вектор-столбец по индексу 1: " + vector2);

        System.out.println("_______________");
        System.out.println("Проверяем метод multiplyByScalar(double number):");
        System.out.println("Матрица: " + matrix2);
        matrix2.multiplyByScalar(2);
        System.out.println("Матрица после умножения на скаляр 2: " + matrix2);

        System.out.println("_______________");
        System.out.println("Проверяем метод void setVectorLine(int index, Vector vector):");
        System.out.println("Матрица: " + matrix2);
        Vector vector3 = new Vector(new double[]{5, 7, 8, 3});
        System.out.println("Вектор: " + vector3);
        matrix2.setVectorLine(1, vector3);
        System.out.println("Матрица после вставки вектора по индексу 1: " + matrix2);

        System.out.println("_______________");
        System.out.println("Проверяем метод Vector multiplyMatrixAndVector(Vector vector):");
        Vector vector4 = new Vector(new double[]{2, 3, 4, 6});
        System.out.println("Матрица: " + matrix2);
        System.out.println("Вектор: " + vector4);
        Vector vector5 = matrix2.multiplyMatrixAndVector(vector4);
        System.out.println("Результат перемножения матрицы на вектор: " + vector5);

        System.out.println("___________________");
        System.out.println("Проверка метода transpose():");
        System.out.println("Транспонируем матрицу: " + matrix2);
        Matrix matrix5 = matrix2.transpose();
        System.out.println("Полученный результат: " + matrix5);

        System.out.println("___________________");
        System.out.println("Проверка метода void add(Matrix matrix):");
        System.out.println("Первая матрица: " + matrix2);
        System.out.println("Вторая матрица: " + matrix4);
        matrix2.add(matrix4);
        System.out.println("Получившийся результат сложения двух матриц: " + matrix2);

        System.out.println("Проверка метода add, если матрицы не равны:");

        double[][] dimension2 = {
                {5, 7, 3},
                {7, 0, 1},
                {8, 1, 2},
                {4, 8, 0},
                {9, 6, 5}
        };

        Matrix matrix6 = new Matrix(dimension2);
        System.out.println("Первая матрица: " + matrix2);
        System.out.println("Вторая матрица: " + matrix6);
        matrix2.add(matrix6);
        System.out.println("Результат после сложения: " + matrix2);

        System.out.println("_______________");
        System.out.println("Проверяем метод void subtract (Matrix matrix):");
        System.out.println("Первая матрица: " + matrix3);
        System.out.println("Вторая матрица: " + matrix6);
        matrix3.subtract(matrix6);
        System.out.println("Результат вычитания: " + matrix3);

        System.out.println("_______________");
        System.out.println("Проверяем статический метод getSum:");
        System.out.println("Первая матрица: " + matrix2);
        System.out.println("Вторая матрица: " + matrix6);
        Matrix resultMatrix1 = Matrix.getSum(matrix2, matrix6);
        System.out.println("Результат после сложения: " + resultMatrix1);

        System.out.println("_______________");
        System.out.println("Проверяем статический метод getDifference:");
        System.out.println("Первая матрица: " + matrix3);
        System.out.println("Вторая матрица: " + matrix6);
        Matrix resultMatrix2 = Matrix.getDifference(matrix3, matrix6);
        System.out.println("Результат вычитания: " + resultMatrix2);

        double [][] twoDimArray = {{5,7,3}, {7,0}, {8,1,2,3}};
        System.out.println(Arrays.deepToString(twoDimArray));
        Matrix matrix7 = new Matrix(twoDimArray);
    }
}
