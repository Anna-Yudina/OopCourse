package ru.academits.yudina.hash_table;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Введенная длина массива " + initialCapacity + " должна быть > 0");
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        int i = 0;

        for (E item : this) {
            objects[i] = item;
            i++;
        }

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        modCount++;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(object)) {
            if (lists[index].size() == 0) {
                lists[index] = null;
            }

            size--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        boolean isDeleted = false;
        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int sizeBefore = list.size();

                if (list.removeAll(collection)) {
                    isDeleted = true;
                    int sizeAfter = list.size();
                    size -= (sizeBefore - sizeAfter);

                    if (list.size() == 0) {
                        lists[i] = null;
                    }
                }
            }

            i++;
        }

        modCount++;

        return isDeleted;
    }

    @Override
    public void clear() {
        if (lists == null) {
            return;
        }

        Arrays.fill(lists, null);
        size = 0;
        modCount++;
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
    }

    @Override
    public boolean contains(Object object) {
        int index = getIndex(object);

        if (lists[index] == null) {
            return false;
        }

        return lists[index].contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            if (lists == null) {
                return false;
            }

            clear();
            return true;
        }

        boolean isDeleted = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int sizeBefore = list.size();

                if (list.retainAll(collection)) {
                    isDeleted = true;
                    int sizeAfter = list.size();
                    size -= (sizeBefore - sizeAfter);
                }
            }
        }

        modCount++;

        return isDeleted;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private int arrayIndex = 0;
        private int listIndex = 0;
        private int count = 0;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return size > count;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В перечислении больше нет элементов.");
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Коллекция изменилась в процессе прохода по коллекции.");
            }

            while (lists[arrayIndex] == null || lists[arrayIndex].size() == 0) {
                arrayIndex++;
            }

            E currentData = lists[arrayIndex].get(listIndex);
            listIndex++;
            count++;

            if (listIndex == lists[arrayIndex].size()) {
                arrayIndex++;
                listIndex = 0;
            }

            return currentData;
        }
    }
}