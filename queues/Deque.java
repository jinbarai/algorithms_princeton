import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // top of deque
    private int n; // size of deque

    // helper class node
    private class Node
    {
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    public void validateEmpty(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (n==0) {
            first = new Node();
            first.item = item;
            first.next = null;
            n++;
            return;
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (n==0) {
            first = new Node();
            first.item = item;
            first.next = null;
            n++;
            return;
        }

        Node temp = first;

        while(temp.next!=null) {
            temp = temp.next;
        }
        Node t = new Node();
        t.item  = item;
        t.next = null;
        temp.next = t;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;                   // return the saved item
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Not possible");
        Node temp = first;
        while (temp.next.next!=null) {
            //System.out.println(temp.item);
            temp = temp.next;
        }
        Item item = temp.next.item;
        temp.next = null;
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // Referenced from Linked List implementation
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(1);
        dq.addFirst(2);
        dq.addFirst(3);
        dq.addFirst(4);
        dq.addLast(5);


        //System.out.println(dq.size());
        //System.out.println(dq.isEmpty());

        for (Integer item: dq) {
            System.out.println(item);
        }

        System.out.println(dq.size());
        System.out.println(dq.removeLast());
        System.out.println(dq.size());
    }

}
