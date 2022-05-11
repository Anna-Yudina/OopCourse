package ru.academits.yudina.tree_main;

import ru.academits.yudina.tree.Tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> numbers1 = new Tree<>();
        numbers1.add(20);
        numbers1.add(8);
        numbers1.add(32);
        numbers1.add(4);
        numbers1.add(15);
        numbers1.add(25);
        numbers1.add(37);
        numbers1.add(2);
        numbers1.add(12);
        numbers1.add(27);
        numbers1.add(null);

        System.out.println("Распечатаем дерево с помощью обхода в глубину с помощью рекурсии:");
        Consumer<Integer> consumer = x -> System.out.print(x + " ");
        numbers1.depthTraversalWithRecursion(consumer);

        System.out.println(System.lineSeparator() + "____________________");
        System.out.println("Распечатаем дерево с помощью обхода в глубину без рекурсии:");
        numbers1.depthTraversal(consumer);

        System.out.println(System.lineSeparator() + "____________________");
        System.out.println("Распечатаем дерево с помощью обхода в ширину:");
        numbers1.breadthTraversal(consumer);

        System.out.println(System.lineSeparator() + "____________________");
        System.out.println("Эксперименты с компаратором:");

        Comparator<Integer> comparator = (number1, number2) -> {
            if (number1 == null) {
                if (number2 == null) {
                    return 0;
                }

                return -1;
            }

            if (number2 == null) {
                return 1;
            }

            return number1.compareTo(number2);
        };

        Tree<Integer> numbers2 = new Tree<>(comparator);
        numbers2.add(20);
        numbers2.add(8);
        numbers2.add(32);
        numbers2.add(4);
        numbers2.add(15);
        numbers2.add(25);
        numbers2.add(37);
        numbers2.add(2);
        numbers2.add(12);
        numbers2.add(27);
        numbers2.add(null);
        numbers2.depthTraversalWithRecursion(consumer);

        System.out.println(System.lineSeparator() + "________________");
        System.out.println("Создадим пустое дерево:");
        Tree<Integer> numbers3 = new Tree<>();
        numbers3.depthTraversalWithRecursion(consumer);
        System.out.println(numbers3);
        System.out.println(numbers2);

        System.out.println("_____________________");
        System.out.println("Удалим корень дерева: " + 20);
        System.out.println(numbers1.remove(20));
        System.out.println("Дерево после удаления: " + numbers1);
        System.out.println("число элементов после удаления: " + numbers1.getCount());

        System.out.println("_____________________");
        System.out.println("Удалим лист дерева: " + 4);
        numbers1.remove(4);
        System.out.println("Дерево после удаления: " + numbers1);
        System.out.println("число элементов после удаления: " + numbers1.getCount());

        System.out.println("_____________________");
        System.out.println("Удалим узел дерева, у которого есть 2 ребенка: " + 8);
        numbers1.remove(8);
        System.out.println("Дерево после удаления: " + numbers1);
        System.out.println("число элементов после удаления: " + numbers1.getCount());

        System.out.println("_____________________");
        System.out.println("Удалим узел со значением null");
        numbers1.remove(null);
        System.out.println("Дерево после удаления: " + numbers1);
        System.out.println("число элементов после удаления: " + numbers1.getCount());
    }
}