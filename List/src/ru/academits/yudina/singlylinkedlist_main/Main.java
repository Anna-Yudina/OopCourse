package ru.academits.yudina.singlylinkedlist_main;

import ru.academits.yudina.singlylinkedlist.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> myFirstList = new SinglyLinkedList<>();
        myFirstList.addFirst(3);
        myFirstList.addFirst(6);
        myFirstList.addFirst(9);
        myFirstList.addFirst(17);
        myFirstList.addFirst(8);
        myFirstList.addFirst(4);

        System.out.println(myFirstList);

        System.out.println("Размер списка равен: " + myFirstList.getCount());

        try {
            System.out.println("Первый элемент списка: " + myFirstList.getFirst());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("Удаляем первый элемент со значением: " + myFirstList.deleteFirst());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(myFirstList);

        int index1 = 1;
        try {
            int value = myFirstList.get(index1);
            System.out.println("Значение элемента по индексу " + index1 + " составляет: " + value);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int index2 = 2;
        int value2 = 10;
        try {
            int oldValue = myFirstList.set(index2, value2);
            System.out.println("Предыдущее значение по индексу " + index2 + " составляет: " + oldValue);
            System.out.println(myFirstList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int index3 = 0;
        try {
            int deleteValue = myFirstList.deleteByIndex(index3);
            System.out.println("Удаляемое значение по индексу " + index3 + " составляет: " + deleteValue);
            System.out.println(myFirstList);
            System.out.println("Размер списка после удаления: " + myFirstList.getCount());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int index4 = 2;
        int value4 = 5;
        try {
            System.out.println("Выполняем вставку элемента по индексу " + index4 + " со значением " + value4);
            myFirstList.add(index4, value4);
            System.out.println("Итог: " + myFirstList);
            System.out.println("Размер списка после вставки: " + myFirstList.getCount());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("______________________");
        myFirstList.add(2, null);
        System.out.println(myFirstList);
        Integer value5 = null;
        String massage = myFirstList.deleteByData(value5) ? "успех" : "ошибка";
        System.out.println("Удаление элемента по значению " + value5 + ":  " + massage);
        System.out.println("Итог: " + myFirstList);
        System.out.println("Размер списка после удаления: " + myFirstList.getCount());

        myFirstList.reverse();
        System.out.println("Реверс итог: " + myFirstList);

        System.out.println("Вставка по индексу 0");
        myFirstList.add(0, 1);
        System.out.println(myFirstList);

        SinglyLinkedList<Integer> newList = myFirstList.getCopy();
        System.out.println("Копирование списка: " + newList);

        myFirstList.add(2, 4);
        System.out.println("Старый список " + myFirstList);
        System.out.println("Новый список: " + newList);

        newList.add(4, 11);
        System.out.println("Старый список " + myFirstList);
        System.out.println("Новый список: " + newList);

        newList.add(1, 14);
        System.out.println("Старый список " + myFirstList);
        System.out.println("Новый список: " + newList);
        System.out.println("Размер старого списка: " + myFirstList.getCount());
        System.out.println("Размер нового списка: " + newList.getCount());

        System.out.println("______________________");
        System.out.println("Проверка метода: 'void add(int index, T data)'");
        System.out.println("Дан список: " + myFirstList);
        myFirstList.add(0, 15);
        System.out.println("После добавления: " + myFirstList);

        try {
            myFirstList.add(-1, 16);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        myFirstList.add(6, 17);
        System.out.println("После добавления: " + myFirstList);
        System.out.println("Размер списка " + myFirstList.getCount());

        try {
            myFirstList.add(9, 18);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("После добавления: " + myFirstList);
        System.out.println("Размер списка " + myFirstList.getCount());
    }
}