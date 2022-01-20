package ru.academIts.yudina.range_main;

import ru.academIts.yudina.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное вещественное число на прямой:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное вещественное число на прямой:");
        double to = scanner.nextDouble();

        Range range = new Range(from, to);
        double rangeLength = range.getRangeLength();
        System.out.println("Длина диапазона равна " + rangeLength );

        System.out.println("Введите любое вещественное число:");
        double number = scanner.nextDouble();

        boolean isInsideNumber = range.isInside(number);
        String answer = isInsideNumber ? "Да" : "Нет";
        System.out.println("Число попадает в диапазон? " + answer);
    }
}
