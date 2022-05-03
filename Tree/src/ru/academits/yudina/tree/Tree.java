package ru.academits.yudina.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int count;
    private Comparator<? super T> comparator;

    public Tree() {
    }

    public Tree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            count++;
            return;
        }

        TreeNode<T> currentNode = root;

        for (; ; ) {
            if (universalCompare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    count++;
                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    count++;
                    return;
                }
            }
        }
    }


    private int universalCompare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        } else {
            if (data1 == null && data2 == null) {
                return 0;
            } else if (data1 == null && data2 != null) {
                return -1;
            } else if (data1 != null && data2 == null) {
                return 1;
            }

            return ((Comparable<? super T>) data1).compareTo(data2);
        }
    }

    public int getCount() {
        return count;
    }

    public boolean contains(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> parentNode = getParentNode(data);

        if (parentNode == null) {
            return universalCompare(root.getData(), data) == 0;
        }

        return true;
    }

    public boolean remove(T data) {
        TreeNode<T> deletedNode;
        TreeNode<T> deletedParentNode = getParentNode(data);

        if (deletedParentNode == null) {
            if (universalCompare(root.getData(), data) != 0) {
                return false;
            }
        }

        if (deletedParentNode == null) {
            if (universalCompare(root.getData(), data) == 0) {
                removeNode(root, null);
                return true;
            }

            return false;
        }

        if (universalCompare(deletedParentNode.getLeft().getData(), data) == 0) {
            deletedNode = deletedParentNode.getLeft();
        } else {
            deletedNode = deletedParentNode.getRight();
        }

        if (deletedNode == null) {
            return false;
        }

        int comparatorResult = universalCompare(deletedParentNode.getData(), data);

        if (deletedNode.getLeft() == null) {
            if (deletedNode.getRight() == null) {
                if (comparatorResult > 0) {
                    deletedParentNode.setLeft(null);
                } else {
                    deletedParentNode.setRight(null);
                }
            } else {
                if (comparatorResult > 0) {
                    deletedParentNode.setLeft(deletedNode.getRight());
                } else {
                    deletedParentNode.setRight(deletedNode.getRight());
                }
            }

            count--;
            return true;
        } else {
            if (deletedNode.getRight() == null) {
                if (comparatorResult > 0) {
                    deletedParentNode.setLeft(deletedNode.getLeft());
                } else {
                    deletedParentNode.setRight(deletedNode.getLeft());
                }

                count--;
                return true;
            }

            removeNode(deletedNode, deletedParentNode);
        }

        return true;
    }

    private void removeNode(TreeNode<T> deletedNode, TreeNode<T> deletedParentNode) {
        TreeNode<T> minParentNode = getMinParentNode(deletedNode);
        TreeNode<T> minNode;

        if (universalCompare(deletedNode.getData(), minParentNode.getData()) == 0) {
            minNode = minParentNode.getRight();
        } else {
            minNode = minParentNode.getLeft();
        }

        if (universalCompare(deletedNode.getData(), minParentNode.getData()) != 0) {
            minParentNode.setLeft(minNode.getRight());
        }

        TreeNode<T> deletedLeftChildNode = deletedNode.getLeft();
        TreeNode<T> deletedRightChildNode = deletedNode.getRight();

        if (deletedParentNode != null) {
            if (deletedParentNode.getLeft() == deletedNode) {
                deletedParentNode.setLeft(minNode);
            } else {
                deletedParentNode.setRight(minNode);
            }
        } else {
            root = minNode;
        }

        if (universalCompare(deletedNode.getData(), minParentNode.getData()) != 0) {
            minNode.setRight(deletedRightChildNode);
        }

        minNode.setLeft(deletedLeftChildNode);
        count--;
    }

    public TreeNode<T> getParentNode(T data) {
        TreeNode<T> node = root;
        TreeNode<T> parentNode = null;

        int comparatorResult;

        for (; ; ) {
            comparatorResult = universalCompare(node.getData(), data);

            if (comparatorResult == 0) {
                return parentNode;
            }

            TreeNode<T> temp;

            if (comparatorResult > 0) {
                if (node.getLeft() != null) {
                    temp = node;
                    parentNode = node;
                    node = temp.getLeft();
                    continue;
                }

                return null;
            }

            if (node.getRight() != null) {
                temp = node;
                parentNode = node;
                node = temp.getRight();
                continue;
            }

            return null;
        }
    }

    private TreeNode<T> getMinParentNode(TreeNode<T> deletedNode) {
        TreeNode<T> minNode = deletedNode.getRight();
        TreeNode<T> minParentNode = deletedNode;

        for (; ; ) {
            if (minNode.getLeft() == null) {
                return minParentNode;
            } else {
                minParentNode = minNode;
                minNode = minNode.getLeft();
            }
        }
    }

    // обход в глубину с рекурсией
    private void depthTraversalRecursionInner(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        depthTraversalRecursionInner(node.getLeft(), consumer);

        depthTraversalRecursionInner(node.getRight(), consumer);
    }

    public void depthTraversalRecursion(Consumer<T> consumer) {
        depthTraversalRecursionInner(root, consumer);
    }

    // обход в глубину без рекурсии
    public void depthTraversal(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> deletedNode = stack.removeLast();
            consumer.accept(deletedNode.getData());

            if (deletedNode.getRight() != null) {
                stack.addLast(deletedNode.getRight());
            }

            if (deletedNode.getLeft() != null) {
                stack.addLast(deletedNode.getLeft());
            }
        }
    }

    // обход в ширину
    public void breadthTraversal(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    // метод сделан для тренировки
    public String toString() {
        if (root == null) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        Consumer<T> consumer = x -> stringBuilder.append(x).append(", ");
        breadthTraversal(consumer);

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}