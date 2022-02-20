package ru.academits.yudina.vector_main;

import ru.academits.yudina.vector.Vector;

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
        vector1.add(vector2);
        System.out.println(" = " + vector1);
        System.out.print("Результат сложения двух векторов: " + vector2 + " + " + vector1);
        vector2.add(vector1);
        System.out.println(" = " + vector2);
        System.out.println("_______________");

        System.out.print("Результат вычитания двух векторов: " + vector3 + " - " + vector4);
        vector3.subtract(vector4);
        System.out.println(" = " + vector3);
        System.out.print("Результат вычитания двух векторов: " + vector4 + " - " + vector3);
        vector4.subtract(vector3);
        System.out.println(" = " + vector4);
        System.out.println("_______________");

        double number = 3;
        System.out.print("Результат умножения вектора на скаляр: " + vector3 + " * " + number);
        vector3.multiplyByScalar(number);
        System.out.println(" = " + vector3);
        System.out.println("_______________");

        System.out.print("Результат разворота вектора " + vector3 + ": ");
        vector3.reverse();
        System.out.println(vector3);
        System.out.println("_______________");

        System.out.print("Результат получения длины вектора " + vector3 + ": ");
        System.out.printf("l = %.2f %n", vector1.getLength());
        System.out.println("_______________");

        int index1 = 2;
        System.out.print("Результат получения компоненты с индексом " + index1 + " вектора " + vector3 + ": ");
        System.out.println(vector3.getElement(index1));
        System.out.println("_______________");

        double value = 5.6;
        System.out.println("Устанавливаем компоненту вектора " + vector2 + " с индексом " + index1 + " и значением " + value);
        vector2.setElement(index1, value);
        System.out.println("Вектор после установки компоненты: " + vector2);
        System.out.println("_______________");

        double[] array4 = new double[]{6, 8, 5, 3};
        double[] array5 = new double[]{3, 5, 1, 6, 8, 3};
        Vector vector6 = new Vector(array4);
        Vector vector7 = new Vector(7, array5);
        System.out.println("Результат сложения двух векторов с помощью статического метода: ");
        System.out.print(vector6 + " + " + vector7 + " = ");
        System.out.println(Vector.getSum(vector6, vector7));
        System.out.println("Результат сложения двух векторов с помощью статического метода: ");
        System.out.print(vector7 + " + " + vector6 + " = ");
        System.out.println(Vector.getSum(vector7, vector6));
        System.out.println("_______________");

        System.out.println("Результат вычитания двух векторов с помощью статического метода: ");
        System.out.print(vector6 + " - " + vector7);
        System.out.println(" = " + Vector.getDifference(vector6, vector7));
        System.out.println("Результат вычитания двух векторов с помощью статического метода: ");
        System.out.print(vector7 + " - " + vector6);
        System.out.println(" = " + Vector.getDifference(vector7, vector6));
        System.out.println("_______________");

        System.out.println("Результат скалярного произведения двух векторов с помощью статического метода: ");
        System.out.println(vector6 + " * " + vector7 + " = " + Vector.getScalarProduct(vector6, vector7));
        System.out.println("Результат скалярного произведения двух векторов с помощью статического метода: ");
        System.out.println(vector7 + " * " + vector6 + " = " + Vector.getScalarProduct(vector7, vector6));
    }
}