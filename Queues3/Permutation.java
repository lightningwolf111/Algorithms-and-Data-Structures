import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    private static RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
    public static void main(String[] args) {
        int k = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            randQueue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(randQueue.dequeue());
        }
    }
}
