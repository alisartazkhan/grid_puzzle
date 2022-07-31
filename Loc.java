/*
 * Name: Ali Sartaz Khan
 * Description: Loc object that contains a value and coordinates as well a field
 * to check if it was visited.
 */

public class Loc {
    public final int row;
    public final int col;
    private String val;
    public boolean visited;

    /*
     * Constructor for the Loc class. 
     * 
     * x: int row 
     * y: int column
     */
    public Loc(int x, int y, String val) {
	this.row = x;
	this.col = y;
	this.val = val;
	this.visited = false;
    }

    /*
     * Method returns Loc in the form (row, col)
     */
    public String toString() {
	return "(" + row + ", " + col + ")";
    }

    /*
     * Method returns the String value at this location
     */
    public String getVal() {
	return val;
    }
}
