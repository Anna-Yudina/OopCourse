package ru.academits.yudina.array_list_main;

import ru.academits.yudina.array_list.MyArrayList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        System.out.println("Печатаем список: " + list);

        System.out.println("___________________");
        int index1 = 9;
        System.out.println("Проверка метода remove(index):");
        System.out.println("Удаляемое значение по индексу 9: " + list.remove(index1));
        System.out.println("Список после удаления: " + list);

        System.out.println("___________________");
        System.out.println("Длина списка: " + list.size());

        System.out.println("___________________");
        int index2 = 2;
        int number1 = 7;
        list.add(index2, number1);
        System.out.println("Добавляем новый элемент со значением " + number1 + " по индексу: " + index2 + ", результат: " + list);

        System.out.println("___________________");
        System.out.println("Проверка метода 'boolean contains(Object object)':");
        int number2 = 5;
        String massage1 = list.contains(number2) ? "содержит" : "не содержит";
        System.out.println("Список " + list + " " + massage1 + " элемент со значением " + number2);

        System.out.println("___________________");
        System.out.println("Проверка метода 'Object[] toArray()':");
        Object[] numbersArray = list.toArray();

        for (Object object : numbersArray) {
            System.out.print(object + " ");
        }

        System.out.println();
        System.out.println("___________________");
        System.out.println("Проверка метода '<E> E[] toArray(E[] array)':");

        Integer[] numbersArray2 = new Integer[list.size()];
        numbersArray2 = list.toArray(list.toArray(numbersArray2));

        for (Object obj : numbersArray2) {
            System.out.print(obj + " ");
        }

        System.out.println();
        System.out.println("___________________");
        System.out.println("Проверка метода 'boolean remove(Object object)':");
        int number3 = 6;
        System.out.println("Удаляем из списка " + list + " элемент со значением " + number3);
        list.remove((Object) number3);
        System.out.println("Получаем список после удаления: " + list);

        System.out.println("___________________");
        System.out.println("Проверка метода 'E set(int index, E element)':");
        int index3 = 8;
        int number4 = 16;
        System.out.println("Вставляем элемент по индексу " + index3 + " со значением " + number4 + " в список " + list);
        int lastValue = list.set(index3, number4);
        System.out.println("Старое значение элемента: " + lastValue);
        System.out.println("Список после применения метода: " + list);

        System.out.println("___________________");
        System.out.println("Проверка метода 'boolean containsAll(Collection<?> collection)':");
        MyArrayList<String> namesList1 = new MyArrayList<>(3);
        namesList1.add("Марина");
        namesList1.add("Лиза");
        namesList1.add("Света");
        namesList1.add("Сергей");

        MyArrayList<String> namesList2 = new MyArrayList<>(3);
        namesList2.add("Лиза");
        namesList2.add("Марина");
        namesList2.add("Света");
        namesList2.add("Степан");

        System.out.println("Проверим содержит ли первый список " + namesList1 + " все элементы второго списка " + namesList2);
        String massage2 = namesList1.containsAll(namesList2) ? "Содержит!" : "Не содержит!";
        System.out.println(massage2);

        System.out.println("______________");
        System.out.println("Проверка метода 'boolean addAll(Collection<? extends E> collection)':");
        System.out.println("Добавим в список " + namesList1 + " элементы списка " + namesList2);
        namesList1.addAll(namesList2);
        System.out.println("Получили результат: " + namesList1);

        System.out.println("______________");
        System.out.println("Проверка метода 'boolean addAll(int index, Collection<? extends E> collection)':");

        MyArrayList<Integer> numberList1 = new MyArrayList<>();
        numberList1.add(1);
        numberList1.add(2);
        numberList1.add(3);
        numberList1.add(4);
        numberList1.add(5);
        numberList1.add(6);

        MyArrayList<Integer> numberList2 = new MyArrayList<>();
        numberList2.add(7);
        numberList2.add(8);
        numberList2.add(9);

        System.out.println("Даны 2 списка: " + numberList1 + "; " + numberList2);

        int index = 2;
        System.out.println("Добавили второй список в первый список, начиная с index = " + index);
        numberList1.addAll(index, numberList2);
        System.out.println(numberList1);

        System.out.println("______________");
        System.out.println("Проверка метода 'boolean removeAll()':");

        MyArrayList<Integer> numberList3 = new MyArrayList<>();
        numberList3.add(1);
        numberList3.add(2);
        numberList3.add(3);
        numberList3.add(4);
        numberList3.add(5);
        numberList3.add(6);
        numberList3.add(7);
        numberList3.add(8);
        numberList3.add(9);
        numberList3.add(10);

        MyArrayList<Integer> numberList4 = new MyArrayList<>();
        numberList4.add(1);
        numberList4.add(3);
        numberList4.add(4);
        numberList4.add(8);

        System.out.println("Даны 2 списка: " + numberList3 + "; " + numberList4);
        System.out.println("Удалили второй список из первого списка:");
        numberList3.removeAll(numberList4);
        System.out.println(numberList3);

        System.out.println("______________");
        System.out.println("Проверка метода 'boolean retainAll(Collection<?> collection)':");
        numberList3.add(0, 1);
        numberList3.add(2, 3);
        numberList3.add(3, 4);
        System.out.println("Даны 2 списка: " + numberList3 + "; " + numberList4);
        numberList3.retainAll(numberList4);
        System.out.println("Результат работы метода: " + numberList3);

        System.out.println("______________");
        System.out.println("Проверка итератора:");

        for (int number : numberList4) {
            System.out.print(number + ", ");
        }

        // оставила варнинг, чтобы явно проверить работу итератора
        for (Iterator<Integer> iterator = numberList1.iterator(); iterator.hasNext(); ) {
            Integer number = iterator.next();
            System.out.print(number + ", ");
        }

        System.out.println(System.lineSeparator() + "______________");
        System.out.println("Проверка метода 'int indexOf(Object object) ':");
        System.out.println("Дан список: " + numberList3);
        int number5 = 3;
        int index4 = numberList3.indexOf(number5);
        System.out.println("Полученный индекс после выполнения метода: " + index4);
    }
}