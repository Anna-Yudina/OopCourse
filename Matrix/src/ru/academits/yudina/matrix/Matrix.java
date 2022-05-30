package ru.academits.yudina.matrix;

import ru.academits.yudina.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Переданное количество строк " + rowsCount + " или столбцов " +
                    columnsCount + " в матрице не верное. Числа должны быть > 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] matrixComponentsArray) {
        int columnsCount = 0;

        for (double[] row : matrixComponentsArray) {
            if (row.length > columnsCount) {
                columnsCount = row.length;
            }
        }

        if (columnsCount == 0){
            throw new IllegalArgumentException("Переданный двумерный массив не содержит элементов");
        }

        rows = new Vector[matrixComponentsArray.length];

        for (int i = 0; i < matrixComponentsArray.length; i++) {
            rows[i] = new Vector(columnsCount);

            for (int j = 0; j < matrixComponentsArray[i].length; j++) {
                rows[i].setElement(j, matrixComponentsArray[i][j]);
            }
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i] = matrix.getRow(i);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива векторов должен быть > 0");
        }

        int columnsCount = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > columnsCount) {
                columnsCount = vector.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(columnsCount);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                rows[i].setElement(j, vectors[i].getElement(j));
            }
        }
    }

    // получение количества строк
    public int getRowsCount() {
        return rows.length;
    }

    // получение количества столбцов
    public int getColumnsCount() {
        return rows[0].getSize();
    }

    // получение вектора-строки по индексу
    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    // задание вектора-строки по индексу
    public void setRow(int index, Vector vector) {
        checkRowIndex(index);

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер введенного вектора " + vector.getSize() +
                    " должен быть равен количеству столбцов в матрице " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    // получение вектора-столбца по индексу
    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы количества столбцов матрицы. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (getColumnsCount() - 1) + "].");
        }

        double[] array = new double[getRowsCount()];

        for (int i = 0; i < getRowsCount(); i++) {
            array[i] = rows[i].getElement(index);
        }

        return new Vector(array);
    }

    // транспонирование матрицы
    public Matrix transpose() {
        Vector[] transposedMatrixRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedMatrixRows[i] = getColumn(i);
        }

        rows = transposedMatrixRows;
        return this;
    }

    // умножение на скаляр
    public void multiplyByScalar(double number) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(number);
        }
    }

    // умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора " + vector.getSize() +
                    " и количество столбцов матрицы " + getColumnsCount() + " должны быть равны.");
        }

        Vector resultVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            resultVector.setElement(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    // сложение матриц
    public void add(Matrix matrix) {
        checkSizesEquality(this, matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    // вычитание матриц
    public void subtract(Matrix matrix) {
        checkSizesEquality(this, matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Vector vector : rows) {
            stringBuilder.append(vector);
            stringBuilder.append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы количества строк матрицы. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (getRowsCount() - 1) + "].");
        }
    }

    private static void checkSizesEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры первой матрицы " + matrix1.getRowsCount() + "x" + matrix1.getColumnsCount() +
                    " и размеры второй матрицы " + matrix2.getRowsCount() + "x" + matrix2.getColumnsCount() + " должны быть равны");
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkSizesEquality(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkSizesEquality(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);
        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы " + matrix1.getColumnsCount() +
                    " должно быть равно числу строк второй матрицы" + matrix2.getRowsCount());
        }

        Vector[] resultMatrixRows = new Vector[matrix1.getRowsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            resultMatrixRows[i] = new Vector(matrix2.getColumnsCount());

            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                double element = 0;

                for (int k = 0; k < matrix2.getRowsCount(); k++) {
                    element += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }

                resultMatrixRows[i].setElement(j, element);
            }
        }

        return new Matrix(resultMatrixRows);
    }

    // вычисление определителя матрицы
    private static double getDeterminant(Matrix matrix) {
        if (matrix.getRowsCount() == 1) {
            return matrix.rows[0].getElement(0);
        }

        if (matrix.getRowsCount() == 2) {
            return matrix.rows[0].getElement(0) * matrix.rows[1].getElement(1) -
                    matrix.rows[1].getElement(0) * matrix.rows[0].getElement(1);
        }

        double determinant = 0;

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            determinant += Math.pow(-1, i) * matrix.rows[0].getElement(i) * getDeterminant(getMinor(i, matrix));
        }

        return determinant;
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount() || getRowsCount() == 0) {
            throw new UnsupportedOperationException("Размеры матрицы " + getRowsCount() + "x" + getColumnsCount() +
                    ". Матрица не является квадратной. Определитель не может быть получен.");
        }

        return getDeterminant(this);
    }

    private static Matrix getMinor(int deletedColumnIndex, Matrix matrix) {
        int matrixRowsCount = matrix.getRowsCount();
        Matrix resultMatrix = new Matrix(matrixRowsCount - 1, matrixRowsCount - 1);

        for (int i = 1; i < matrixRowsCount; i++) {
            double[] numbers = new double[matrixRowsCount - 1];
            int k = 0;

            for (int j = 0; j < matrixRowsCount; j++) {
                if (j == deletedColumnIndex) {
                    continue;
                }

                numbers[k] = matrix.rows[i].getElement(j);
                k++;
            }

            resultMatrix.rows[i - 1] = new Vector(matrixRowsCount - 1, numbers);
        }

        return resultMatrix;
    }
}