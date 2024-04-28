package Classes;

public class MyMinHeap<T extends Comparable<T>> implements Iterable<T> {
    /**
     * MyMinHeap is implemented through MyArrayList instead of MyLinkedList
     * because MyArrayList provide better and faster functionality
     * for MyMinHeap methods, as we need access to elements
     * through their index values to be quick.
     */
    private MyArrayList<T> heap;

    /**
     * Default constructor just initialises MyArrayList Object.
     */
    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    /**
     * This method inserts item to the List
     * and orders it in the array through traverseUp()s method
     *
     * @param item is the Object added to the MinHeap
     */
    public void insert(T item) {
        heap.add(item);
        traverseUp(heap.Size() - 1);
    }

    /**
     * This method gives back the Minimum value in the List and removes it from the list
     * Then it reorders the whole list through heapify() method
     *
     * @return Minimum value in the List
     */
    public T extractMin() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        T min = heap.get(0);
        T lastItem = heap.get(heap.Size() - 1);
        heap.set(0, lastItem);
        heap.remove(heap.Size() - 1);
        heapify(0);
        return min;
    }

    /**
     * This method gives back the Minimum value in the List
     *
     * @return Minimum value in the List
     */
    public T getMin() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return heap.get(0);
    }

    /**
     * This method checks if MinHeap is empty
     *
     * @return boolean whether MinHeap is empty
     */
    public boolean isEmpty() {
        return heap.Size() == 0;
    }

    /**
     * This method returns MinHeap's size
     *
     * @return int size of the MinHeap List
     */
    public int size() {
        return heap.Size();
    }

    /**
     * This method returns ArrayList's iterator method
     * as it will act the same way
     *
     * @return the iterator Object
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return heap.iterator();
    }

    /**
     * This is a supporting recursive function that reorders the whole array
     * in the MinHeap format, such that for each index, left and right indexes
     * are in 2i and 2i+1 forms. If smallestIndex is not the same as initial index,
     * then it swaps them and recursively calls function again.
     *
     * @param index the index from where the heapification starts
     */
    private void heapify(int index) {
        int leftChildIndex = leftChildOf(index);
        int rightChildIndex = rightChildOf(index);
        int smallestIndex = index;

        if (leftChildIndex < heap.Size() && heap.get(leftChildIndex).compareTo(heap.get(smallestIndex)) < 0) {
            smallestIndex = leftChildIndex;
        }

        if (rightChildIndex < heap.Size() && heap.get(rightChildIndex).compareTo(heap.get(smallestIndex)) < 0) {
            smallestIndex = rightChildIndex;
        }

        if (smallestIndex != index) {
            swap(index, smallestIndex);
            heapify(smallestIndex);
        }
    }

    /**
     * This is a supporting function that reorders the array by passing the new element up
     * in the MinHeap format, such that for each index, left and right indexes
     * are in 2i and 2i+1 forms. It goes on until current index value is bigger than parentIndex value.
     *
     * @param index the index from where the traversal starts
     */
    private void traverseUp(int index) {
        int parentIndex = parentOf(index);
        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentOf(index);
        }
    }

    /**
     * This is a supporting function that gets index of leftChild
     *
     * @param index current searched index for child index
     * @return int of leftChild
     */
    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    /**
     * This is a supporting function that gets index of rightChild
     *
     * @param index current searched index for child index
     * @return int of rightChild
     */
    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    /**
     * This is a supporting function that gets index of parent element
     *
     * @param index current searched index for parent index
     * @return int of parent element
     */
    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    /**
     * This is a supporting function that swaps 2 index values with each other
     *
     * @param index1 first element to be swapped
     * @param index2 second element to be swapped
     */
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
