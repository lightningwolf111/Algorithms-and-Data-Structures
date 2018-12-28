import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    static RandomizedQueue randQueue = new RandomizedQueue();
    public static void main(String[] args) {
        int k = StdIn.readInt();
        int l = 0;
        while(StdIn.hasNextChar() && l < 5) {
            randQueue.enqueue(StdIn.readString());
            l++;
        }
        for(int i = 0; i < k; i++) {
            StdOut.println(randQueue.dequeue());
        }
    }
}
