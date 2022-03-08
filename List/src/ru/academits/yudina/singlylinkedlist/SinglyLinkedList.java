package ru.academits.yudina.singlylinkedlist;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (this.head == null) {
            throw new NullPointerException("Ошибка. Список пуст");
        }

        return head.getData();
    }

    @Override
    public String toString() {
        if (this.head == null) {
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
        if (count == 0) {
            head = new Node<>(data);
        } else {
            head = new Node<>(data, head);
        }

        count++;
    }

    public T deleteFirst() {
        if (this.head == null) {
            throw new NullPointerException("Ошибка. Список пуст");
        }

        Node<T> deleteHead = head;
        head = head.getNext();
        count--;
        return deleteHead.getData();
    }

    public T get(int index) {
        if (this.head == null) {
            throw new NullPointerException("Ошибка. Список пуст");
        }

        controlIndex(index);

        return getNode(index).getData();
    }

    public T set(int index, T data) {
        controlIndex(index);

        Node<T> node = getNode(index);

        T oldData = node.getData();
        node.setData(data);
        return oldData;
    }

    public T deleteByIndex(int index) {
        controlIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        Node<T> node = getNode(index - 1);

        T data = node.getNext().getData();
        node.setNext(node.getNext().getNext());
        count--;
        return data;
    }

    public void add(int index, T data) {
        controlIndex(index - 1);

        if (index == 0) {
            addFirst(data);
            return;
        }

        Node<T> node = getNode(index - 1);

        node.setNext(new Node<>(data, node.getNext()));
        count++;
    }

    public boolean deleteByDate(T data) {
        if (data == null) {
            return false;
        }

        for (Node<T> current = head, previous = null; current != null; previous = current, current = current.getNext()) {
            if (data.equals(current.getData())) {
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
        if (this.head == null) {
            return null;
        }

        SinglyLinkedList<T> newList = new SinglyLinkedList<>(count);
        newList.head = head;

        for (Node<T> node = head; node != null; node = node.getNext()) {
            new Node<>(node.getData(), node.getNext());
        }

        return newList;
    }

    public void controlIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера списка. " +
                    "Допустимые значения должны быть в диапазоне [0, " + (count - 1) + "].");
        }
    }

    public Node<T> getNode(int index) {
        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }
}