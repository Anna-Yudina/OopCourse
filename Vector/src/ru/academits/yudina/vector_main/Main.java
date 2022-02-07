package ru.academits.yudina.vector_main;

import ru.academits.yudina.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[] array1 = new double[]{2, 4, 7, 15, 8, 1, 6};
        double[] array2 = new double[]{1, 2, 3, 4};
        double[] array3 = new double[]{3, 4, 5};

        Vector vector1 = new Vector(6, array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(5, array3);
        Vector vector4 = new Vector(3);
        Vector vector5 = new Vector(vector3);

        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(vector5);
        System.out.println("_______________");

        System.out.print("Результат сложения двух векторов : " + vector1 + " + " + vector2);
        System.out.println(" = " + Arrays.toString(vector1.getSum(vector2)));
        System.out.print("Результат сложения двух векторов: " + vector2 + " + " + vector1);
        System.out.println(" = " + Arrays.toString(vector2.getSum(vector1)));
        System.out.println("_______________");

        System.out.print("Результат вычитания двух векторов: " + vector1 + " - " + vector2);
        System.out.println(" = " + Arrays.toString(vector1.getDifference(vector2)));
        System.out.print("Результат вычитания двух векторов: " + vector2 + " - " + vector1);
        System.out.println(" = " + Arrays.toString(vector2.getDifference(vector1)));
        System.out.println("_______________");

        int number = 3;
        System.out.print("Результат умножения вектора на скаляр: "  + vector3 + " * " + number);
        System.out.println(" = " + Arrays.toString(vector3.getMultiplicationByScalar(number)));
        System.out.println("_______________");

        System.out.print("Результат разворота вектора " + vector3 + ": ");
        System.out.println(" = " + Arrays.toString(vector3.getReverseVector()));
        System.out.println("_______________");

        System.out.print("Результат получаения длины вектора " + vector3 + ": ");
        System.out.printf("l = %.2f %n", vector1.getVectorLength());
        System.out.println("_______________");

        int index = 2;
        System.out.print("Результат получения компоненты с индексом " + index + " вектора " + vector3 + ": ");
        System.out.println(vector3.getComponentVectorByIndex(index));
        System.out.println("_______________");

        double value = 5.6;
        System.out.println("Устанавливаем компоненту вектора " + vector2 + " с индексом " + index + " и значением " + value);
        vector2.setComponentVectorByIndex(index, value);
        System.out.println("Вектор после установки компоненты: " + vector2);
        System.out.println("_______________");

        double[] array4 = new double[]{6, 8, 5, 3};
        double[] array5 = new double[]{3, 5, 1, 6, 8, 3};
        Vector vector6 = new Vector(array4);
        Vector vector7 = new Vector(7, array5);
        System.out.println("Результат сложения двух векторов с помощью статического метода: ");
        System.out.println(vector6 + " + " + vector7 + " = " + Arrays.toString(Vector.getSumTwoVectors(vector6, vector7)));
        System.out.println("Результат сложения двух векторов с помощью статического метода: ");
        System.out.println(vector7 + " + " + vector6 + " = " + Arrays.toString(Vector.getSumTwoVectors(vector7, vector6)));
        System.out.println("_______________");

        System.out.println("Результат вычитания двух векторов с помощью статического метода: ");
        System.out.println(vector6 + " - " + vector7 + " = " + Arrays.toString(Vector.getDifference(vector6, vector7)));
        System.out.println("Результат вычитания двух векторов с помощью статического метода: ");
        System.out.print(vector7 + " - " + vector6);
        System.out.println(" = " + Arrays.toString(Vector.getDifference(vector7, vector6)));
        System.out.println("_______________");

        System.out.println("Результат скалярного произведения двух векторов с помощью статического метода: ");
        System.out.println(vector6 + " * " + vector7 + " = " + Arrays.toString(Vector.getScalarMultiplication(vector6, vector7)));
        System.out.println("Результат скалярного произведения двух векторов с помощью статического метода: ");
        System.out.println(vector7 + " * " + vector6 + " = " + Arrays.toString(Vector.getScalarMultiplication(vector7, vector6)));
    }
}