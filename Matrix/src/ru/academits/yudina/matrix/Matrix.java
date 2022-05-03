package ru.academits.yudina.matrix;

import ru.academits.yudina.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int line, int column) {
        vectors = new Vector[line];

        for (int i = 0; i < line; i++) {
            vectors[i] = new Vector(column);
        }
    }

    public Matrix(double[][] dimension) {
        vectors = new Vector[dimension.length];

        for (int i = 0; i < dimension.length; i++) {
            vectors[i] = new Vector(dimension[i]);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];
        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i] = matrix.vectors[i];
        }
    }

    public Matrix(Vector[] vectors) {
        this.vectors = vectors;
    }

    // получение размеров матрицы
    public String getMatrixSize() {
        return vectors.length + "x" + vectors[0].getSize();
    }

    // получение вектора-строки по индексу
    public Vector getVectorLine(int index) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности массива векторов. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (vectors.length - 1) + "].");
        }

        return vectors[index];
    }

    // задание вектора-строки по индексу
    public void setVectorLine(int index, Vector vector) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности массива векторов. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (vectors.length - 1) + "].");
        }

        vectors[index] = vector;
    }

    // получение вектора-столбца по индексу
    public Vector getVectorColumn(int index) {
        if (index < 0 || index >= vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности массива векторов. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (vectors[0].getSize() - 1) + "].");
        }

        double[] array = new double[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            array[i] = vectors[i].getElement(index);
        }

        return new Vector(array);
    }

    // транспонирование матрицы
    // TODO дописать метод, транспонирование - это столбцы делаем строками и наоборот
    public Vector[] transpose(){
        if (vectors.length < vectors[0].getSize()){
            for (int i = vectors.length; i < vectors[0].getSize(); i++){
                vectors[i] = new Vector(vectors[0].getSize());
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            for (int j = i; j < vectors.length; j++) {
                double tmp = vectors[j].getElement(i);
                vectors[j].setElement(i, vectors[i].getElement(j));
                vectors[i].setElement(j, tmp);
            }
        }
        return vectors;
    }

    // умножение на скаляр
    public void multiplyByScalar(double number) {
        for (int i = 0; i < vectors.length; i++) {
            vectors[i].multiplyByScalar(number);
        }
    }

    // вычисление определителя матрицы
    // TODO создать метод

    // умножение матрицы на вектор
    public Vector multiplyMatrixAndVector(Vector vector) {
        Vector newVector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            double sum = 0;

            for (int j = 0; j < vectors[i].getSize(); j++) {
                sum = sum + vectors[i].getElement(j)*vector.getElement(j);
            }

            newVector.setElement(i, sum);
        }

        return newVector;
    }

    // сложение матриц
    public void add (Matrix matrix){
        for (int i = 0; i< vectors.length; i++){
            vectors[i].add(matrix.vectors[i]);
        }
    }

    // вычитание матриц
    public void subtract (Matrix matrix){
        for (int i = 0; i< vectors.length; i++){
            vectors[i].subtract(matrix.vectors[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < vectors.length; i++) {
            stringBuilder.append(vectors[i]);
        }

        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
