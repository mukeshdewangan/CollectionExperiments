package ThreadInterruption;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Custom Linked List class using Generics
class MyList<T> implements Iterable<T> {
    Node<T> head, tail;

    // add new Element at tail of the linked List in O(1)
    public void add(T data)
    {
        Node<T> node = new Node<>(data, null);
        if (head == null)
            tail = head = node;
        else {
            tail.setNext(node);
            tail = node;
        }
    }

    // return Head
    public Node<T> getHead()
    {
        return head;
    }

    // return Tail
    public Node<T> getTail()
    {
        return tail;
    }

    // return Iterator instance
    public Iterator<T> iterator()
    {
        return new MyListIterator<T>(this);
    }
}

class MyListIterator<T> implements Iterator<T> {
    Node<T> current;

    // initialize pointer to head of the MyList for iteration
    public MyListIterator(MyList<T> MyList)
    {
        current = MyList.getHead();
    }

    // returns false if next element does not exist
    public boolean hasNext()
    {
        return current != null;
    }

    // return current data and update pointer
    public T next()
    {
        if(!hasNext()) throw  new NoSuchElementException("No more elements are present");
        T data = current.getData();
        current = current.getNext();
        return data;
    }

    // implement if needed
    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}

// Constituent Node of Linked MyList
class Node<T> {
    T data;
    Node<T> next;
    public Node(T data, Node<T> next)
    {
        this.data = data;
        this.next = next;
    }

    // Setter getter methods for Data and Next Pointer
    public void setData(T data)
    {
        this.data = data;
    }

    public void setNext(Node<T> next)
    {
        this.next = next;
    }

    public T getData()
    {
        return data;
    }

    public Node<T> getNext()
    {
        return next;
    }
}

public class IteratorExample {
    public static void main(String[] args)
    {
        // Create Linked List
        MyList<String> myList = new MyList<>();

        // Add Elements
        myList.add("abc");
        myList.add("mno");
        myList.add("pqr");
        myList.add("xyz");

        Iterator<String> iter = myList.iterator();
        for(int i=0; i< 6; i++){
            System.out.println(iter.next());
        }
        // Iterate through the list using For Each Loop
        for (String string : myList)
            System.out.println(string);
    }

}
