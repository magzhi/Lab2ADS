package Classes;
import Interfaces.MyList;
import java.util.Iterator;

/**
 * Public MyLinkedList<T> class. It implements MyList<T> interface functionality and extends Comparable<T>
 *     MyList interface is the minimum set of methods that were given for this assignment
 *     Comparable is used to implement a sorting algorithm into this class.
 *
 * @param <T> an Object type of the created ArrayList.
 */
public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    /**
     * 3 private data types included in each MyLinkedList
     *
     * @head is the first node that contains the first element of our List
     *
     * @tail is the last node that contains the last element of our List
     *
     * @size is the current size of the list array
     */
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * This is private class within our LinkedList
     * This is a double linked List, so each node contains 3 data points:
     * @data which is the Object contained within Node
     * @prev which is the previous Node in the chain
     * @next which is the next Node in the chain
     *
     * @param <T> node containing T Object in it
     */
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * Default generic constructor for MyLinkedList
     * creates an empty array with both head and tail nulls
     * and sets the default size to be empty
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * This method adds said element to the array to the end of the List
     *
     * @param item is the Object getting added to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void add(T item) {
        addLast(item);
    }

    /**
     * This method first ensures if the index is actually indexed within the array and exists,
     * then calls getNode() method at the index location
     * if the returned node is not null, it proceeds setting Object
     * at that index to be 'item'
     *
     * @param index is the index of where we want to set the Object
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void set(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> node = getNode(index);
        if (node != null) {
            node.data = item;
        }
    }

    /**
     * This method first ensures if the index is actually indexed within the array and exists,
     * then it either throws an out-of-bounds error
     * or it then checks whether it is first or last index to use addFirst or addLast
     * if not it creates a new node, travels to the indexed position
     * sets 'current' as the node which will be on the right
     * and sets 'prevNode' as the one on the left
     * then rearranges their prev/next datas to fit in new Node
     * and increases size by 1
     *
     * @param index is the index of where we want to set the Object
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node<T> newNode = new Node<>(item);
            Node<T> current = getNode(index);
            Node<T> prevNode = current.prev;

            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = current;
            current.prev = newNode;

            size++;
        }
    }

    /**
     * This method implements a quick way to add first Node in the List,
     * if the List is empty, it just adds new Node as both head and tail,
     * otherwise it makes new Node's next to be last head,
     * last head's prev to be new Node
     * and then sets new Node as new head
     *
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * This method implements a quick way to add last Node in the List,
     * if the List is empty, it just adds new Node as both head and tail,
     * otherwise it makes new Node's prev to be last tail,
     * last tail's next to be new Node
     * and then sets new Node as new tail
     *
     * @param item is the Object getting set to our array
     *             it is a T as it can be anything and one preset Object type
     */
    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * This method first uses getNode to search for indexed Object,
     * then it either returns the Object indexed at the location,
     * or it throws an out-of-bounds error in case index is not in the List
     *
     * @param index the location queried to be found
     * @return returns Object located at that index of the array
     */
    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        if (node != null) {
            return node.data;
        }
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    /**
     * This method returns head Object or throws out-of-bounds in case it is empty
     * @return head Object
     */
    @Override
    public T getFirst() {
        if (head != null) {
            return head.data;
        }
        throw new IndexOutOfBoundsException("List is empty");
    }

    /**
     * This method returns tail Object or throws out-of-bounds in case it is empty
     * @return tail Object
     */
    @Override
    public T getLast() {
        if (tail != null) {
            return tail.data;
        }
        throw new IndexOutOfBoundsException("List is empty");
    }

    /**
     * This method first ensures if the index is actually indexed within the array and exists,
     * then it uses getNode method to find the indexed position
     * then it rearranges prev/next values of the prev/next Nodes
     *
     * @param index the location queried to be removed
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> node = getNode(index);
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        size--;
    }

    /**
     * This method quickly removes head Node in case it exists,
     * and sets new head as head.next, if that is not null,
     * then it sets its prev value to be null,
     * otherwise it sets tail to be null as the List is empty,
     * then it reduces size by 1
     */
    @Override
    public void removeFirst() {
        if (head != null) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
        }
    }

    /**
     * This method quickly removes tail Node in case it exists,
     * and sets new tail as tail.next, if that is not null,
     * then it sets its next value to be null,
     * otherwise it sets head to be null as the List is empty,
     * then it reduces size by 1
     */
    @Override
    public void removeLast() {
        if (tail != null) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
        }
    }

    /**
     * This method calls sorting method for the array
     * I chose mergeSort because it has a constant complexity
     * at both worst and best cases, which is n*log(n)
     * this does the mergesort first and then finds tail
     * through .next connections starting from head Node
     */
    @Override
    public void sort() {
        head = mergeSort(head);
        tail = getTail(head);
    }

    /**
     * This is a recursive method to sort an array.
     * The idea is to split up array into 2 chunks and do that recursively,
     * then send up the resulting merged and sorted parts back up
     * which is a split and conquer type algorithm.
     *
     * @param head the first element of the given Node chain
     * @return gives back the head element which is taken from merge() method
     */
    private Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;

        // Apply mergeSort on left half
        Node<T> left = mergeSort(head);

        // Apply mergeSort on right half
        Node<T> right = mergeSort(nextOfMiddle);

        // Merge the sorted halves
        return merge(left, right);
    }

    /**
     * This is the merge part of the mergeSort algorithm
     * first 2 ifs check whether we are split to the base cases where just 1 element is left
     * Then it compares element from left and right chains
     * and then reattaches them in ordered form, while keeping the smallest
     * head element Node at the 'result' field
     *
     * @param left head element of the leftArray
     * @param right head element of the rightArray
     * @return gives the head element of the resulting sorted chain
     */
    private Node<T> merge(Node<T> left, Node<T> right) {
        Node<T> result = null;

        // Base cases
        if (left == null)
            return right;
        if (right == null)
            return left;

        // Pick either left or right and recur
        if (left.data.compareTo(right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right);
            result.next.prev = result;
        } else {
            result = right;
            result.next = merge(left, right.next);
            result.next.prev = result;
        }

        return result;
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
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.next;
            index++;
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
        int index = size - 1;
        Node<T> current = tail;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.prev;
            index--;
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
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * This method resets our array to empty state
     */
    @Override
    public void Clear() {
        head = null;
        tail = null;
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
     * The Iterator Object contains of a single data point which is the current Node counted thus far
     * and 2 methods:
     * hasNext() which checks if the next index even exists at all
     * next() which returns back the next Object from the array or gives an out-of-bounds error
     *
     * @return this gives back Iterator<T> Object
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("No more elements");
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * This is a supporting method which is called whenever we
     * need to find the Node object by its index
     *
     * @param index given search index
     * @return Node that is located at that index
     *         or null in case index is out-of-bounds.
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    /**
     * This is a supporting method which is used in the mergeSort method
     * which provides the middle Node out of the current chain.
     * The algorithm works in such way:
     *      It sets the initial node.
     *      then it assigns it to 2 temp Nodes.
     *      slow one moves forward by 1 each loop.
     *      fast one moves forward by 2 each loop.
     *      Thus, when fast reaches the point,
     *      where both next and next.next are null,
     *      that means slow has reached the middle
     *      point of the current chain
     *
     *
     * @param head given search index
     * @return Node that is located in the middle
     */
    private Node<T> getMiddle(Node<T> head) {
        if (head == null)
            return head;

        Node<T> slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * This is a supporting method which is called after mergeSort()
     * to find the last node in the chain to assign tail value to it.
     * It finds by going through .next Nodes until it reaches the end.
     *
     * @param node given head Node
     * @return Node that is located at the end of the chian
     */
    private Node<T> getTail(Node<T> node) {
        while (node != null && node.next != null) {
            node = node.next;
        }
        return node;
    }
}
