
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF grid;
    private boolean[] matrix;
    private final int size;
    private int count;
    private final int bottom;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("Wrong input, should be greater than 0");
        }
        // Generating a grid of type WeightedQuickUnion with size n
        grid = new WeightedQuickUnionUF((n*n)+2);
        // Initialized to value false since default value
        matrix = new boolean[(n*n)+2];
        // size of grid
        this.size = n;
        this.bottom = size*size+1;
    }

    private int getIndexofElement(int i, int j) {
        // we can get the position of any element in (row,col) form in matrix
        return ((i-1)* this.size + j);
    }

    private void connect (int i, int j) {
        grid.union(i, j);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > this.size || col > this.size || col < 0 || row < 0) {
        	throw new IndexOutOfBoundsException("Wrong entry");
        } 

        int index = getIndexofElement(row, col);
        if (!matrix[index]) {
            matrix[index] = true; // open site (row i, column j) if it is not open already
            this.count++;
        }
        if (row == 1) {
            connect(index, 0);
        }
        if (row == this.size) {
            grid.union(index, bottom);
        }
        if (row > 1 && isOpen(row - 1, col)) { // if the left site is open connect it to left site
            connect(index, getIndexofElement(row - 1, col));
        }
        if (row < this.size && isOpen(row + 1, col)) { // if the bottom site is open connect it to bottom site
            connect(index, getIndexofElement(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) { // if the left site is open connect it to left site
            connect(index, getIndexofElement(row,col- 1));
        }
        if (col < this.size && isOpen(row, col + 1)) { // if the right site is open connect it to right site
            connect(index, getIndexofElement(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > this.size || col > this.size || col < 0 || row < 0) {
        	throw new IndexOutOfBoundsException("Wrong entry");
        } 

        return matrix[getIndexofElement(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
         if (row > this.size || col > this.size || col < 0 || row < 0) {
        	throw new IndexOutOfBoundsException("Wrong entry");
        } 
        // check if bottom and site have the same root
        return grid.find(getIndexofElement(row, col)) == grid.find(0);
    }
    // returns the number of open sites
    public int numberOfOpenSites() {
        // loop through the cells of the grid and return an integer to give
        // no of open sites
        return this.count;

    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(bottom, 0);
    }
}