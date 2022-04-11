package ru.academits.yudina.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Tree<T extends Number & Comparable<? super T>> {
    private TreeNode<T> root; // корень дерева
    private int count;

    public void add(T data) {
        if (data == null) {
            throw new NullPointerException("Объект равен null");
        }

        if (root == null) {
            root = new TreeNode<>(data);
            count++;
            return;
        }

        TreeNode<T> currentNode = root;

        for (; ; ) {
            if (currentNode.getData().compareTo(data) > 0) {
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

    // получение числа элементов
    public int getCount() {
        return count;
    }

    // обход в ширину
    public String toString() {
        if (root == null) {
            return "{}";
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        StringBuilder string = new StringBuilder("{");

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            string.append(currentNode.getData()).append(", ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }

        string.setLength(string.length() - 2);
        string.append("}");

        return string.toString();
    }

    // поиск узла
    public boolean search(T data) {
        TreeNode<T> nodeParent = getNodeParent(data);

        if (nodeParent == null) {
            return root.getData().compareTo(data) == 0;
        }

        return true;
    }

    public boolean remove(T data) {
        if (data == null) {
            throw new NullPointerException("Объект равен null");
        }

        TreeNode<T> deletedNode;
        TreeNode<T> deletedNodeParent = getNodeParent(data);

        if (deletedNodeParent != null) {
            if (deletedNodeParent.getLeft().getData().compareTo(data) == 0) {
                deletedNode = deletedNodeParent.getLeft();
            } else {
                deletedNode = deletedNodeParent.getRight();
            }
        } else {
            if (root.getData().compareTo(data) == 0) {
                removeNode(root, null);
                return true;
            } else {
                return false;
            }
        }

        if (deletedNode == null) {
            return false;
        }


        if (deletedNode.getLeft() == null) {
            if (deletedNode.getRight() == null) {
                if (deletedNodeParent.getData().compareTo(data) > 0) {
                    deletedNodeParent.setLeft(null);
                } else {
                    deletedNodeParent.setRight(null);
                }
            } else {
                if (deletedNodeParent.getData().compareTo(data) > 0) {
                    deletedNodeParent.setLeft(deletedNode.getRight());
                } else {
                    deletedNodeParent.setRight(deletedNode.getRight());
                }

            }

            count--;
            return true;
        } else {
            if (deletedNode.getRight() == null) {
                if (deletedNodeParent.getData().compareTo(data) > 0) {
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

        if (deletedNodeParent != null) {
            if (deletedNodeParent.getData().compareTo(minNode.getData()) > 0) {
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
        if (data == null) {
            throw new NullPointerException("Объект равен null");
        }

        TreeNode<T> node = root;
        TreeNode<T> nodeParent = null;

        for (; ; ) {
            if (node.getData().compareTo(data) == 0) {
                return nodeParent;
            }

            if (node.getData().compareTo(data) > 0) {
                if (node.getLeft() != null) {
                    nodeParent = node;
                    node = node.getLeft();
                    continue;
                } else {
                    return null;
                }
            }

            if (node.getRight() != null) {
                nodeParent = node;
                node = node.getRight();
            } else {
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
    private void printTreeRecursion(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        System.out.print(node + " ");

        TreeNode<T> leftChild = node.getLeft();
        printTreeRecursion(leftChild);

        TreeNode<T> rightChild = node.getRight();
        printTreeRecursion(rightChild);
    }

    public void printTree() {
        printTreeRecursion(root);
        System.out.println();
    }

    // обход в глубину без рекурсии
    public Object[] toArray() {
        Deque<TreeNode<T>> stack = new LinkedList<>();
        Object[] array = new Object[count];

        stack.addLast(root);

        int i = 0;

        while (!stack.isEmpty()) {
            TreeNode<T> deletedNode = stack.removeLast();
            array[i] = deletedNode.getData();
            i++;

            if (deletedNode.getRight() != null) {
                stack.addLast(deletedNode.getRight());
            }

            if (deletedNode.getLeft() != null) {
                stack.addLast(deletedNode.getLeft());
            }
        }

        return array;
    }
}