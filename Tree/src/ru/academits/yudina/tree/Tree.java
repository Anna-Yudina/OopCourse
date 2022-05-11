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
        } else {
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
        TreeNode<T> deletedNodeParent = getParentNode(data);

        if (deletedNodeParent == null) {
            if (compare(root.getData(), data) != 0) {
                return false;
            }

            removeNode(root, null);
            return true;
        }

        TreeNode<T> deletedNode;

        if (compare(deletedNodeParent.getLeft().getData(), data) == 0) {
            deletedNode = deletedNodeParent.getLeft();
        } else {
            deletedNode = deletedNodeParent.getRight();
        }

        int comparisonResult = compare(deletedNodeParent.getData(), data);

        if (deletedNode.getLeft() == null) {
            if (deletedNode.getRight() == null) {
                if (comparisonResult > 0) {
                    deletedNodeParent.setLeft(null);
                } else {
                    deletedNodeParent.setRight(null);
                }
            } else {
                if (comparisonResult > 0) {
                    deletedNodeParent.setLeft(deletedNode.getRight());
                } else {
                    deletedNodeParent.setRight(deletedNode.getRight());
                }
            }

            count--;
            return true;
        }

        if (deletedNode.getRight() == null) {
            if (comparisonResult > 0) {
                deletedNodeParent.setLeft(deletedNode.getLeft());
            } else {
                deletedNodeParent.setRight(deletedNode.getLeft());
            }

            count--;
            return true;
        }

        removeNode(deletedNode, deletedNodeParent);

        return true;
    }

    private void removeNode(TreeNode<T> deletedNode, TreeNode<T> deletedNodeParent) {
        TreeNode<T> minNodeParent = getMinNodeParent(deletedNode);
        TreeNode<T> minNode;
        int comparisonResult = compare(deletedNode.getData(), minNodeParent.getData());

        if (comparisonResult == 0) {
            minNode = minNodeParent.getRight();
        } else {
            minNode = minNodeParent.getLeft();
            minNodeParent.setLeft(minNode.getRight());
        }

        TreeNode<T> deletedNodeLeftChild = deletedNode.getLeft();
        TreeNode<T> deletedNodeRightChild = deletedNode.getRight();

        if (deletedNodeParent == null) {
            root = minNode;
        } else {
            if (deletedNodeParent.getLeft() == deletedNode) {
                deletedNodeParent.setLeft(minNode);
            } else {
                deletedNodeParent.setRight(minNode);
            }
        }

        if (comparisonResult != 0) {
            minNode.setRight(deletedNodeRightChild);
        }

        minNode.setLeft(deletedNodeLeftChild);
        count--;
    }

    public TreeNode<T> getParentNode(T data) {
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

    private TreeNode<T> getMinNodeParent(TreeNode<T> deletedNode) {
        TreeNode<T> minNode = deletedNode.getRight();
        TreeNode<T> minNodeParent = deletedNode;

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