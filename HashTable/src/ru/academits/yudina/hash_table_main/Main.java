package ru.academits.yudina.hash_table_main;

import ru.academits.yudina.hash_table.MyHashTable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyHashTable<Integer> numbers1 = new MyHashTable<>(15);
        numbers1.add(4);
        numbers1.add(5);
        numbers1.add(6);
        numbers1.add(7);
        numbers1.add(8);

        System.out.println("создана первая таблица " + Arrays.toString(numbers1.toArray()) + " размерности " + numbers1.size());

        ArrayList<Integer> arrayList = new ArrayList<>(15);
        arrayList.add(14);
        arrayList.add(15);
        arrayList.add(16);
        arrayList.add(17);
        arrayList.add(11);

        System.out.println("создана вторая таблица " + Arrays.toString(arrayList.toArray()) + " размерности " + arrayList.size());

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean contains(Object object)'");
        int number2 = 2;
        System.out.println("Проверим содержит ли таблица " + Arrays.toString(numbers1.toArray()) + " элемент " + number2);
        String massage1 = numbers1.contains(number2) ? "да, содержит" : "нет, не содержит";
        System.out.println(massage1);

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean containsAll(Collection<?> collection)'");
        numbers1.add(14);
        numbers1.add(15);
        numbers1.add(16);
        numbers1.add(17);
        numbers1.add(18);
        System.out.println("Даны 2 таблицы: " + Arrays.toString(numbers1.toArray()) + " и " + Arrays.toString(arrayList.toArray()));
        System.out.println("Проверяем, содержит ли первая таблица все элементы второй таблицы: ");
        String massage2 = numbers1.containsAll(arrayList) ? "да, содержит" : "нет, не содержит";
        System.out.println(massage2);

        System.out.println(System.lineSeparator() + "_________________________");
        System.out.println("Проверка метода 'boolean addAll(Collection<? extends E> collection)'");
        MyHashTable<Integer> numbers2 = new MyHashTable<>();
        numbers2.add(15);
        numbers2.add(18);
        numbers2.add(2);
        numbers2.add(9);
        numbers2.add(11);
        System.out.println("Добавим в таблицу " + Arrays.toString(numbers1.toArray()) + " все элементы вотрой таблицы " + Arrays.toString(numbers2.toArray()));
        numbers1.addAll(numbers2);
        System.out.println("Получившийся результат: " + Arrays.toString(numbers1.toArray()));

        System.out.println("Проверка метода 'void clear()'");
        System.out.println("Удаляем таблицу: " + Arrays.toString(numbers2.toArray()));
        numbers2.clear();
        System.out.println("Получаем результат: " + Arrays.toString(numbers2.toArray()));

        System.out.println(System.lineSeparator() + "__________________________");
        System.out.println("Проверка метода '<T> T[] toArray(T[] array) '");
        Integer[] numbersArray = new Integer[9];
        Integer[] resultNumbersArray = numbers1.toArray(numbersArray);
        System.out.println(Arrays.toString(resultNumbersArray));

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean remove(Object object)'");
        int number1 = 14;
        System.out.println("Удаляем число " + number1 + " из первой таблицы " + Arrays.toString(numbers1.toArray()));
        numbers1.remove(number1);
        System.out.println("Получившаяся таблица после удаления: " + Arrays.toString(numbers1.toArray()));

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean removeAll(Collection<?> collection)'");
        MyHashTable<Integer> numbers3 = new MyHashTable<>(15);
        numbers3.add(3);
        numbers3.add(4);
        numbers3.add(5);
        numbers3.add(6);
        numbers3.add(7);
        numbers3.add(8);
        numbers3.add(9);

        MyHashTable<Integer> numbers4 = new MyHashTable<>(15);
        numbers4.add(3);
        numbers4.add(5);
        numbers4.add(7);
        numbers4.add(9);

        System.out.println("Удаляем из первой таблицы " + Arrays.toString(numbers3.toArray()) +
                " элементы второй таблицы " + Arrays.toString(numbers4.toArray()));
        numbers3.removeAll(numbers4);
        System.out.println("Получаем результат: " + Arrays.toString(numbers3.toArray()));
        System.out.println("Размер таблицы: " + numbers3.size());

        System.out.println("__________________________");
        System.out.println("Проверка метода 'boolean retainAll(Collection<?> collection)'");
        System.out.println("Даны 2 таблицы: " + Arrays.toString(numbers1.toArray()) + " и " + Arrays.toString(arrayList.toArray()));
        numbers1.retainAll(arrayList);
        System.out.println("Получившаяся таблица после запуска метода: " + Arrays.toString(numbers1.toArray()));
        MyHashTable<Integer> numbers5 = new MyHashTable<>(10);
        numbers1.retainAll(numbers5);
        System.out.println("Получившаяся таблица после запуска метода: " + Arrays.toString(numbers1.toArray()));

        System.out.println("__________________________");
        System.out.println("Проверка Итератора");
        MyHashTable<Integer> myHashTable1 = new MyHashTable<>();
        myHashTable1.add(1);
        myHashTable1.add(5);
        myHashTable1.add(5);
        myHashTable1.add(9);
        System.out.println("Создали новую таблицу : " + Arrays.toString(myHashTable1.toArray()));
        System.out.println("После обхода итератором: ");

        for (Integer number : myHashTable1) {
            System.out.print(number + " ");
        }

        myHashTable1.add(10);
        myHashTable1.add(7);
        myHashTable1.remove(5);
        myHashTable1.remove(1);
        myHashTable1.add(7);
        myHashTable1.add(7);
        myHashTable1.add(7);
        myHashTable1.add(7);
        myHashTable1.add(7);
        myHashTable1.remove(7);
        myHashTable1.remove(7);

        System.out.println("После второго обхода итератором: ");

        for (Integer number : myHashTable1) {
            System.out.print(number + " ");
        }
    }
}