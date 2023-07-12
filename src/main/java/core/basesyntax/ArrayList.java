package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_COEFFICIENT = 1.5;
    private static final double EMPTY_SIZE = 0;
    private T[] objects = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        growIfArrayFull();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        checkBoundForAdding(index);
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > objects.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            objects[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkForBounds(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkForBounds(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForBounds(index);
        T temp = objects[index];
        size--;
        System.arraycopy(objects, index + 1, objects, index, size - index);
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((objects[i] != null && objects[i].equals(element)) || (objects[i] == null)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no " + element + " in list");
    }

    public void checkForBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    public void checkBoundForAdding(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    public void growIfArrayFull() {
        if (size == objects.length) {
            grow();
        }
    }

    public void grow() {
        int newSize = (int) (objects.length * INCREASE_COEFFICIENT);
        T[] oldArray = objects;
        objects = (T[]) new Object[newSize];
        System.arraycopy(oldArray, 0, objects, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }
}
