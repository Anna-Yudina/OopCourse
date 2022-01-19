package ru.academIts.yudina.range_main;

import ru.academIts.yudina.range.Range;

import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное вещественное число на прямой:");
        double from = scanner.nextDouble();
        System.out.println("Введите конечное вещественное число на прямой:");
        double to = scanner.nextDouble();

        Range rangeObject = new Range(from, to);
        double range = rangeObject.getRange();
        System.out.println("Длина диапазона равна " + range);

        System.out.println("Введите любое вещественное число:");
        double number = scanner.nextDouble();
        
        boolean isInsideNumber = rangeObject.isInside(number);
        String answer = (isInsideNumber)?"Да":"Нет";
        System.out.println("Число попадает в диапазон? " + answer);
    }
}
