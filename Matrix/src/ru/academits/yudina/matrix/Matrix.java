package ru.academits.yudina.matrix;

import ru.academits.yudina.vector.Vector;

public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int rowCount, int columnCount) {
        if (rowCount <= 0 || columnCount <= 0) {
            throw new IllegalArgumentException("Переданное количество строк " + rowCount + " или столбцов " +
                    columnCount + " в матрице не верное. Числа должны быть > 0");
        }

        matrixRows = new Vector[rowCount];

        for (int i = 0; i < rowCount; i++) {
            matrixRows[i] = new Vector(columnCount);
        }
    }

    public Matrix(double[][] matrixComponentArray) {
        if (matrixComponentArray.length == 0 || matrixComponentArray[0].length == 0) {
            throw new IllegalArgumentException("Размеры переданного двумерного массива должны быть > 0");
        }

        int columnCount = 0;

        for (double[] doubles : matrixComponentArray) {
            if (doubles.length > columnCount) {
                columnCount = doubles.length;
            }
        }

        matrixRows = new Vector[matrixComponentArray.length];

        for (int i = 0; i < matrixComponentArray.length; i++) {
            matrixRows[i] = new Vector(columnCount);

            for (int j = 0; j < matrixComponentArray[i].length; j++) {
                matrixRows[i].setElement(j, matrixComponentArray[i][j]);
            }
        }
    }

    public Matrix(Matrix matrix) {
        matrixRows = new Vector[matrix.getRowCount()];

        for (int i = 0; i < matrix.getRowCount(); i++) {
            matrixRows[i] = new Vector(matrix.getColumnCount());

            for (int j = 0; j < matrix.getColumnCount(); j++) {
                matrixRows[i].setElement(j, matrix.getRow(i).getElement(j));
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Размер переданного массива векторов должен быть > 0");
        }

        int columnCount = 0;

        for (Vector vector : vectors) {
            if (vector.getSize() > columnCount) {
                columnCount = vector.getSize();
            }
        }

        this.matrixRows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.matrixRows[i] = new Vector(columnCount);

            for (int j = 0; j < vectors[i].getSize(); j++) {
                this.matrixRows[i].setElement(j, vectors[i].getElement(j));
            }
        }
    }

    // получение количества строк
    public int getRowCount() {
        return matrixRows.length;
    }

    // получение количества столбцов
    public int getColumnCount() {
        return matrixRows[0].getSize();
    }

    // получение вектора-строки по индексу
    public Vector getRow(int index) {
        checkRowIndex(index);

        return matrixRows[index];
    }

    // задание вектора-строки по индексу
    public void setRow(int index, Vector vector) {
        checkRowIndex(index);

        if (vector.getSize() != getColumnCount()) {
            throw new IllegalArgumentException("Размер введенного вектора " + vector.getSize() +
                    " должен быть равен количеству столбцов в матрице " + getColumnCount());
        }

        matrixRows[index] = new Vector(vector);
    }

    // получение вектора-столбца по индексу
    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnCount()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности массива векторов. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (getColumnCount() - 1) + "].");
        }

        double[] array = new double[getRowCount()];

        for (int i = 0; i < getRowCount(); i++) {
            array[i] = matrixRows[i].getElement(index);
        }

        return new Vector(array);
    }

    // транспонирование матрицы
    public Matrix transpose() {
        Vector[] transposedMatrixRows = new Vector[getColumnCount()];

        for (int i = 0; i < getColumnCount(); i++) {
            transposedMatrixRows[i] = new Vector(getRowCount());
        }

        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                transposedMatrixRows[j].setElement(i, matrixRows[i].getElement(j));
            }
        }

        matrixRows = transposedMatrixRows;
        return this;
    }

    // умножение на скаляр
    public void multiplyByScalar(double number) {
        for (Vector vector : matrixRows) {
            vector.multiplyByScalar(number);
        }
    }

    // умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (getColumnCount() != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора " + vector.getSize() +
                    " и количество столбцов матрицы " + getColumnCount() + " должны быть равны.");
        }

        Vector resultVector = new Vector(getRowCount());

        for (int i = 0; i < getRowCount(); i++) {
            double sum = 0;

            for (int j = 0; j < matrixRows[i].getSize(); j++) {
                sum += matrixRows[i].getElement(j) * vector.getElement(j);
            }

            resultVector.setElement(i, sum);
        }

        return resultVector;
    }

    // сложение матриц
    public void add(Matrix matrix) {
        if (matrix.getRowCount() != getRowCount() && matrix.getColumnCount() != getColumnCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны");
        }

        for (int i = 0; i < matrix.getRowCount(); i++) {
            matrixRows[i].add(matrix.matrixRows[i]);
        }
    }

    // метод увеличения текущей матрицы в зависимости от размеров входящей матрицы
    public void increaseMatrix(Matrix matrix) {
        int inputMatrixRow = matrix.getRowCount();
        int inputMatrixColumn = matrix.getColumnCount();
        int currentMatrixRow = getRowCount();
        int currentMatrixColumn = getColumnCount();

        Matrix increasedMatrix;

        if (inputMatrixRow > currentMatrixRow) {
            if (inputMatrixColumn > currentMatrixColumn) {
                increasedMatrix = getIncreasedMatrix(inputMatrixRow, inputMatrixColumn);
            } else {
                increasedMatrix = getIncreasedMatrix(inputMatrixRow, currentMatrixColumn);
            }
        } else {
            if (inputMatrixColumn > currentMatrixColumn) {
                increasedMatrix = getIncreasedMatrix(currentMatrixRow, inputMatrixColumn);
            } else {
                return;
            }
        }

        this.matrixRows = increasedMatrix.matrixRows;
    }

    // вспомогательный метод для получения увеличенной матрицы до размеров, переданных в аргументе
    private Matrix getIncreasedMatrix(int row, int column) {
        Matrix increasedMatrix = new Matrix(row, column);

        for (int i = 0; i < getRowCount(); i++) {
            double[] numbers = new double[column];

            for (int j = 0; j < getColumnCount(); j++) {
                numbers[j] = matrixRows[i].getElement(j);
            }

            increasedMatrix.matrixRows[i] = new Vector(column, numbers);
        }

        return increasedMatrix;
    }

    // вычитание матриц
    public void subtract(Matrix matrix) {
        if (matrix.getRowCount() != getRowCount() && matrix.getColumnCount() != getColumnCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны");
        }

        for (int i = 0; i < matrix.getRowCount(); i++) {
            matrixRows[i].subtract(matrix.matrixRows[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Vector vector : matrixRows) {
            stringBuilder.append(vector);
            stringBuilder.append(" ");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= getRowCount()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размерности массива векторов. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (getRowCount() - 1) + "].");
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() && matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны");
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() && matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Размеры матриц должны быть равны");
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);
        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Число столбцов первой матрицы " + matrix1.getColumnCount() +
                    " должно быть равно числу строк второй матрицы" + matrix2.getRowCount());
        }

        //Matrix resultMatrix = new Matrix(matrix2.getColumnCount(), matrix1.getRowCount());
        double[][] matrixComponentArray = new double[matrix2.getColumnCount()][matrix1.getRowCount()];

//        for (int i = 0; i < matrix1.getRowCount(); i++) {
//            for (int j = 0; j < matrix2.getColumnCount(); j++) {
//                matrixComponentArray[i][j] = multiplyMatricesCell(firstMatrix, secondMatrix, i, col);
//            }
//        }
        
//        for (int i = 0; i < matrix2.getColumnCount(); i++) {
//            resultMatrix.matrixRows[i] = matrix1.multiplyByVector(matrix2.getColumn(i));
//        }

        return new Matrix(matrixComponentArray);
    }

    // вычисление определителя матрицы
    private static double getMatrixDeterminant(Matrix matrix) {
        if (matrix.getRowCount() == 1) {
            return matrix.matrixRows[0].getElement(0);
        }

        if (matrix.getRowCount() == 2) {
            return matrix.matrixRows[0].getElement(0) * matrix.matrixRows[1].getElement(1) - matrix.matrixRows[1].getElement(0) * matrix.matrixRows[0].getElement(1);
        } else {
            double determinant = 0;

            for (int i = 0; i < matrix.getRowCount(); i++) {
                determinant += Math.pow(-1, i) * matrix.matrixRows[0].getElement(i) * getMatrixDeterminant(getMinor(i, matrix));
            }

            return determinant;
        }
    }

    public double getMatrixDeterminant() {
        if (getRowCount() != getColumnCount() || getRowCount() == 0) {
            throw new IllegalArgumentException("Определитель матрицы может быть получен только у квадратной матрицы.");
        }

        return getMatrixDeterminant(this);
    }

    private static Matrix getMinor(int deletedColumnIndex, Matrix matrix) {
        int matrixRowCount = matrix.getRowCount();
        Matrix resultMatrix = new Matrix(matrixRowCount - 1, matrixRowCount - 1);

        for (int i = 1; i < matrixRowCount; i++) {
            double[] numbers = new double[matrixRowCount - 1];
            int k = 0;

            for (int j = 0; j < matrixRowCount; j++) {
                if (j == deletedColumnIndex) {
                    continue;
                }

                numbers[k] = matrix.matrixRows[i].getElement(j);
                k++;
            }

            resultMatrix.matrixRows[i - 1] = new Vector(matrixRowCount - 1, numbers);
        }

        return resultMatrix;
    }
}