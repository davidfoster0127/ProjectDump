// Zeeshan Karim & David Foster
// Maze Generator -- Maze.java
// This class implements an n by m maze generator
// It takes heavy inspiration from Maze.java by Robert Sedgewick and Kevin Wayne
// Their work is available at http://algs4.cs.princeton.edu/41graph/Maze.java.html
// This class also makes use of the StdDraw library also by the same authors for display
// The StdDraw.java library is included in this project's src directory

import java.util.Random;

public class Maze {
	private int n;
	private int m;
	private int startx;
	private int starty;
	private int solx = 0;
	private int soly = 0;
	private boolean debug;
	private boolean[][] north;
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] visited;
	private boolean done = false;
	
	public int timedelay = 0;
	
	public Maze(int n, int m, boolean debug){
		this.n = n;
		this.m = m;
		this.debug = debug;
		init();
		generate();
	}
	
	private void init(){
		
		StdDraw.setCanvasSize(n*30, m*30);
		StdDraw.setXscale(0, n+2);
		StdDraw.setYscale(0, m+2);
		
		// Create border cells and mark as visited
		visited = new boolean[n+2][m+2];
		for(int x = 0; x < n+2; x++){
			visited[x][0] = true;
			visited[x][m+1] = true;
		}
		for(int y = 0; y < m+2; y++){
			visited[0][y] = true;
			visited[n+1][y] = true;
		}
		
		// initialize all walls as present
		north = new boolean[n+2][m+2];
		east = new boolean[n+2][m+2];
		south = new boolean[n+2][m+2];
		west = new boolean[n+2][m+2];
		
		for(int x = 0; x < n+2; x++){
			for(int y = 0; y < m+2; y++){
				north[x][y] = true;
				east[x][y] = true;
				south[x][y] = true;
				west[x][y] = true;
			}
		}
	}
	
	private void generate(){
		
		// Select a random cell on the edge of the maze and set as the start point
		Random rn = new Random();
		int r = rn.nextInt(4);
		if(r == 0){
			startx = rn.nextInt(n)+1;
			starty = m;
		}
		if(r == 1){
			startx = n;
			starty = rn.nextInt(m)+1;
		}
		if(r == 2){
			startx = rn.nextInt(n)+1;
			starty = 1;
		}
		if(r == 3){
			startx = 1;
			starty = rn.nextInt(m)+1;
		}
		
		generate(startx, starty);
		
		// Choose a random solution on the edge of the maze that isn't the start
		do{
			r = rn.nextInt(4);
			if(r == 0){
				solx = rn.nextInt(n)+1;
				soly = m;
			}
			if(r == 1){
				solx = n;
				soly = rn.nextInt(m)+1;
			}
			if(r == 2){
				solx = rn.nextInt(n)+1;
				soly = 1;
			}
			if(r == 3){
				solx = 1;
				soly = rn.nextInt(m)+1;
			}
		}while(solx == startx && soly == starty);
		
		for(int x = 1; x <= n; x++){
			for(int y = 1; y <= m; y++){
				visited[x][y] = false;
			}
		}
		
		draw();
	}
	
	private void generate(int x, int y){
		if(debug) draw();
		visited[x][y] = true;
		Random rn = new Random();
		int r;
		// While there is an unvisited neighbor
		while(!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]){
			while(true){
				r = rn.nextInt(4);
				if(r == 0 && !visited[x][y+1]){
					// move north
					north[x][y] = false;
					south[x][y+1] = false;
					generate(x, y+1);
					break;
				}
				if(r == 1 && !visited[x+1][y]){
					// move east
					east[x][y] = false;
					west[x+1][y] = false;
					generate(x+1, y);
					break;
				}
				if(r == 2 && !visited[x][y-1]){
					// move south
					north[x][y-1] = false;
					south[x][y] = false;
					generate(x, y-1);
					break;
				}
				if(r == 1 && !visited[x-1][y]){
					// move west
					east[x-1][y] = false;
					west[x][y] = false;
					generate(x-1, y);
					break;
				}
			}
		}	
	}
	
	public void solve(){
		done = false;
		solve(startx, starty);
	}
	
	private void solve(int x, int y){
        if (x == 0 || y == 0 || x == n+1 || y == m+1) return;
        if (done || visited[x][y]) return;
		
		visited[x][y] = true;
		
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(x + 0.5, y + 0.5, 0.25, 0.25);
		StdDraw.show(timedelay);
		
		if(x == solx && y == soly) done = true;
		
		if(!north[x][y]) solve(x, y+1);
		if(!east[x][y]) solve(x+1, y);
		if(!south[x][y]) solve(x, y-1);
		if(!west[x][y]) solve(x-1, y);
		
		if(done) return;
		
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(x + 0.5, y + 0.5, 0.45, 0.45);
		StdDraw.show(timedelay);
	}
	
	public void draw(){
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(startx+0.5, starty+0.5, 0.5, 0.5);
		
		if(solx != 0 && soly != 0){
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledRectangle(solx+0.5, soly+0.5, 0.5, 0.5);
		}
		
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= m; y++) {
                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (west[x][y])  StdDraw.line(x, y, x, y + 1);
                if (east[x][y])  StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.show(timedelay);
	}
	
	public void display(){
		draw();
	}
	
	
}
