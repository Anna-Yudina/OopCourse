package ru.academits.yudina.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10; // вместимость по умолчанию

    private E[] items;
    private int size; // размер листа (количество заполненных элементов)
    private int modCount; // количество изменений

    // конструктор без аргументов
    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    // конструктор, принимающий вместимость
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Веденная вместимость списка " + initialCapacity + " должна быть > 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
    }

    @Override // получение размера листа
    public int size() {
        return size;
    }

    @Override // проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }

    // добавление в конец
    @Override
    public boolean add(E data) {
        modCount++;

        add(0, data);

        return true;
    }

    // добавление по индексу
    @Override
    public void add(int index, E data) {
        checkIndex(index);

        modCount++;

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = data;
        size = size + 1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        modCount++;
        E deletedData = items[index];
        int newSize = size - 1;

        if (size > index) {
            System.arraycopy(items, index + 1, items, index, newSize - index);
        }

        size = newSize;
        items[size] = null;
        return deletedData;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
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
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        int inputCollectionSize = collection.size();

        if (inputCollectionSize == 0) {
            return false;
        }

        modCount++;

        if (inputCollectionSize > items.length - size) {
            ensureCapacity(size + inputCollectionSize);
        }

        System.arraycopy(items, index, items, inputCollectionSize + index, size - index);
        System.arraycopy(collection.toArray(), 0, items, index, inputCollectionSize);
        size += inputCollectionSize;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int newSize = size;
        boolean isChange = false;

        for (int i = 0; i < newSize; i++) {
            if (collection.contains(items[i])) {
                items[i] = null;
                remove(i);
                i--;
                newSize--;
                isChange = true;
            }

            size = newSize;
        }

        return isChange;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int newSize = size;
        boolean isChange = false;

        for (int i = 0; i < newSize; i++) {
            if (!collection.contains(items[i])) {
                items[i] = null;
                remove(i);
                i--;
                newSize--;
                modCount++;
                isChange = true;
            }

            size = newSize;
        }

        return isChange;
    }

    @Override
    public void clear() {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                items[i] = null;
            }

            size = 0;
            modCount++;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldData = items[index];
        items[index] = element;
        return oldData;
    }

    @Override
    public int indexOf(Object object) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(object, items[index])) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int index = size - 1; index >= 0; index--) {
            if (Objects.equals(object, items[index])) {
                return index;
            }
        }

        return -1;
    }

    public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в диапазоне от 0 до " + (size - 1));
        }
    }

    // реализовывать не нужно
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    // реализовывать не нужно
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    // реализовывать не нужно
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");

        for (int i = 0; i < size; i++) {
            result.append(get(i));

            if (i != size - 1) {
                result.append(", ");
            }
        }

        result.append("]");

        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В перечислении больше нет элементов.");
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Коллекция модифицировалась в процессе прохода по коллекции.");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }
}