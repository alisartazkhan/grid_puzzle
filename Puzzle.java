/*
 * Name: Ali Sartaz Khan
 * Description: This program creates a puzzle object that goes through a grid of Letters
 * and finds a particular word using algorithms like BFS and DFS.
 */

public class Puzzle {
	
	Grid grid;
	
	/*
	 * Constructor for the puzzle class. This just assigns the grid variable to the 
	 * grid parameter. 
	 * 
	 * grid: Grid object containing the grid
	 */
	public Puzzle(Grid grid)
	{
		this.grid = grid;
	}
	
	
	/*
	 * Method goes through all the Locations that have been visited when searching
	 * for a particular word and resets the visited field of the Loc object to false
	 * so that it doesn't interfere when any other words are searched.
	 * 
	 * arr: An Queue created using the Deque object containing all the Loc objects 
	 */
	public void resetVisited(Deque<Loc> arr)
	{
		while (arr.size()>0)
		{
			try {
			arr.getFirst().visited = false;}
			catch (EmptyDequeException e){
				return;
			}
		}
	}
	
	
	/*
	 * Method uses BFS and DFS to find the word in the parameter from the grid of letters.
	 * We are also provided with the starting point. This Method accepts 3 parameters and returns
	 * the string representation of the locations of the letters of the word.
	 * 
	 * word: String word that we are searching for in the list
	 * r: int row number
	 * c: int column number
	 */
	public String find(String word, int r, int c)
	{
		Deque<Loc> arr = new Deque<>(); // keeps track of all the Loc visited so we can later reset
		Deque<Loc> Q = new Deque<>();
		Loc L = grid.getLoc(r, c);
		Q.addToBack(L);
		int i = 0; // index counter for word
		
		
		while (!Q.isEmpty())
		{
			Loc v = null;
			try {
				v = Q.getFirst();
			} catch (EmptyDequeException e) {
				
				e.printStackTrace();
			}
			arr.addToBack(v);
			v.visited = true;
			String  letter = ""+ word.charAt(i);
			i ++;
			
			if (letter.equals(v.getVal())) {
				String ret = null;
				try {
					ret = DFS(word, i, v.row, v.col);
				} catch (EmptyDequeException e) {
					e.printStackTrace();
				}
				if (ret != "") {
					resetVisited(arr);
					return ret;
				}
							 
			}
			
			i--;
			Deque<Loc> adjList = new Deque<>();
			adjacentList(v, adjList);
			
			while (adjList.size()>0)
			{
				Loc w = null;
				
				try {
					w = adjList.getFirst();
				} catch (EmptyDequeException e) {
					e.printStackTrace();
				}
				
				if (w != null && w.visited == false && !Q.contains(w)) 
					Q.addToBack(w);	
			}
			
		}
		
		return "Word Not Found";}
		
		
	/*
	 * Method gets the Loc objects adjacent to the Lov object, v, and adds them to the adjList.
	 * 
	 * v: Loc object whose adjacent objects we are finding.
	 * adjList: Deque containing the List of adjacent objects to v
	 */
	public void adjacentList(Loc v, Deque<Loc> adjList)
	{
		int r = v.row;
		int c = v.col;
		adjList.addToBack(grid.getLoc(r-1, c)); //UP
		adjList.addToBack(grid.getLoc(r, c+1)); //Right
		adjList.addToBack(grid.getLoc(r+1, c)); //down
		adjList.addToBack(grid.getLoc(r, c-1)); //left
	}
	
	
	/*
	 * Method is a DFS helper function that calls the main recursive DFS function .
	 * Accepts 4 parameters. 
	 * 
	 * word: String word we are looking for
	 * i: int index number of word to keep track of the letters we are looking for
	 * r: int row number of starting point
	 * c: int column number of starting point
	 */
	public String DFS(String word, int i, int r, int c) throws EmptyDequeException
	{
		Loc start = grid.getLoc(r, c);
		Deque<Loc> path = new Deque<>();
		path.addToBack(start);
		return DFS(path, word, i);		
		
	}
	
	
	/*
	 * Method is a DFS algorithm that tries to find the path of the word in the grid using 
	 * a Depth first search and recursive backtracking. When the word is found, the method
	 * returns the string representation of the path of the letters of the word in the grid.
	 * 
	 * path: Deque object representing a stack containing path of the letters
	 * word: String word that we are looking for
	 * i: int index number of letters in the word
	 */
	public String DFS(Deque<Loc> path, String word, int i) throws EmptyDequeException
	{
		
		Loc u = (Loc) path.peekLast();
		u.visited = true;
		
		if (path.size() == word.length()) 
			return getLocations(path);
		
		Deque<Loc> adjList = new Deque<>();
		adjacentList(u, adjList);
		
		
		while (adjList.size()>0)
		{
			Loc w = adjList.getFirst();
			if (w != null)
			{	
				String letter = ""+ word.charAt(i);
				i ++;
				
				if (letter.equals(w.getVal())) {
					path.addToBack(w);
					String ret = DFS(path, word, i);
					
					if (ret != "")
						return ret;
					
					w.visited = false; // back-tracking
					path.getLast(); // back-tracking
				}
				
				i--;	
					
			}
		}
		return ""; // returns "" if word isnt found
		}

	
	/*
	 * Method returns the String representation of the path Stack by providing all coordinates next
	 * to each other. 
	 * 
	 * path: Stack containing the path of letters of the word we are looking for
	 */
	public String getLocations(Deque<Loc> path) throws EmptyDequeException
	{
		String ret = "";
		for(int i =0; i< path.size(); i++)
		{
			Loc val = path.getFirst();
			ret += val.toString();
			path.addToBack(val);
		}
		
		return ret;
	}
}
