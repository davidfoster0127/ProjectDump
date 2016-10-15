/* Zeeshan Karim & David Foster
*  Maze Generator -- Main.java
*  This class tests the functionality of Maze.java
*  Display size depends on the size of the maze, max recommended size is 60x30
*  The start and end are selected randomly from cells on the outer edge of the maze
*  The start of the maze is the green square
*  The end of the maze is the red square
*  The solution is the path of blue squares
*/
public class Main {

	public static void main(String[] args) {

		// Maze dimensions
		int n = 30;
		int m = 30;
		
		//Maze maze = new Maze(n, m, true);		// Debug
		Maze maze = new Maze(n, m, false);
		
		// Time per draw in ms; slows down solution for better viewing
		maze.timedelay = 0;
		
		maze.display();
		maze.solve();
	}

}
