package ru.academits.yudina.hashtable_main;

import ru.academits.yudina.hashtable.MyHashTable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> numbers1 = new MyHashTable<>(15);
        numbers1.add(4);
        numbers1.add(5);
        numbers1.add(6);
        numbers1.add(7);
        numbers1.add(8);

        System.out.println("создан первый список " + Arrays.toString(numbers1.toArray()) + " размерности " + numbers1.size());

        ArrayList<Integer> arrayList = new ArrayList<>(15);
        arrayList.add(14);
        arrayList.add(15);
        arrayList.add(16);
        arrayList.add(17);
        arrayList.add(18);

        System.out.println("создан второй список " + Arrays.toString(arrayList.toArray()) + " размерности " + arrayList.size());

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean remove(Object object)'");
        int number1 = 14;
        System.out.println("Удаляем число " + number1 + " из первого списка " + Arrays.toString(numbers1.toArray()));
        numbers1.remove(number1);
        System.out.println("Получившийся список после удаления: " + Arrays.toString(numbers1.toArray()));

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean contains(Object object)'");
        int number2 = 6;
        System.out.println("Проверим содержит ли список " + Arrays.toString(numbers1.toArray()) + " элемент " + number2);
        String massage1 = numbers1.contains(number2) ? "да, содержит" : "нет, не содержит";
        System.out.println(massage1);

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean containsAll(Collection<?> collection)'");
        numbers1.add(14);
        numbers1.add(15);
        numbers1.add(16);
        numbers1.add(17);
        numbers1.add(18);
        System.out.println("Даны 2 списка: " + Arrays.toString(numbers1.toArray()) + " и " + Arrays.toString(arrayList.toArray()));
        System.out.println("Проверяем, содержит ли первый список все элементы второго списка: ");
        String massage2 = numbers1.containsAll(arrayList) ? "да, содержит" : "нет, не содержит";
        System.out.println(massage2);

        System.out.println("__________________________");
        System.out.println("Проверка Итератора");
        MyHashTable<Integer> myHashTable1 = new MyHashTable<>();
        myHashTable1.add(1);
        myHashTable1.add(5);
        myHashTable1.add(5);
        myHashTable1.add(9);
        System.out.println("Создали новый список : " + Arrays.toString(myHashTable1.toArray()));
        System.out.println("После обхода итератором: ");

        for (Integer number : myHashTable1) {
            System.out.print(number + " ");
        }

        System.out.println(System.lineSeparator() + "__________________________");
        System.out.println("Проверка метода '<T> T[] toArray(T[] array) '");
        Integer[] numbersArray = new Integer[10];
        Integer[] resultNumbersArray = myHashTable1.toArray(numbersArray);
        System.out.println(Arrays.toString(resultNumbersArray));

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean retainAll(Collection<?> collection)'");
        System.out.println("Даны 2 списка: " + Arrays.toString(numbers1.toArray()) + " и " + Arrays.toString(arrayList.toArray()));
        numbers1.retainAll(arrayList);
        System.out.println("Получившийся список после запуска метода: " + Arrays.toString(numbers1.toArray()));

        System.out.println("_________________________");
        System.out.println("Проверка метода 'boolean addAll(Collection<? extends E> collection)'");
        MyHashTable<Integer> numbers2 = new MyHashTable<>();
        numbers2.add(15);
        numbers2.add(18);
        numbers2.add(2);
        numbers2.add(9);
        numbers2.add(11);
        System.out.println("Добавим в список " + Arrays.toString(numbers1.toArray()) + " все элементы вотрого списка " + Arrays.toString(numbers2.toArray()));
        numbers1.addAll(numbers2);
        System.out.println("Получившийся результат: " + Arrays.toString(numbers1.toArray()));

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean removeAll(Collection<?> collection)'");
        System.out.println("Удаляем из первого списка " + Arrays.toString(numbers1.toArray()) +
                " элементы второго списка " + Arrays.toString(numbers2.toArray()));
        numbers1.removeAll(numbers2);
        System.out.println("Получаем результат: " + Arrays.toString(numbers1.toArray()));
    }
}