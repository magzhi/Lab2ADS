package Classes;
import Interfaces.MyList;
import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

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

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        Node<T> node = getNode(index);
        if (node != null) {
            node.data = item;
        }
    }

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

    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        if (node != null) {
            return node.data;
        }
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    @Override
    public T getFirst() {
        if (head != null) {
            return head.data;
        }
        throw new IndexOutOfBoundsException("List is empty");
    }

    @Override
    public T getLast() {
        if (tail != null) {
            return tail.data;
        }
        throw new IndexOutOfBoundsException("List is empty");
    }

    @Override
    public void remove(int index) {
        Node<T> node = getNode(index);
        if (node != null) {
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
    }

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

    @Override
    public void sort() {
        head = mergeSort(head);
        tail = getTail(head);
    }

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

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

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

    @Override
    public void Clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int Size() {
        return size;
    }

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

    private Node<T> getTail(Node<T> node) {
        while (node != null && node.next != null) {
            node = node.next;
        }
        return node;
    }
}
