import Classes.*;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList<>();

        myArrayList.add(5);
        myArrayList.add(4);
        myArrayList.add(3);
        myArrayList.add(2);
        myArrayList.add(1);

        System.out.println("List pre sort");
        for (int i = 0; i < myArrayList.Size(); i++) {
            System.out.println(myArrayList.get(i));
        }
        myArrayList.sort();
        System.out.println("List post sort");
        for (int i = 0; i < myArrayList.Size(); i++) {
            System.out.println(myArrayList.get(i));
        }
        System.out.println("Value of index 3: " + myArrayList.get(3));
        System.out.println("Size of an list: " + myArrayList.Size());
        System.out.println("Vaule of index 0 before remove: " + myArrayList.get(0));
        myArrayList.remove(0);
        System.out.println("Vaule of index 0 after remove: " + myArrayList.get(0));
        System.out.println("Size of an list: " + myArrayList.Size());
        System.out.println("Vaule of index 2 before remove: " + myArrayList.get(2));
        myArrayList.remove(2);
        System.out.println("Vaule of index 2 after remove: " + myArrayList.get(2));
        System.out.println("Size of an list: " + myArrayList.Size());


        MyLinkedList myLinkedList = new MyLinkedList<>();

        myLinkedList.add(5);
        myLinkedList.add(4);
        myLinkedList.add(3);
        myLinkedList.add(2);
        myLinkedList.add(1);
        new Stack<>();

        System.out.println("List pre sort");
        for (int i = 0; i < myLinkedList.Size(); i++) {
            System.out.println(myLinkedList.get(i));
        }
        myLinkedList.sort();
        System.out.println("List post sort");
        for (int i = 0; i < myLinkedList.Size(); i++) {
            System.out.println(myLinkedList.get(i));
        }
        System.out.println("Value of index 3: " + myLinkedList.get(3));
        System.out.println("Size of an list: " + myLinkedList.Size());
        System.out.println("Vaule of index 0 before remove: " + myLinkedList.get(0));
        myLinkedList.remove(0);
        System.out.println("Vaule of index 0 after remove: " + myLinkedList.get(0));
        System.out.println("Size of an list: " + myLinkedList.Size());
        System.out.println("Vaule of index 2 before remove: " + myLinkedList.get(2));
        myLinkedList.remove(2);
        System.out.println("Vaule of index 2 after remove: " + myLinkedList.get(2));
        System.out.println("Size of an list: " + myLinkedList.Size());

        MyStack myStack = new MyStack<>();
        System.out.println("Check stack emptiness: " + myStack.isEmpty());
        myStack.push(5);
        myStack.push(4);
        myStack.push(3);
        myStack.push(2);
        myStack.push(1);

        System.out.println("Peeked element: " + myStack.peek());
        System.out.println("Popped element: " + myStack.pop());
        System.out.println("Peeked element: " + myStack.peek());
        System.out.println("Popped element: " + myStack.pop());
        System.out.println("Check stack emptiness: " + myStack.isEmpty());
        System.out.println("Check stack size: " + myStack.size());

        MyQueue myQueue = new MyQueue();
        System.out.println("Check queue emptiness: " + myQueue.isEmpty());
        myQueue.enqueue(5);
        myQueue.enqueue(4);
        myQueue.enqueue(3);
        myQueue.enqueue(2);
        myQueue.enqueue(1);

        System.out.println("Peeked element: " + myQueue.peek());
        System.out.println("Dequeued element: " + myQueue.dequeue());
        System.out.println("Peeked element: " + myQueue.peek());
        System.out.println("Dequeued element: " + myQueue.dequeue());
        System.out.println("Check queue emptiness: " + myQueue.isEmpty());
        System.out.println("Check queue size: " + myQueue.size());

        MyMinHeap myMinHeap = new MyMinHeap<>();
        System.out.println("Is the heap empty? " + myMinHeap.isEmpty());

        // Insert elements
        myMinHeap.insert(5);
        myMinHeap.insert(10);
        myMinHeap.insert(3);
        myMinHeap.insert(7);
        myMinHeap.insert(1);

        System.out.println("Minimum element: " + myMinHeap.getMin());
        System.out.println("Extracted element is: " + myMinHeap.extractMin());
        System.out.println("Is the heap empty? " + myMinHeap.isEmpty());
        System.out.println("Size of the heap: " + myMinHeap.size());
        System.out.println("Minimum element: " + myMinHeap.getMin());
        System.out.println("Extracted element is: " + myMinHeap.extractMin());
    }
}