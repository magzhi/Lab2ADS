package Classes;
import java.util.Iterator;

public class MyStack<T extends Comparable<T>> implements Iterable<T> {
    /**
     * MyStack is implemented through MyLinkedList instead of MyArrayList
     * because LinkedLists provide better and faster functionality
     * for Stack methods, as we mainly need bottom elements
     * to be quickly accessible.
     */
    private MyLinkedList<T> list;

    /**
     * Default constructor just initialises MyLinkedList Object.
     */
    public MyStack() {
        list = new MyLinkedList<>();
    }

    /**
     * This method sends item at the end of the List
     *
     * @param item is the Object added to the Stack
     */
    public void push(T item) {
        list.addLast(item);
    }

    /**
     * This method gives back item at the end of the List and removes it
     *
     * @return item Object located in the last Node
     */
    public T pop() {
        T item = list.getLast();
        list.removeLast();
        return item;
    }

    /**
     * This method gives back item at the end of the List
     *
     * @return item Object located in the last Node
     */
    public T peek() {
        return list.getLast();
    }

    /**
     * This method checks if Stack is empty
     *
     * @return boolean whether Stack is empty
     */
    public boolean isEmpty() {
        return list.Size() == 0;
    }

    /**
     * This method returns Stack's size
     *
     * @return int size of the Stack List
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
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
