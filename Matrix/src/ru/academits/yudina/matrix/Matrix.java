package ru.academits.yudina.matrix;

import ru.academits.yudina.vector.Vector;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int line, int column) {
        if (line <= 0) {
            throw new IllegalArgumentException("Введенное количество строк в матрице " + line + " не верное. Число должно быть > 0");
        }

        vectors = new Vector[line];

        for (int i = 0; i < line; i++) {
            vectors[i] = new Vector(column);
        }
    }

    public Matrix(double[][] dimension) {
        if (dimension.length == 0) {
            throw new IllegalArgumentException("Размер введенного двумерного массива должен быть > 0");
        }

        int column = 0;

        for (double[] doubles : dimension) {
            if (doubles.length > column) {
                column = doubles.length;
            }
        }

        vectors = new Vector[dimension.length];

        for (int i = 0; i < dimension.length; i++) {
            vectors[i] = new Vector(column);

            for (int j = 0; j < dimension[i].length; j++) {
                vectors[i].setElement(j, dimension[i][j]);
            }
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];
        System.arraycopy(matrix.vectors, 0, vectors, 0, matrix.vectors.length);
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размер введенного массива векторов должен быть > 0");
        }

        int column = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > column) {
                column = vector.getSize();
            }
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(column);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                this.vectors[i].setElement(j, vectors[i].getElement(j));
            }
        }
    }

    // получение размеров матрицы
    public String getMatrixSize() {
        return vectors.length + "x" + vectors[0].getSize();
    }

    // получение вектора-строки по индексу
    public Vector getVectorLine(int index) {
        checkIndex(index);

        return vectors[index];
    }

    // задание вектора-строки по индексу
    public void setVectorLine(int index, Vector vector) {
        checkIndex(index);

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
    public Matrix transpose() {

        Matrix transposeMatrix = new Matrix(vectors[0].getSize(), vectors.length);

        for (int i = 0; i < vectors[0].getSize(); i++) {
            Vector vector = this.getVectorColumn(i);
            transposeMatrix.setVectorLine(i, vector);
        }

        this.vectors = transposeMatrix.vectors;
        return this;
    }

    // умножение на скаляр
    public void multiplyByScalar(double number) {
        for (Vector vector : vectors) {
            vector.multiplyByScalar(number);
        }
    }

    // умножение матрицы на вектор
    public Vector multiplyMatrixAndVector(Vector vector) {
        if (vectors[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора должен быть = " + vectors[0].getSize());
        }

        Vector newVector = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; i++) {
            double sum = 0;

            for (int j = 0; j < vectors[i].getSize(); j++) {
                sum = sum + vectors[i].getElement(j) * vector.getElement(j);
            }

            newVector.setElement(i, sum);
        }

        return newVector;
    }

    // сложение матриц
    public void add(Matrix matrix) {
        if (matrix.vectors.length != vectors.length && matrix.vectors[0].getSize() != vectors[0].getSize()) {
            increaseMatrix(matrix);
        }

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i].add(matrix.vectors[i]);
        }
    }

    // метод увеличения текущей матрицы в зависимости от размеров входящей матрицы
    public void increaseMatrix(Matrix matrix) {
        int inputMatrixLine = matrix.vectors.length;
        int inputMatrixColumn = matrix.vectors[0].getSize();
        int currentMatrixLine = vectors.length;
        int currentMatrixColumn = vectors[0].getSize();

        Matrix increasedMatrix;

        if (inputMatrixLine > currentMatrixLine) {
            if (inputMatrixColumn > currentMatrixColumn) {
                increasedMatrix = getIncreasedMatrix(inputMatrixLine, inputMatrixColumn);
            } else {
                increasedMatrix = getIncreasedMatrix(inputMatrixLine, currentMatrixColumn);
            }
        } else {
            if (inputMatrixColumn > currentMatrixColumn) {
                increasedMatrix = getIncreasedMatrix(currentMatrixLine, inputMatrixColumn);
            } else {
                return;
            }
        }

        this.vectors = increasedMatrix.vectors;
    }

    // вспомогательный метод для получения увеличенной матрицы до размеров, переданных в аргументе
    private Matrix getIncreasedMatrix(int line, int column) {
        Matrix increasedMatrix = new Matrix(line, column);

        for (int i = 0; i < vectors.length; i++) {
            increasedMatrix.vectors[i] = new Vector(column);
            double[] numbers = new double[column];

            for (int j = 0; j < vectors[0].getSize(); j++) {
                numbers[j] = vectors[i].getElement(j);
            }

            increasedMatrix.vectors[i] = new Vector(column, numbers);
        }

        return increasedMatrix;
    }

    // вычитание матриц
    public void subtract(Matrix matrix) {
        if (matrix.vectors.length != vectors.length && matrix.vectors[0].getSize() != vectors[0].getSize()) {
            increaseMatrix(matrix);
        }

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i].subtract(matrix.vectors[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Vector vector : vectors) {
            stringBuilder.append(vector);
        }

        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= vectors.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности массива векторов. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (vectors.length - 1) + "].");
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);
        return resultMatrix;
    }

    public static Matrix getMultiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors[0].getSize() != matrix2.vectors.length) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу столбцов второй матрицы");
        }

        Matrix resultMatrix = new Matrix(matrix2.vectors[0].getSize(), matrix1.vectors.length);

        for (int i = 0; i < matrix2.vectors[0].getSize(); i++) {
            resultMatrix.vectors[i] = matrix1.multiplyMatrixAndVector(matrix2.getVectorColumn(i));
        }

        return resultMatrix.transpose();
    }
    
    // вычисление определителя матрицы
    // TODO создать метод
}
