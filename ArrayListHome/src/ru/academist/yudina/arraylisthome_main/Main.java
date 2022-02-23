package ru.academist.yudina.arraylisthome_main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Прочитать в список все строки из файла
        File inputFile = new File("C:/Users/anuta/IdeaProjects/OopCourse/ArrayListHome/src/ru/academist/yudina/inputFile.txt");
        ArrayList<String> arrayList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = reader.readLine();

        while (line != null) {
            arrayList.add(line);
            line = reader.readLine();
        }

        System.out.println(arrayList);

        //2. Есть список из целых чисел. Удалить из него все четные числа. В
        //этой задаче новый список создавать нельзя
        ArrayList<Integer> numbersList1 = new ArrayList<>(Arrays.asList(1, 6, 8, 9, 5, 4, 10, 11));
        for (Iterator<Integer> i = numbersList1.iterator(); i.hasNext(); ) {
            if (i.next() % 2 == 0) {
                i.remove();
            }
        }

        System.out.println(numbersList1);

        //3. Есть список из целых чисел, в нём некоторые числа могут повторяться.
        // Надо создать новый список, в котором будут элементы первого списка в таком же порядке, но без повторений
        //Например, был список [1, 5, 2, 1, 3, 5], должен получиться новый список [1, 5, 2, 3]
        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> newNumbersList2 = new ArrayList<>();
        for (Integer integer : numbersList2) {
            if (!newNumbersList2.contains(integer)) {
                newNumbersList2.add(integer);
            }
        }

        System.out.println(newNumbersList2);
    }
}
