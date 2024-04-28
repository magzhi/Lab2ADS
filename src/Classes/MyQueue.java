package Classes;

public class MyQueue<T extends Comparable<T>> implements Iterable<T> {
    /**
     * MyQueue is implemented through MyLinkedList instead of MyArrayList
     * because LinkedLists provide better and faster functionality
     * for MyQueue methods, as we mainly need top/bottom elements
     * to be quickly accessible.
     */
    private MyLinkedList<T> list;

    /**
     * Default constructor just initialises MyLinkedList Object.
     */
    public MyQueue() {
        list = new MyLinkedList<>();
    }

    /**
     * This method sends item at the end of the List
     *
     * @param item is the Object added to the Queue
     */
    public void enqueue(T item) {
        list.addLast(item);
    }

    /**
     * This method gives back item at the start of the List and removes it
     *
     * @return item Object located in the first Node
     */
    public T dequeue() {
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }

    /**
     * This method gives back item at the start of the List
     *
     * @return item Object located in the last Node
     */
    public T peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Queue is empty");
        }
        return list.getFirst();
    }

    /**
     * This method checks if Queue is empty
     *
     * @return boolean whether Queue is empty
     */
    public boolean isEmpty() {
        return list.Size() == 0;
    }

    /**
     * This method returns Queue's size
     *
     * @return int size of the Queue List
     */
    public int size() {
        return list.Size();
    }

    /**
     * This method returns LinkedList's iterator method
     * as it will act the same way
     *
     * @return the iterator Object
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
}
