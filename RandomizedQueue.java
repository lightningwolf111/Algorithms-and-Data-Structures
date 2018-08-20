import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
   Item[] container;
   int itemCount = 0;
   int endPosition = 0;
   
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
       container[endPosition] = item;
       endPosition++;
       arrayChange();
   }
   public Item dequeue() {
       int position = StdRandom.uniform(endPosition);
       Item toReturn = container[position];
       container[position] = null;
       itemCount--;
       arrayChange();
       return toReturn;
   }
   public Item sample() {
       return container[StdRandom.uniform(endPosition)];
   }
   public Iterator<Item> iterator() {
       return new RandomizedQueueIterator();
   }
   
   private class RandomizedQueueIterator implements Iterator<Item> {
       int currentIndex = 0;
       int itemsReturned = 0;
       public boolean hasNext() {
           return itemsReturned != itemCount;
       }
       public Item next() {
           while(container[currentIndex] == null) {
               currentIndex++;
           }
           Item toReturn = container[currentIndex];
           currentIndex++;
           return toReturn;
       }
   }
   public static void main(String[] args) {
       RandomizedQueue test = new RandomizedQueue();
       test.enqueue("1");
       System.out.println(test.sample());
       System.out.println(test.dequeue());
       System.out.println(test.isEmpty());
       test.enqueue("2");
       test.enqueue("3");
       System.out.println(test.sample());
       System.out.println(test.sample());
       System.out.println(test.sample());
       System.out.println(test.sample());
       System.out.println(test.sample());
       System.out.println(test.sample());
   }
       
   private void copyAndCompact(Item[] newArray) {
       int currentNewPos = 0;
       for(int currentOldPos = 0; currentOldPos < container.length; currentOldPos++) {
           if(container[currentOldPos] != null) {
               newArray[currentNewPos] = container[currentOldPos];
               currentNewPos++;
           }
           container = newArray;
           endPosition = currentNewPos; // should be same as itemcount
       }
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
       if(endPosition == container.length - 1 && itemCount > 0.75 * container.length) {
           grow();
       } else if(endPosition == container.length - 1) {
           copyAndCompact(container);
       } else if(itemCount < 0.25 * container.length) {
           shrink();
       }
   }
}