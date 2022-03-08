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

        Integer value5 = null;
        String massage = myFirstList.deleteByDate(value5) ? "успех" : "ошибка";
        System.out.println("Удаление элемента по значению " + value5 + ":  " + massage);
        System.out.println("Итог: " + myFirstList);
        System.out.println("Размер списка после удаления: " + myFirstList.getCount());

        myFirstList.reverse();
        System.out.println("Реверс итог: " + myFirstList);

        SinglyLinkedList<Integer> newList = myFirstList.getCopy();
        System.out.println("Копирование списка: " + newList);

        System.out.println("Добавляем в новый");
        newList.addFirst(8);
        System.out.println(myFirstList);
        System.out.println(newList);

        System.out.println("Добавляем в старый");
        myFirstList.addFirst(9);
        System.out.println(myFirstList);
        System.out.println(newList);
    }
}