package ru.academits.yudina.singlylinkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Ошибка. Список пуст");
        }

        return head.getData();
    }

    @Override
    public String toString() {
        if (head == null) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Node<T> node = head; node != null; node = node.getNext()) {
            stringBuilder.append(node.getData()).append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void addFirst(T data) {
        head = new Node<>(data, head);
        count++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("Ошибка. Список пуст");
        }

        T deletedData = head.getData();
        head = head.getNext();
        count--;
        return deletedData;
    }

    public T get(int index) {
        checkIndex(index);
        return getNode(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index);

        Node<T> node = getNode(index);

        T oldData = node.getData();
        node.setData(data);
        return oldData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        Node<T> previousNode = getNode(index - 1);

        T deletedData = previousNode.getNext().getData();
        previousNode.setNext(previousNode.getNext().getNext());
        count--;
        return deletedData;
    }

    public void add(int index, T data) {
        if (index == 0) {
            addFirst(data);
            return;
        }

        checkIndex(index);

        Node<T> previousNode = getNode(index - 1);
        previousNode.setNext(new Node<>(data, previousNode.getNext()));
        count++;
    }

    public boolean deleteByData(T data) {
        for (Node<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (Objects.equals(data, current.getData())) {
                if (previous == null) {
                    deleteFirst();
                } else {
                    previous.setNext(current.getNext());
                    count--;
                }

                return true;
            }
        }

        return false;
    }

    public void reverse() {
        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            Node<T> next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        head = previous;
    }

    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        if (head == null) {
            return newList;
        }

        Node<T> newCurrent = new Node<>(head.getData());
        newList.head = newCurrent;
        Node<T> newPrevious = newCurrent;

        for (Node<T> current = head.getNext(); current != null; current = current.getNext()) {
            newCurrent = new Node<>(current.getData());
            newPrevious.setNext(newCurrent);
            newPrevious = newCurrent;
        }

        newList.count = count;
        return newList;
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера списка. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (count - 1) + "].");
        }
    }

    private Node<T> getNode(int index) {
        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }
}