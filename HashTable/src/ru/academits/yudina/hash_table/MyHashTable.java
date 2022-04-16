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
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Введенный размер списка " + initialCapacity + " должен быть > 0");
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

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E item : list) {
                    objects[i] = item;
                    i++;
                }
            }
        }

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection unchecked
        T[] result = (T[]) Arrays.copyOf(toArray(), array.length, array.getClass());

        if (array.length > size)
            array[size] = null;

        return result;
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

        boolean isRemoved = lists[index].remove(object);

        if (isRemoved){
            if (lists[index].size() == 0){
                lists[index].clear();
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

        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return true;
        }

        boolean isDeleted = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int sizeBefore = list.size();

                if (list.removeAll(collection)){
                 isDeleted = true;
                }

                int sizeAfter = list.size();
                size -= (sizeBefore - sizeAfter);
                modCount++;
            }
        }

        return isDeleted;
    }

    @Override
    public void clear() {
        Arrays.fill(lists, null);
        size = 0;
    }

    private int getIndex(Object object) {
        if (object == null){
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
        if (collection.isEmpty()) {
            return true;
        }

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
            return true;
        }
        
        boolean isDeleted = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int sizeBefore = list.size();

                if (list.retainAll(collection)) {
                    isDeleted = true;
                }

                int sizeAfter = list.size();
                size -= (sizeBefore - sizeAfter);
                modCount++;
            }
        }

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
            return arrayIndex <= lists.length && count < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В перечислении больше нет элементов.");
            }

            while (lists[arrayIndex] == null) {
                arrayIndex++;
            }

            ArrayList<E> arrayList = lists[arrayIndex];
            E currentData = arrayList.get(listIndex);
            listIndex++;
            count++;

            if (listIndex == arrayList.size()) {
                arrayIndex++;
                listIndex = 0;
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Коллекция изменилась в процессе прохода по коллекции.");
            }

            return currentData;
        }
    }
}