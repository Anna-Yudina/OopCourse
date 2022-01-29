package ru.academits.yudina.range_main;

import ru.academits.yudina.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное вещественное число на прямой:");
        double from1 = scanner.nextDouble();

        System.out.println("Введите конечное вещественное число на прямой:");
        double to1 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);
        double rangeLength = range1.getLength();
        System.out.println("Длина диапазона равна " + rangeLength);

        System.out.println("Введите любое вещественное число:");
        double number = scanner.nextDouble();

        boolean isInsideNumber = range1.isInside(number);
        String answer = isInsideNumber ? "Да" : "Нет";
        System.out.println("Число попадает в диапазон? " + answer);

        System.out.println("Введите начальное вещественное число на прямой для второго отрезка:");
        double from2 = scanner.nextDouble();

        System.out.println("Введите конечное вещественное число на прямой для второго отрезка:");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(from2, to2);

        Range intersectionRange = range1.getIntersection(range2);
        System.out.println("Результат пересечения двух интервалов: " + intersectionRange);

        Range[] unionRanges = range1.getUnion(range2);
        System.out.println("Результат объединения двух интервалов: " + Arrays.toString(unionRanges));

        Range[] differenceRanges = range1.getDifference(range2);
        System.out.println("Результат разности двух интервалов: " + Arrays.toString(differenceRanges));
    }
}