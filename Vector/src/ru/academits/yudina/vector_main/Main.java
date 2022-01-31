package ru.academits.yudina.vector_main;

import ru.academits.yudina.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(6);
        System.out.println(vector);

        double[] testArray = new double[]{1,2,3,4};
        Vector vector2 = new Vector(testArray);
        System.out.println(vector2);

        Vector vector3 = new Vector(vector2);
        System.out.println(vector3);
    }
}
