/* *****************************************************************************
 *  Name: Jin Barai
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first; // top of deque
    private int n; // size of deque

    // construct an empty randomized queue
    private class Node
    {
        Item item;
        Node next;
    }

    public RandomizedQueue() {
        first = null;
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    public void validateEmpty(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
    }

    // add the item
    public void enqueue(Item item) {
        validateEmpty(item);
        if (n == 0) {
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

    // remove and return a random item
    public Item dequeue() {
        int generateLucky = StdRandom.uniform(n);
        Node temp = first;
        while (generateLucky!=1) {
            temp = temp.next;


            generateLucky--;
        }
        return temp.item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        //StdRandom.shuffle(n);
        int generateLucky = StdRandom.uniform(n);
        Node temp = first;
        while (generateLucky!=1) {
            temp = temp.next;
            generateLucky--;
        }
        return temp.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // Referenced from Linked List implementation
    private class RandomizedQueueIterator implements Iterator<Item> {
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
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);

        System.out.println(rq.isEmpty());

        for (Integer item: rq) {
            System.out.println(item);
        }

        //System.out.println(rq.dequeue());
        System.out.println(rq.size());
        System.out.println(rq.sample());
        System.out.println(rq.sample());
        System.out.println(rq.sample());

    }

}