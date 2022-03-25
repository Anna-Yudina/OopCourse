package ru.academits.yudina.arraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size; // размер листа (количество заполненных элементов)
    private static final int DEFAULT_CAPACITY = 10; // вместимость по умолчанию
    int modCount; // количество изменений

    // конструктор без аргументов
    public MyArrayList() {
        //noinspection unchecked
        this.items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    // конструктор, принимающий вместимость
    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            //noinspection unchecked
            this.items = (E[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Ошибка: введенный размер списка " + initialCapacity + " должен быть > 0");
        }
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

        if (size == items.length) {
            items = increaseCapacity();
        }

        items[size] = data;
        size++;
        return true;
    }

    // добавление по индексу
    @Override
    public void add(int index, E data) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " должен быть в диапазоне от 0 до " + size);
        }

        modCount++;
        int copySize = size;
        E[] copyItems = this.items;

        if (copySize == copyItems.length) {
            copyItems = increaseCapacity();
        }

        System.arraycopy(copyItems, index, copyItems, index + 1, copySize - index);
        copyItems[index] = data;
        size = copySize + 1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <E> E[] toArray(E[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (E[]) Arrays.copyOf(items, size);
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean remove(Object object) {
        E[] copyItems = items;
        int size = this.size;
        int index = 0;
        modCount++;

        // found - метка выхода из цикла
        found:
        {
            if (object == null) {
                for (; index < size; index++)
                    if (copyItems[index] == null)
                        break found;
            } else {
                for (; index < size; index++)
                    if (object.equals(copyItems[index]))
                        break found;
            }

            return false;
        }

        final int newSize = size - 1;
        System.arraycopy(copyItems, index + 1, copyItems, index, newSize - index);
        this.size = newSize;
        copyItems[this.size] = null;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " должен быть в диапазоне от 0 до " + size);
        }

        modCount++;
        E[] copyItems = items;
        E lastData = copyItems[index];

        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(copyItems, index + 1, copyItems, index, newSize - index);
        }

        size = newSize;
        copyItems[size] = null;
        return lastData;
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
        Object[] inputArray = collection.toArray();
        int inputCapacity = inputArray.length;

        if (inputCapacity == 0) {
            return false;
        }

        modCount++;
        Object[] items = this.items;
        final int copySize = this.size;

        if (inputCapacity > items.length - copySize) {
            items = increaseCapacity(size + inputCapacity);
        }

        System.arraycopy(inputArray, 0, items, size, inputCapacity);
        size = copySize + inputCapacity;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        Object[] inputArray = collection.toArray();
        int inputCapacity = inputArray.length;

        if (inputCapacity == 0) {
            return false;
        }

        modCount++;
        Object[] items = this.items;
        final int copySize = this.size;

        if (inputCapacity > items.length - copySize) {
            items = increaseCapacity(size + inputCapacity);
        }

        System.arraycopy(items, index, items, inputCapacity + index, size - index);
        System.arraycopy(inputArray, 0, items, index, inputCapacity);
        size = copySize + inputCapacity;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] copyItems = items;
        int copySize = size;
        modCount++;

        for (int index = 0; index < copySize; index++) {
            if (collection.contains(copyItems[index])) {
                copyItems[index] = null;
                System.arraycopy(copyItems, index + 1, copyItems, index, copySize - 1 - index);
                index--;
                copySize--;
            }

            size = copySize;
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Object[] copyItems = items;
        int copySize = size;
        modCount++;

        for (int index = 0; index < copySize; index++) {
            if (!collection.contains(copyItems[index])) {
                copyItems[index] = null;
                System.arraycopy(copyItems, index + 1, copyItems, index, copySize - 1 - index);
                index--;
                copySize--;
            }

            size = copySize;
        }

        return true;
    }

    @Override
    public void clear() {
        E[] copyItems = items;
        modCount++;

        for (int i = 0; i < size; i++) {
            copyItems[i] = null;
            size = 0;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " должен быть в диапазоне от 0 до " + size);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " должен быть в диапазоне от 0 до " + size);
        }

        E lastData = items[index];
        items[index] = element;
        return lastData;
    }

    @Override
    public int indexOf(Object object) {
        E[] copyItems = items;

        if (object == null) {
            for (int index = 0; index < size; index++) {
                if (copyItems[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (object.equals(copyItems[index])) {
                    return index;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        E[] copyItems = items;

        if (object == null) {
            for (int index = size - 1; index >= 0; index--) {
                if (copyItems[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = size - 1; index >= 0; index--) {
                if (object.equals(copyItems[index])) {
                    return index;
                }
            }
        }

        return -1;
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
            increaseCapacity(minCapacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            Arrays.copyOf(items, size);
        }
    }

    private E[] increaseCapacity() {
        return increaseCapacity(size + 1);
    }

    private E[] increaseCapacity(int capacity) {
        int newCapacity = capacity / 2 + capacity;
        return items = Arrays.copyOf(items, newCapacity);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, size));
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        public MyIterator() {
        }

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException();
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }
}