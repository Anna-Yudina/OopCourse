package ru.academits.yudina.singlylinkedlist;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(Node<T> head) {
        this.head = head;
    }

    public int getCount() {
        return count;
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Node<T> node = head; node != null; node = node.getNext()) {
            stringBuilder.append(node.getData()).append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void addFirstElement(T data) {
        Node<T> tempNode = new Node<>();
        tempNode.setData(data);

        if (count != 0) {
            tempNode.setNext(head);
        }

        head = tempNode;
        count++;
    }

    public Node<T> deleteFirstElement() {
        Node<T> tempHead = head;
        head = head.getNext();
        count--;
        return tempHead;
    }

    public T getElementValue(int index) {
        Node<T> node = head;

        if (count <= index) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размера списка. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (count - 1) + "].");
        }

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node.getData();
    }

    public T changeElementValue(int index, T value) {
        Node<T> node = head;

        if (count <= index) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размера списка. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (count - 1) + "].");
        }

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        T oldValue = node.getData();
        node.setData(value);
        return oldValue;
    }

    public T deleteElement(int index) {
        Node<T> node = head;

        if (count <= index) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размера списка. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (count - 1) + "].");
        }

        for (int i = 0; i < index - 1; i++) {
            node = node.getNext();
        }

        T value = node.getNext().getData();
        node.setNext(node.getNext().getNext());
        count--;
        return value;
    }

    public void addElement(int index, T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> node = head;

        if (count <= index) {
            throw new IllegalArgumentException("Индекс " + index + " выходит за пределы размера списка. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (count - 1) + "].");
        }

        if (index == 0) {
            addFirstElement(value);
        } else {
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }

            newNode.setNext(node.getNext());
            node.setNext(newNode);
            count++;
        }
    }

    public boolean deleteElementValue(T value) {
        boolean isDelete = false;
        for (Node<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (value.equals(p.getData())) {
                if (prev == null) {
                    deleteFirstElement();
                } else {
                    prev.setNext(p.getNext());
                    count--;
                }

                isDelete = true;
                break;
            }
        }

        return isDelete;
    }

    public void reverse() {
        Node<T> current = head;
        Node<T> previous = null;
        Node<T> forward;

        while (current != null) {
            forward = current.getNext();
            current.setNext(previous);
            previous = current;
            current = forward;
        }

        head = previous;
    }

    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        newList.head = head;

        for (Node<T> node = head; node != null; node = node.getNext()) {
            new Node<>(node.getData(), node.getNext());
        }

        return newList;
    }
}