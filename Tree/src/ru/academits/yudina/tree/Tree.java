package ru.academits.yudina.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root; // корень дерева
    private int count;
    private final Comparator<? super T> comparator;

    public Tree() {
        comparator = null;
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

        int comparatorResult; // результат компаратора -1 1 0

        Comparator<? super T> cpr = comparator;

        if (cpr != null) {
            for (; ; ) {
                comparatorResult = cpr.compare(data, currentNode.getData());

                if (comparatorResult < 0) {
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
        } else {
            for (; ; ) {
                comparatorResult = myCompareTo(data, currentNode.getData());

                if (comparatorResult < 0) {
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
    }

    private int myCompareTo(T data, T currentData) {
        Comparable<? super T> comparableData = (Comparable<? super T>) data;

        if (comparableData == null && currentData == null) {
            return 0;
        } else if (comparableData == null && currentData != null) {
            return -1;
        } else if (comparableData != null && currentData == null) {
            return 1;
        } else {
            if (Objects.equals(comparableData, currentData)) {
                return 0;
            } else if (comparableData.compareTo(currentData) > 0) {
                return 1;
            }
            return -1;
        }
    }

    // получение числа элементов
    public int getCount() {
        return count;
    }

    public boolean contains(T data) {
        TreeNode<T> nodeParent = getNodeParent(data);

        if (nodeParent == null) {
            return myCompareTo(root.getData(), data) == 0;
        }

        return true;
    }

    public boolean remove(T data) {
        if (!contains(data)) {
            return false;
        }

        TreeNode<T> deletedNode;
        TreeNode<T> deletedNodeParent = getNodeParent(data);
        int comparatorResult; // результат компаратора -1 1 0
        Comparator<? super T> cpr = comparator;

        if (deletedNodeParent != null) {
            if (cpr != null) {
                comparatorResult = cpr.compare(deletedNodeParent.getLeft().getData(), data);
            } else {
                comparatorResult = myCompareTo(deletedNodeParent.getLeft().getData(), data);
            }

            if (comparatorResult == 0) {
                deletedNode = deletedNodeParent.getLeft();
            } else {
                deletedNode = deletedNodeParent.getRight();
            }
        } else {
            if (cpr != null) {
                comparatorResult = cpr.compare(root.getData(), data);
            } else {
                comparatorResult = myCompareTo(root.getData(), data);
            }

            if (comparatorResult == 0) {
                removeNode(root, null);
                return true;
            }
            return false;
        }

        if (deletedNode == null) {
            return false;
        }

        if (cpr != null) {
            comparatorResult = cpr.compare(deletedNodeParent.getData(), data);
        } else {
            comparatorResult = myCompareTo(deletedNodeParent.getData(), data);
        }

        if (deletedNode.getLeft() == null) {
            if (deletedNode.getRight() == null) {
                if (comparatorResult > 0) {
                    deletedNodeParent.setLeft(null);
                } else {
                    deletedNodeParent.setRight(null);
                }
            } else {
                if (comparatorResult > 0) {
                    deletedNodeParent.setLeft(deletedNode.getRight());
                } else {
                    deletedNodeParent.setRight(deletedNode.getRight());
                }

            }

            count--;
            return true;
        } else {
            if (deletedNode.getRight() == null) {
                if (comparatorResult > 0) {
                    deletedNodeParent.setLeft(deletedNode.getLeft());
                } else {
                    deletedNodeParent.setRight(deletedNode.getLeft());
                }

                count--;
                return true;
            } else {
                removeNode(deletedNode, deletedNodeParent);
            }
        }

        return true;
    }

    private void removeNode(TreeNode<T> deletedNode, TreeNode<T> deletedNodeParent) {
        TreeNode<T> minNodeParent = getMinNodeParent(deletedNode);
        TreeNode<T> minNode;

        if (Objects.equals(deletedNode, minNodeParent)) {
            minNode = minNodeParent.getRight();
        } else {
            minNode = minNodeParent.getLeft();
        }

        if (!Objects.equals(deletedNode, minNodeParent)) {
            minNodeParent.setLeft(minNode.getRight());
        }

        TreeNode<T> leftChildDeleteNode = deletedNode.getLeft();
        TreeNode<T> rightChildDeleteNode = deletedNode.getRight();

        int comparatorResult; // результат компаратора -1 1 0
        Comparator<? super T> cpr = comparator;

        if (deletedNodeParent != null) {
            if (cpr != null) {
                comparatorResult = cpr.compare(deletedNodeParent.getData(), minNode.getData());
            } else {
                comparatorResult = myCompareTo(deletedNodeParent.getData(), minNode.getData());
            }

            if (comparatorResult > 0) {
                deletedNodeParent.setLeft(minNode);
            } else {
                deletedNodeParent.setRight(minNode);
            }
        } else {
            root = minNode;
        }

        if (!Objects.equals(deletedNode, minNodeParent)) {
            minNode.setRight(rightChildDeleteNode);
        }

        minNode.setLeft(leftChildDeleteNode);
        count--;
    }

    private TreeNode<T> getNodeParent(T data) {
        TreeNode<T> node = root;
        TreeNode<T> nodeParent = null;

        int comparatorResult; // результат компаратора -1 1 0
        Comparator<? super T> cpr = comparator;

        if (cpr != null) {
            for (; ; ) {
                comparatorResult = cpr.compare(node.getData(), data);

                if (comparatorResult == 0) {
                    return nodeParent;
                }

                if (comparatorResult > 0) {
                    if (node.getLeft() != null) {
                        nodeParent = node;
                        node = node.getLeft();
                        continue;
                    }
                    return null;
                }

                if (node.getRight() != null) {
                    nodeParent = node;
                    node = node.getRight();
                }

                return null;
            }
        } else {
            for (; ; ) {
                comparatorResult = myCompareTo(node.getData(), data);

                if (comparatorResult == 0) {
                    return nodeParent;
                }

                if (comparatorResult > 0) {
                    if (node.getLeft() != null) {
                        nodeParent = node;
                        node = node.getLeft();
                        continue;
                    }

                    return null;
                }

                if (node.getRight() != null) {
                    nodeParent = node;
                    node = node.getRight();
                }

                return null;
            }
        }
    }

    public TreeNode<T> getMinNodeParent(TreeNode<T> deletedNode) {
        TreeNode<T> minNode = deletedNode.getRight();
        TreeNode<T> minNodeParent = deletedNode;

        for (; ; ) {
            if (minNode.getLeft() == null) {
                break;
            } else {
                minNodeParent = minNode;
                minNode = minNode.getLeft();
            }
        }

        return minNodeParent;
    }

    // обход в глубину с рекурсией
    private void depthTraversalRecursionInner(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        TreeNode<T> leftChild = node.getLeft();
        depthTraversalRecursionInner(leftChild, consumer);

        TreeNode<T> rightChild = node.getRight();
        depthTraversalRecursionInner(rightChild, consumer);
    }

    public void depthTraversalRecursion(Consumer<T> consumer) {
        depthTraversalRecursionInner(root, consumer);
        System.out.println();
    }

    // обход в глубину без рекурсии
    public void depthTraversalNoRecursion(Consumer<T> consumer) {
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