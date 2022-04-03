package ru.academits.yudina.hashtable;

import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    ArrayList<E>[] listOfList;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int initialCapacity) {
        if (initialCapacity >= 0) {
            //noinspection unchecked
            this.listOfList = (ArrayList<E>[]) new ArrayList[initialCapacity];
        } else {
            throw new IllegalArgumentException("Ошибка: введенный размер списка " + initialCapacity + " должен быть > 0");
        }
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
        int index = 0;

        for (ArrayList<E> arrayList : listOfList) {
            if (arrayList != null) {
                for (E item : arrayList) {
                    objects[index] = item;
                    index++;
                }
            }
        }

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        T[] result;
        int index = 0;

        if (array.length < size) {
            //noinspection unchecked
            result = (T[]) Arrays.copyOf(array, size, array.getClass());

            for (ArrayList<E> arrayList : listOfList) {
                if (arrayList != null) {
                    for (E item : arrayList) {
                        //noinspection unchecked
                        result[index] = (T) item;
                        index++;
                    }
                }
            }

            return result;
        }

        result = array;

        for (ArrayList<E> arrayList : listOfList) {
            if (arrayList != null) {
                for (E item : arrayList) {
                    //noinspection unchecked
                    result[index] = (T) item;
                    index++;
                }
            }
        }

        if (array.length > size)
            array[size] = null;

        return array;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if (listOfList[index] == null) {
            ArrayList<E> arrayList = new ArrayList<>();
            arrayList.add(element);
            listOfList[index] = arrayList;
        } else {
            listOfList[index].add(element);
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (listOfList[index] == null) {
            return false;
        }

        ArrayList<E> arrayList = listOfList[index];

        if (arrayList.size() == 1) {
            arrayList.clear();
            listOfList[index] = null;
            size--;
            return true;
        }

        Iterator<E> iterator = arrayList.iterator();

        while (iterator.hasNext()) {
            E item = iterator.next();
            if (item.equals(object)) {
                iterator.remove();
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.size() == 0) {
            return false;
        }

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isDeleted = false;

        for (Object item : collection) {
            isDeleted = removeAllOneObject(item);
        }

        return isDeleted;
    }

    public boolean removeAllOneObject(Object object) {
        int index = getIndex(object);

        if (listOfList[index] == null) {
            return false;
        }

        ArrayList<E> arrayList = listOfList[index];

        if (arrayList.size() == 1) {
            arrayList.clear();
            listOfList[index] = null;
            size--;
            return true;
        }

        Iterator<E> iterator = arrayList.iterator();

        while (iterator.hasNext()) {
            E item = iterator.next();
            if (item.equals(object)) {
                iterator.remove();
                size--;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        ArrayList<E>[] copyListOfList = listOfList;

        for (int i = 0; i < size; i++) {
            copyListOfList[i] = null;
            size = 0;
        }
    }

    public int getIndex(Object object) {
        return Math.abs(object.hashCode() % listOfList.length);
    }

    @Override
    public boolean contains(Object object) {
        int index = getIndex(object);

        if (listOfList[index] == null) {
            return false;
        }

        ArrayList<E> arrayList = listOfList[index];

        for (E item : arrayList) {
            if (item.equals(object)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
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
        if (collection.size() == 0) {
            return false;
        }

        ArrayList<E> remoteItemsList = new ArrayList<>();

        for (ArrayList<E> list : listOfList) {
            if (list == null) {
                continue;
            }

            for (E item : list) {
                if (!collection.contains(item)) {
                    remoteItemsList.add(item);
                }
            }
        }

        return removeAll(remoteItemsList);
    }


    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        int arrayIndex = 0;
        int listIndex = 0;
        int count = 0;

        public MyIterator() {
        }

        public boolean hasNext() {
            return arrayIndex < listOfList.length && count < size;
        }

        public E next() {
            if (arrayIndex >= listOfList.length && count >= size) {
                throw new NoSuchElementException();
            }

            while (listOfList[arrayIndex] == null) {
                arrayIndex++;
            }

            ArrayList<E> arrayList = listOfList[arrayIndex];
            E currentData = arrayList.get(listIndex);
            listIndex++;
            count++;

            if (listIndex == arrayList.size()) {
                arrayIndex++;
                listIndex = 0;
            }

            return currentData;
        }
    }
}