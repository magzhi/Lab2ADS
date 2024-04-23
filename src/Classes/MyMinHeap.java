package Classes;

public class MyMinHeap<T extends Comparable<T>> implements Iterable<T> {
    private MyArrayList<T> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public void insert(T item) {
        heap.add(item);
        traverseUp(heap.Size() - 1);
    }

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

    public T getMin() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.Size() == 0;
    }

    public int size() {
        return heap.Size();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return heap.iterator();
    }

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

    private void traverseUp(int index) {
        int parentIndex = parentOf(index);
        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentOf(index);
        }
    }

    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
