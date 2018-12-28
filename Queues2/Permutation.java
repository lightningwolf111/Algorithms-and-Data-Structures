import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    private static RandomizedQueue randQueue = new RandomizedQueue();
    public static void main(String[] args) {
        int k = StdIn.readInt();
        while(StdIn.hasNextChar()) {
            randQueue.enqueue(StdIn.readString());
        }
        for(int i = 0; i < k; i++) {
            StdOut.println(randQueue.dequeue());
        }
    }
}
