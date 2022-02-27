package ru.academits.yudina.list_main;

import ru.academits.yudina.singlylinkedlist.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> myFirstList = new SinglyLinkedList<>();
        myFirstList.addFirstElement(3);
        myFirstList.addFirstElement(6);
        myFirstList.addFirstElement(9);
        myFirstList.addFirstElement(17);
        myFirstList.addFirstElement(8);
        myFirstList.addFirstElement(4);

        System.out.println(myFirstList);

        System.out.println("Размер списка равен: " + myFirstList.getCount());

        System.out.println("Первый элемент списка: " + myFirstList.getHead());

        System.out.println("Второй элемент списка: " + myFirstList.getHead().getNext());

        System.out.println("Удаляем первый элемент со значением: " + myFirstList.deleteFirstElement());
        System.out.println(myFirstList);

        int index1 = 4;
        try {
            int value = myFirstList.getElementValue(index1);
            System.out.println("Значение элемента по индексу " + index1 + " составляет: " + value);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int index2 = 2;
        int value2 = 10;
        try {
            int oldValue = myFirstList.changeElementValue(index2, value2);
            System.out.println("Предыдущее значение по индексу " + index2 + " составляет: " + oldValue);
            System.out.println(myFirstList);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int index3 = 3;
        try {
            int deleteValue = myFirstList.deleteElement(index3);
            System.out.println("Удаляемое значение по индексу " + index3 + " составляет: " + deleteValue);
            System.out.println(myFirstList);
            System.out.println("Размер списка после удаления: " + myFirstList.getCount());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int index4 = 0;
        int value4 = 5;
        try {
            System.out.println("Выполняем вставку элемента по индексу " + index4 + " со значением " + value4);
            myFirstList.addElement(index4, value4);
            System.out.println("Итог: " + myFirstList);
            System.out.println("Размер списка после вставки: " + myFirstList.getCount());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int value5 = 5;
        String massage = myFirstList.deleteElementValue(value5) ? "успех" : "ошибка";
        System.out.println("Удаление элемента по значению " + value5 + ":  " + massage);
        System.out.println("Итог: " + myFirstList);
        System.out.println("Размер списка после удаления: " + myFirstList.getCount());

        myFirstList.reverse();
        System.out.println("Реверс итог: " + myFirstList);

        SinglyLinkedList<Integer> newList = myFirstList.getCopy();
        System.out.println("Копирование списка: " + newList);
    }
}