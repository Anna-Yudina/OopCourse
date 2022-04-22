package ru.academits.yudina.tree;

import java.util.Comparator;
import java.util.TreeMap;

class TreeNode<T> {
    private TreeNode<T> left; // левый сын
    private TreeNode<T> right; // правый сын
    private final T data; // полезные данные

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }
}