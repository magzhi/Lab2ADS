import Classes.MyArrayList;
import Classes.MyLinkedList;

import java.util.ArrayList;

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
    }
}