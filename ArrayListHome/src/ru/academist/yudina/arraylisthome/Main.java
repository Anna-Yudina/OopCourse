package ru.academist.yudina.arraylisthome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 1. Прочитать в список все строки из файла
        File inputFile = new File("C:/Users/anuta/IdeaProjects/OopCourse/ArrayListHome/src/ru/academist/yudina/inputFile.txt");
        ArrayList<String> fileLinesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();

            while (line != null) {
                fileLinesList.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }

        System.out.println("Вывод содержимого файла: ");
        System.out.println(fileLinesList);
        System.out.println("---------------");

        //2. Есть список из целых чисел. Удалить из него все четные числа. В
        //этой задаче новый список создавать нельзя
        System.out.println("Необходимо из списка удалить все четные числа.");
        ArrayList<Integer> task2NumbersList = new ArrayList<>(Arrays.asList(1, 6, 8, 9, 5, 4, 10, 11));
        System.out.println("Список до изменений: " + task2NumbersList);

        for (int i = 0; i < task2NumbersList.size(); i++) {
            if (task2NumbersList.get(i) % 2 == 0) {
                task2NumbersList.remove(i);
                i--;
            }
        }

        System.out.println("Список после изменений: " + task2NumbersList);
        System.out.println("---------------");

        //3. Есть список из целых чисел, в нём некоторые числа могут повторяться.
        // Надо создать новый список, в котором будут элементы первого списка в таком же порядке, но без повторений
        //Например, был список [1, 5, 2, 1, 3, 5], должен получиться новый список [1, 5, 2, 3]
        System.out.println("Необходимо из списка удалить все повторения.");
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        System.out.println("Дан список чисел: " + numbersList);

        ArrayList<Integer> noRepetitionsNumbersList = new ArrayList<>(numbersList.size());
        for (Integer number : numbersList) {
            if (!noRepetitionsNumbersList.contains(number)) {
                noRepetitionsNumbersList.add(number);
            }
        }

        System.out.println("Получен новый список чисел, в котором убраны повторения из изначального списка чисел: " + noRepetitionsNumbersList);
    }
}