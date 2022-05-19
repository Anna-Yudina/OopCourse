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
            if (compare(data, currentNode.getData()) < 0) {
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


    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<? super T>) data1).compareTo(data2);
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
            return compare(root.getData(), data) == 0;
        }

        return true;
    }

    public boolean remove(T data) {
        TreeNode<T> removedNodeParent = getParentNode(data);

        if (removedNodeParent == null) {
            if (compare(root.getData(), data) != 0) {
                return false;
            }

            removeNode(root, null);
            return true;
        }

        TreeNode<T> removedNode;

        int comparisonResult = compare(removedNodeParent.getData(), data);

        if (comparisonResult > 0) {
            removedNode = removedNodeParent.getLeft();
        } else {
            removedNode = removedNodeParent.getRight();
        }

        if (removedNode.getLeft() == null) {
            if (comparisonResult > 0) {
                removedNodeParent.setLeft(removedNode.getRight());
            } else {
                removedNodeParent.setRight(removedNode.getRight());
            }

            count--;
            return true;
        }

        if (removedNode.getRight() == null) {
            if (comparisonResult > 0) {
                removedNodeParent.setLeft(removedNode.getLeft());
            } else {
                removedNodeParent.setRight(removedNode.getLeft());
            }

            count--;
            return true;
        }

        removeNode(removedNode, removedNodeParent);

        return true;
    }

    private void removeNode(TreeNode<T> removedNode, TreeNode<T> removedNodeParent) {
        TreeNode<T> minNodeParent = getMinNodeParent(removedNode);
        TreeNode<T> minNode;

        if (removedNode == minNodeParent) {
            minNode = minNodeParent.getRight();
        } else {
            minNode = minNodeParent.getLeft();
            minNodeParent.setLeft(minNode.getRight());
        }

        TreeNode<T> removedNodeLeftChild = removedNode.getLeft();
        TreeNode<T> removedNodeRightChild = removedNode.getRight();

        if (removedNodeParent == null) {
            root = minNode;
        } else {
            if (removedNodeParent.getLeft() == removedNode) {
                removedNodeParent.setLeft(minNode);
            } else {
                removedNodeParent.setRight(minNode);
            }
        }

        if (removedNode != minNodeParent) {
            minNode.setRight(removedNodeRightChild);
        }

        minNode.setLeft(removedNodeLeftChild);
        count--;
    }

    private TreeNode<T> getParentNode(T data) {
        if (root == null) {
            return null;
        }

        TreeNode<T> node = root;
        TreeNode<T> parentNode = null;

        for (; ; ) {
            int comparisonResult = compare(node.getData(), data);

            if (comparisonResult == 0) {
                return parentNode;
            }

            if (comparisonResult > 0) {
                if (node.getLeft() != null) {
                    parentNode = node;
                    node = node.getLeft();
                    continue;
                }

                return null;
            }

            if (node.getRight() != null) {
                parentNode = node;
                node = node.getRight();
                continue;
            }

            return null;
        }
    }

    private TreeNode<T> getMinNodeParent(TreeNode<T> removedNode) {
        TreeNode<T> minNode = removedNode.getRight();
        TreeNode<T> minNodeParent = removedNode;

        for (; ; ) {
            if (minNode.getLeft() == null) {
                return minNodeParent;
            }

            minNodeParent = minNode;
            minNode = minNode.getLeft();
        }
    }

    // обход в глубину с рекурсией
    private void depthTraversalWithRecursion(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        depthTraversalWithRecursion(node.getLeft(), consumer);

        depthTraversalWithRecursion(node.getRight(), consumer);
    }

    public void depthTraversalWithRecursion(Consumer<T> consumer) {
        depthTraversalWithRecursion(root, consumer);
    }

    // обход в глубину без рекурсии
    public void depthTraversal(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.removeLast();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
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