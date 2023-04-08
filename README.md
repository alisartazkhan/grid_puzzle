# Puzzle Solver

The Puzzle Solver is a Java program that can search for words in a grid using a provided starting point, BFS, DFS, and recursive backtracking. 

## Usage

To use the Puzzle Solver, create a `Grid` object that represents the grid of letters that the program will search through. Then, create a `Puzzle` object and pass the `Grid` object to the constructor. 

To search for a word in the grid, call the `find()` method of the `Puzzle` object and pass the word and the row and column numbers of the starting point. The `find()` method will return a string representation of the locations of the letters of the word in the grid.

## Methods

The `Puzzle` class contains the following methods:

- `resetVisited(Deque<Loc> arr)`: This method goes through all the `Loc` objects that have been visited when searching for a particular word and resets the `visited` field of the `Loc` object to false so that it doesn't interfere when any other words are searched. 

- `find(String word, int r, int c)`: This method uses BFS and DFS to find the word in the parameter from the grid of letters. We are also provided with the starting point. This Method accepts 3 parameters and returns the string representation of the locations of the letters of the word.

- `adjacentList(Loc v, Deque<Loc> adjList)`: This method gets the `Loc` objects adjacent to the `Loc` object, `v`, and adds them to the `adjList`.

- `DFS(String word, int i, int r, int c)`: This method is a DFS helper function that calls the main recursive DFS function. Accepts 4 parameters.

- `DFS(Deque<Loc> path, String word, int i)`: This method is a DFS algorithm that tries to find the path of the word in the grid using a Depth first search and recursive backtracking. When the word is found, the method returns the string representation of the path of the letters of the word in the grid.

- `getLocations(Deque<Loc> path)`: This method returns the string representation of the path Stack by providing all coordinates next to each other.

## Example Usage
* Run ```Puzzle.java```
* Here's an example usage of the Puzzle Solver:

```java
// Create the grid of letters
char[][] letters = {{'a','b','c','d'},{'e','f','g','h'},{'i','j','k','l'},{'m','n','o','p'}};
Grid grid = new Grid(letters);

// Create the Puzzle object
Puzzle puzzle = new Puzzle(grid);

// Search for the word "hello" starting at row 1, column 1
String locations = puzzle.find("hello", 1, 1);

// Print the locations of the letters of the word "hello"
System.out.println(locations);
