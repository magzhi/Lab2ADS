package Classes;
import Interfaces.MyList;
import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    private int size;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T item) {
        ensureCapacity();
        array[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        array[index] = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        ensureCapacity();

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(size, item);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        return (T) array[index];
    }

    @Override
    public T getFirst() {
        if (size == 0)
            throw new IndexOutOfBoundsException("List is empty");
        return (T) array[0];
    }

    @Override
    public T getLast() {
        if (size == 0)
            throw new IndexOutOfBoundsException("List is empty");
        return (T) array[size - 1];
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public void sort() {
        mergeSort(0, size-1);
    }

    private void mergeSort(int left, int right) {
        if (left < right){
            int middle = left + (right - left) / 2;

            mergeSort(left, middle);
            mergeSort(middle + 1, right);

            merge(left, middle, right);
        }
    }

    private void merge(int left, int middle, int right){
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Object[] leftArray = new Object[n1];
        Object[] rightArray = new Object[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (((T) leftArray[i]).compareTo((T) rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public void Clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int Size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("No more elements");
                }
                return (T) array[currentIndex++];
            }
        };
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
