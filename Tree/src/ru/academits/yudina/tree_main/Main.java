package ru.academits.yudina.tree_main;

import ru.academits.yudina.tree.Tree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> numbers = new Tree<>();
        numbers.add(20);
        numbers.add(8);
        numbers.add(32);
        numbers.add(4);
        numbers.add(15);
        numbers.add(25);
        numbers.add(37);
        numbers.add(2);
        numbers.add(12);
        numbers.add(27);

        System.out.println("Выведем элементы дерева с помощью обхода в ширину: " + numbers);
        System.out.println("число элементов " + numbers.getCount());

        System.out.println("_____________________");
        System.out.print("Распечатаем элементы дерева с помощью обхода в глубину с рекурсией: ");
        numbers.printTree();

        System.out.println("_____________________");
        System.out.println("Преобразуем дерево в массив с помощью обхода в глубину без рекурсии: " + Arrays.toString(numbers.toArray()));

        System.out.println("_____________________");
        System.out.println("Удалим корень дерева: " + 20);
        System.out.println(numbers.remove(20));
        System.out.println("Дерево после удаления: " + numbers);
        System.out.println("число элементов после удаления: " + numbers.getCount());

        System.out.println("Удалим лист дерева: " + 2);
        numbers.remove(2);
        System.out.println("Дерево после удаления: " + numbers);
        System.out.println("число элементов после удаления: " + numbers.getCount());

        System.out.println("Удалим узел дерева, у которого есть 2 ребенка: " + 8);
        numbers.remove(8);
        System.out.println("Дерево после удаления: " + numbers);
        System.out.println("число элементов после удаления: " + numbers.getCount());
    }
}