package ru.academits.yudina.array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10; // вместимость по умолчанию

    private E[] items;
    private int size; // размер списка (количество заполненных элементов)
    private int modCount; // количество изменений

    // конструктор без аргументов
    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    // конструктор, принимающий вместимость
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Введенная вместимость списка " + initialCapacity + " должна быть больше либо равна 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
    }

    @Override // получение размера списка
    public int size() {
        return size;
    }

    @Override // проверка на пустоту
    public boolean isEmpty() {
        return size == 0;
    }

    // добавление в конец
    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    // добавление по индексу
    @Override
    public void add(int index, E item) {
        checkIndexForAdd(index);

        modCount++;

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
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
        E removedItem = items[index];
        int newSize = size - 1;

        if (newSize > index) {
            System.arraycopy(items, index + 1, items, index, newSize - index);
        }

        size = newSize;
        items[size] = null;
        return removedItem;
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
        ensureCapacity(size + inputCollectionSize);
        System.arraycopy(items, index, items, inputCollectionSize + index, size - index);
        size += inputCollectionSize;

        int i = index;

        for (E item : collection) {
            items[i] = item;
            i++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                i--;
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isChanged = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(i);
                i--;
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;
        return oldItem;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в диапазоне от 0 до " + (size - 1));
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " должен быть в диапазоне от 0 до " + (size));
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
        if (items.length > 0) {
            items = Arrays.copyOf(items, items.length * 2);
        } else {
            //noinspection unchecked
            items = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В перечислении больше нет элементов.");
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Коллекция изменилась в процессе прохода по коллекции.");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }
}