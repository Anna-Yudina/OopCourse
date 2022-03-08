package ru.academits.yudina.singlylinkedlist;

class Node<T> {
    protected T data;
    protected Node<T> next;

    protected Node(T data) {
        this.data = data;
    }

    protected Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    protected T getData() {
        return data;
    }

    protected Node<T> getNext() {
        return next;
    }

    protected void setData(T data) {
        this.data = data;
    }

    protected void setNext(Node<T> next) {
        this.next = next;
    }
}