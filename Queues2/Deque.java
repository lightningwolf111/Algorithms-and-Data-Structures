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
            if(first != null) {
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
        if(size == 0) {
            throw new NoSuchElementException();
        } else {
            Item item = first.item;
            first = first.next;
            first.previous = null;
            size--;
            return item;
        }
    }
    
    public Item removeLast() {
        if(size == 0) {
            throw new NoSuchElementException();
        } else {
            Item item = last.item;
            last = last.previous;
            last.next = null;
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
            if(current == null) {
                return false;
            } else {
                return true;
            }
        }
        public Item next() {
            if(current == null) {
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
        test.addFirst("string2");
        test.addLast("string3");
        test.addFirst("string4");
        for(String s : test) {
            System.out.println(s);
        }
        System.out.println(test.removeLast());
        System.out.println(test.removeFirst());
    }
}