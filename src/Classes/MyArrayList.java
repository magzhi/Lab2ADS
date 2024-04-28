package Classes;
import Interfaces.MyList;
import java.util.Iterator;

/**
 * Public MyArrayList<T> class. It implements MyList<T> interface functionality and extends Comparable<T>
 *     MyList interface is the minimum set of methods that were given for this assignment
 *     Comparable is used to implement a sorting algorithm into this class.
 *
 * @param <T> an Object type of the created ArrayList.
 */
public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    /**
     * 3 private data types included in each MyArrayList
     *
     * @DEFAULT_CAPACITY is the static setting of the initial capacity of each array that stores our list array,
     *      I made it 16 as multiple of power of 2 seemed right for the performance
     * @array an array that stores our list's array
     *
     * @size is the current size of the list array, not to be confused with the capacity
     */
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    private int size;

    /**
     * Default generic constructor for MyArrayList
     * creates an empty array with the preset capacity
     * and sets the default size to be empty
     */
    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * This method first calls "ensureCapacity()" method to check if our array can fit another element,
     * then it adds said element to the array
     *
     * @param item is the Object getting added to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void add(T item) {
        ensureCapacity();
        array[size++] = item;
    }

    /**
     * This method first ensures if the index is actually indexed within the array and exists,
     * then it either throws an out-of-bounds error
     * or it sets the Object at the index location
     *
     * @param index is the index of where we want to set the Object
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        array[index] = item;
    }

    /**
     * This method first ensures if the index is actually indexed within the array and exists,
     * then it either throws an out-of-bounds error
     * or it then checks whether there is enough space in the current array
     * then it shifts all elements that are after the index
     * and only then it sets the Object at the index location
     * and increases size by 1
     *
     * @param index is the index of where we want to set the Object
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
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

    /**
     * This method calls above "add()" method with just 0 index
     *
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    /**
     * This method calls twice above "add()" method with just 'size' index, which puts it in the end of the array
     *
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void addLast(T item) {
        add(size, item);
    }

    /**
     * This method first ensures if the index is actually indexed within the array and exists,
     * then it either throws an out-of-bounds error
     * or returns the Object indexed at the location
     *
     * @param index the location queried to be found
     * @return returns Object located at that index of the array
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        return (T) array[index];
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        // throwing out of bounds error in case it is empty then return size-1 element
        if (size == 0)
            throw new IndexOutOfBoundsException("List is empty");
        return (T) array[size - 1];
    }

    @Override
    public void remove(int index) {
        // throwing out of bounds error in case of mis-input then remove element
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        // shifting all elements after removed element
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public void removeFirst() {
        // call remove at 0 element
        remove(0);
    }

    @Override
    public void removeLast() {
        // call remove at size-1 element
        remove(size - 1);
    }

    @Override
    public void sort() {
        // calling mergesort
        mergeSort(0, size-1);
    }

    private void mergeSort(int left, int right) {
        // recursive mergesort method
        if (left < right){
            int middle = left + (right - left) / 2;

            // recursiveness, split and conquer
            mergeSort(left, middle);
            mergeSort(middle + 1, right);

            // re-attach split parts will be called at the end after
            // each mergeSort is split down to individual elements
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
        // search for Object and return index or -1 in case it is not there
        for (int i = 0; i < size; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        // same as above but in reverse
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        // use indexOf to get a bool answer
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        // returns an arrayified version of the list back
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public void Clear() {
        // deletes all in the array by subbing a new empty array for existing
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int Size() {
        // sends un-interactable size property back
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            // indexing iterator functionality
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                // checking if next even exists by checking if currentIndex will overflow
                return currentIndex < size;
            }

            @Override
            public T next() {
                // throwing out of bounds error in case of mis-input then return next element
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("No more elements");
                }
                return (T) array[currentIndex++];
            }
        };
    }

    private void ensureCapacity() {
        // private method that checks if current array that is storing list has enough space
        if (size == array.length) {
            // if not, then it proceeds to double the size by creating a new array and filling it back with the old one
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
