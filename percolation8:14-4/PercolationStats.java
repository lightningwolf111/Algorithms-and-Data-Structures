import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int trialsNum;
    private final double[] data;
    private boolean doneTrials = false;
    private double stddev = 0;
    private double mean = 0;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("PercolationStats constructor arguments out of bounds");
        }
        trialsNum = trials;
        
        
        data = new double[trials];
        
        for (int i = 0; i < trials; i++) {
            
            Percolation grid = new Percolation(n);
            
            while (!grid.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                while (grid.isOpen(x, y)) {
                   x = StdRandom.uniform(1, n + 1);
                   y = StdRandom.uniform(1, n + 1);
                }
                
                grid.open(x, y); // only choose from closed locations
            }
            
            data[i] = grid.numberOfOpenSites() /((double) n * n);
            
        }
        doneTrials = true;
    }
    public double mean() {
        mean = StdStats.mean(data);
        return mean;
    }
    
    public double stddev() {
        stddev = StdStats.stddev(data);
        return stddev;
    }
    
    public double confidenceLo() {
        if (stddev != 0) {
            if (mean != 0) {
                return mean - CONFIDENCE_95 * stddev / Math.sqrt(trialsNum);
            } else {
                return mean() - CONFIDENCE_95 * stddev / Math.sqrt(trialsNum);
            }
        } else {
            if (mean != 0) {
                return mean - CONFIDENCE_95 * stddev() / Math.sqrt(trialsNum);
            } else {
                return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trialsNum);
            }
        }
    }
    public double confidenceHi() {
        if (stddev != 0) {
            if (mean != 0) {
                return mean + CONFIDENCE_95 * stddev / Math.sqrt(trialsNum);
            } else {
                return mean() + CONFIDENCE_95 * stddev / Math.sqrt(trialsNum);
            }
        } else {
            if (mean != 0) {
                return mean + CONFIDENCE_95 * stddev() / Math.sqrt(trialsNum);
            } else {
                return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trialsNum);
            }
        }
    }
    
    
    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(StdIn.readInt(), StdIn.readInt());
        if (test.doneTrials) {
            System.out.println("mean  = " + test.mean());
            System.out.println("stddev = " + test.stddev());
            System.out.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
        }
    }
}