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
     * then it adds said element to the array at the end
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

    /**
     * This method calls above "get()" method with just 0 index,
     * which returns 0 index Object or an error
     *
     * @return returns Object located at 0 index of the array
     */
    @Override
    public T getFirst() {
        return get(0);
    }

    /**
     * This method calls above "get()" method with just 'size-1' index,
     * which returns 'size-1' index Object or an error
     *
     * @return returns Object located at 'size-1' index of the array
     */
    @Override
    public T getLast() {
        return get(size-1);
    }

    /**
     * This method first ensures if the index is actually indexed within the array and exists,
     * then it either throws an out-of-bounds error
     * or shifts all elements that are to the right of the index to the left
     *
     * @param index the location queried to be removed
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds");
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    /**
     * This method calls above "remove()" method with just 0 index,
     * which removed Object at 0 index
     */
    @Override
    public void removeFirst() {
        remove(0);
    }

    /**
     * This method calls above "remove()" method with just 'size-1' index,
     * which removed Object at 'size-1' index
     */
    @Override
    public void removeLast() {
        remove(size - 1);
    }

    /**
     * This method calls sorting method for the array
     * I chose mergeSort because it has a constant complexity
     * at both worst and best cases, which is n*log(n)
     */
    @Override
    public void sort() {
        mergeSort(0, size-1);
    }

    /**
     * This is a recursive method to sort an array.
     * The idea is to split up array into 2 chunks and do that recursively,
     * then send up the resulting merged and sorted parts back up
     * which is a split and conquer type algorithm.
     *
     * @param left leftmost index of the current recursive call
     * @param right rightmost index of the current recursive call
     */
    private void mergeSort(int left, int right) {
        if (left < right){
            int middle = left + (right - left) / 2;

            mergeSort(left, middle);
            mergeSort(middle + 1, right);

            merge(left, middle, right);
        }
    }

    /**
     * This is the merge part of the mergeSort algorithm
     * first 2 lines are there to count how many elements each side has
     * then it creates temporary 2 empty arrays with their respective sizes n1, n2
     * then it fills those temp arrays with their elements
     * then through comparison, it orders all elements
     * and reshuffles them into both temp arrays in correct order.
     * This works because both arrays are always ordered at the start
     * as they come from the recursive mergeSort, where the first merge()
     * begins when both leftArray and rightArray has just single element
     * so they both sides always end up sorted at the start of the next merge().
     * After ordered reshuffle is done, the elements are re-added to the original array.
     *
     * @param left index of the leftArray start position
     * @param middle index of the leftArray end position and rightArray start position
     * @param right index of the rightArray end position
     */
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

    /**
     * This method searches for a given Object and returns its index
     * Search is from front to back
     * If it is not found it returns -1
     *
     * @param object is the Object that we are searching for
     * @return index of the found object or -1 in case it is not found
     */
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method searches for a given Object and returns its index in reverse
     * Search is from back to front
     * If it is not found it returns -1
     *
     * @param object is the Object that we are searching for
     * @return index of the found object or -1 in case it is not found
     */
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method searches for a given Object and returns whether it exists
     * Search is from front to back by using indexOf() method
     *
     * @param object is the Object that we are searching for
     * @return boolean on whether such Object is in the array or not
     */
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    /**
     * This method gives back the raw array version of the List
     * by copying our current array up to our current size.
     * Size limit is important as otherwise our raw array would contain empty un-initialised elements
     *
     * @return raw array of the Object[] form
     */
    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    /**
     * This method resets our array to empty state
     */
    @Override
    public void Clear() {
        // deletes all in the array by subbing a new empty array for existing
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * This method returns current List size
     *
     * @return gives back List size
     */
    @Override
    public int Size() {
        return size;
    }

    /**
     * This ia overriden iterator method used in counting
     * The Iterator Object contains of a single data point which is the current index counted thus far
     * and 2 methods:
     * hasNext() which checks if the next index even exists at all
     * next() which returns back the next Object from the array or gives an out-of-bounds error
     *
     * @return this gives back Iterator<T> Object
     */
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

    /**
     * This is a supporting method which is called whenever we are adding something to the List.
     * If the current raw array does not have enough space, it would create a new array
     * with double capacity, and copy old one into the new one.
     */
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
