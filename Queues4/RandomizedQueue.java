import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] container;
   private int itemCount = 0;
   private int endPosition = 0;
   
   public RandomizedQueue() {
       container = (Item[]) new Object[2];
   }
   public boolean isEmpty() {
       return itemCount == 0;
   }
   public int size() {
       return itemCount;
   }
   public void enqueue(Item item) {
       if (item == null) {
           throw new IllegalArgumentException();
       } else {
           container[endPosition] = item;
           endPosition++;
           itemCount++;
           arrayChange();
       }
   }
   public Item dequeue() {
       if (itemCount == 0) {
           throw new NoSuchElementException();
       } else {
           int position = StdRandom.uniform(endPosition);
           while (container[position] == null) {
               position = StdRandom.uniform(endPosition);
           }
           Item toReturn = container[position];
           container[position] = null;
           itemCount--;
           arrayChange();
           return toReturn;
       }
   }
   public Item sample() {
       if (itemCount == 0) {
           throw new NoSuchElementException();
       } else {
           int position = StdRandom.uniform(endPosition);
           while (container[position] == null) {
               position = StdRandom.uniform(endPosition);
           }
           return container[position];
       }
   }
   public Iterator<Item> iterator() {
       return new RandomizedQueueIterator();
   }
   
   private class RandomizedQueueIterator implements Iterator<Item> { // Has to return random items!!
       int itemCountAtCreation = itemCount;
       int itemsReturned = 0;
       Item[] iteratorContainer = (Item[]) new Object[container.length];
       public RandomizedQueueIterator() {
           System.arraycopy(container, 0, iteratorContainer, 0, container.length);
       }
       
       public boolean hasNext() {
           return itemsReturned != itemCountAtCreation;
       }
       public Item next() {
           if (!hasNext()) {
               throw new NoSuchElementException();
           } else {
               int position = StdRandom.uniform(endPosition);
               while (iteratorContainer[position] == null) {
                   position = StdRandom.uniform(endPosition);
               }
               Item toReturn = iteratorContainer[position];
               iteratorContainer[position] = null;
               itemsReturned++;
               return toReturn;
           }
       }
       public void remove() {
           throw new UnsupportedOperationException();
       }
   }
       
   private void copyAndCompact(Item[] newArray) {
       int currentNewPos = 0;
       for (int currentOldPos = 0; currentOldPos < container.length; currentOldPos++) {
           if (container[currentOldPos] != null) {
               newArray[currentNewPos] = container[currentOldPos];
               currentNewPos++;
           }
       }
       container = newArray;
       endPosition = currentNewPos; // should be same as itemcount
   }
   
   private void shrink() {
       Item[] newArray = (Item[]) new Object[container.length/2];
       copyAndCompact(newArray);
   }
   
   private void grow() {
       Item[] newArray = (Item[]) new Object[container.length * 2];
       copyAndCompact(newArray);
   }
   private void arrayChange() {
       if (endPosition == container.length && itemCount > 0.75 * container.length) {
           grow();
       } else if (endPosition == container.length) {
           copyAndCompact(container);
       } else if (itemCount < 0.25 * container.length) {
           shrink();
       }
   }
   
   /* public static void main(String[] args) {
       RandomizedQueue test = new RandomizedQueue();
       int max = 100;
       int[] a = new int[max];
       for (int i = 0; i < max; i++) {
           test.enqueue(i);
       }
       for (int i = 0; i < max; i++) {
           a[i] = (Integer) test.dequeue();
           test.enqueue(max + a[i]);
       }
       System.out.println("Queue size " + test.size());
       for(int i = 0; i < max; i++) {
           for (int j = 0; j < max; j++) {
               if(a[i] == a[j] && j != i) {
                   System.out.println(a[i] + " " + a[j]);
               }
           }
       }
   }
   */
   
}