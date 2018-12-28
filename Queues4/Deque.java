import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first = null;
    private Node last = null;
    
    private class Node {
        Node next;
        Node previous;
        Item item;
    }
    
    public Deque()  {
        
    }
    public boolean isEmpty() {
         return size == 0;
    }
    public int size() {
        return size;
    }
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            Node newFirst = new Node();
            if (first != null) {
                newFirst.next = first;
                first.previous = newFirst;
            } else {
                last = newFirst;
            }
            first = newFirst;
            first.item = item;
            size++;
        }
    }
    
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            Node newLast = new Node();
            if (first != null) {
                newLast.previous = last;
                last.next = newLast;
            } else {
                first = newLast;
            }
            last = newLast;
            last.item = item;
            size++;
        }
    }
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            Item item = first.item;
            if (first.next != null) {
                first = first.next;
                first.previous = null;
            } else {
                first = null;
                last = null;
            }
            size--;
            return item;
        }
    }
    
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            Item item = last.item;
            if (last.previous != null) {
                last = last.previous;
                last.next = null;
            } else {
                first = null;
                last = null;
            }
            size--;
            return item;
        }
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        Node current = first;
        public boolean hasNext() {
            if (current == null) {
                return false;
            } else {
                return true;
            }
        }
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        Deque<String> test = new Deque<String>();
        test.addFirst("string1");
        System.out.println(test.removeLast());
        
    }
}