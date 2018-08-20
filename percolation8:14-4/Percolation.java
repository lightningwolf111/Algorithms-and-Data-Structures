import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] model;
    private int numberOfOpenSites = 0;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int nNum;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("constructor n <= 0 in Percolation");
        } else {
            model = new boolean[n + 1][n + 1];
            uf = new WeightedQuickUnionUF(n * n + 2);
            uf2 = new WeightedQuickUnionUF(n * n + 2);
            nNum = n;
            
            for (int i = 1; i <= nNum; i++) {
                uf2.union(ufCoordinate("top"), ufCoordinate(1, i));
                
                uf.union(ufCoordinate("bottom"), ufCoordinate(nNum, i));
                uf.union(ufCoordinate("top"), ufCoordinate(1, i));
            }
        }
    }
    public void open(int row, int col) {
        if (row < 1 || row > nNum || col < 1 || col > nNum) {
            throw new IllegalArgumentException("Out of bounds in open method in Percolation");
        } else if (!model[row][col]) {
            model[row][col] = true;
            if (row != 1 && isOpen(row - 1, col)) { // connecting left
                uf.union(ufCoordinate(row - 1, col), ufCoordinate(row, col));
                uf2.union(ufCoordinate(row - 1, col), ufCoordinate(row, col));
            }
            if (row != nNum && isOpen(row + 1, col)) { // connecting right
                uf.union(ufCoordinate(row + 1, col), ufCoordinate(row, col));
                uf2.union(ufCoordinate(row + 1, col), ufCoordinate(row, col));
            }
            if (col != 1 && isOpen(row, col - 1)) { // connecting down
                uf.union(ufCoordinate(row, col - 1), ufCoordinate(row, col));
                uf2.union(ufCoordinate(row, col - 1), ufCoordinate(row, col));
            }
            if (col != nNum && isOpen(row, col + 1)) { // connecting up
                uf.union(ufCoordinate(row, col + 1), ufCoordinate(row, col));
                uf2.union(ufCoordinate(row, col + 1), ufCoordinate(row, col));
            }
            numberOfOpenSites++;
        }
    }
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > nNum || col > nNum) {
            throw new IllegalArgumentException("Out of bounds in isOpen method in Percolation" + row + col);
        } else {
            return model[row][col];
        }
    }
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > nNum || col > nNum) {
            throw new IllegalArgumentException("Out of bounds in isFull method in Percolation" + row + col);
        } else {
            if (isOpen(row, col)) {
                return uf2.connected(ufCoordinate("top"), ufCoordinate(row, col));
            } else {
                return false;
            }
        }
    }
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }
    public boolean percolates() {
        if(nNum == 1) {
            return model[1][1];
        }
        
        if(uf.connected(ufCoordinate("top"), ufCoordinate("bottom"))) {
            return true;
        }
        return false;
    }
    private int ufCoordinate(int row, int col) {
        return ((row - 1) * nNum + col - 1);
    }
    private int ufCoordinate(String s) {
        if (s.equals("top")) {
            return nNum * nNum + 1;
        } else if (s.equals("bottom")) {
            return nNum * nNum;
        } else {
            throw new IllegalArgumentException("not valid string");
        }
    }
    /*public static void main(String[] args) {
        Percolation a = new Percolation(6);
        
        a.open(1,6);
        a.open(6,1);
        a.open(6,6);
        a.open(1,1);
        
        System.out.println(a.isOpen(1,6));
        System.out.println(a.isFull(1,6));
        System.out.println(a.isFull(6,1));
        System.out.println(a.isFull(6,6));
        System.out.println(a.isFull(1,1));
    }*/
}