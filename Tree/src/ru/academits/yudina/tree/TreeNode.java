package ru.academits.yudina.tree;

class TreeNode<T extends Number> {
    private TreeNode<T> left; // левый сын
    private TreeNode<T> right; // правый сын
    private T data; // полезные данные

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T data) {
        this.left = left;
        this.right = right;
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

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return  "" + data;
    }
}

