//Ramzi Daswani (rdaswa2)
//"I worked on the homework assignment alone, using  this semester's course materials and online resources."
import java.io.*;
import java.util.Scanner;
import java.util.ArrayDeque;


/*
 * Recursive class to represent a position in a path
 */


public class PathFinder {
	
	public static void main(String[] args) throws IOException {
		if(args.length<1){
			System.err.println("***Usage: java PathFinder maze_file");
			System.exit(-1);
		}

		char [][] maze;
		maze = readMaze(args[0]);
		printMaze(maze);
		Position [] path = stackSearch(maze);
		System.out.println("stackSearch Solution:");
		printPath(path);
		printMaze(maze);
		
		char [][] maze2 = readMaze(args[0]);
		path = queueSearch(maze2);
		System.out.println("queueSearch Solution:");
		printPath(path);
		printMaze(maze2);
	}

	public static Position [] stackSearch(char [] [] maze){
		ArrayDeque<Position> stacklist = new ArrayDeque<Position> ();
		stacklist.push(new Position(0,0,'0'));
		int y=0;
		while(!stacklist.isEmpty()) {
			Position start = stacklist.pop();
			if (start.i==maze.length-1 && start.j==maze.length-1) {
				maze[start.i][start.j]='X';
				for(Position x = start.parent; x!=null; x=x.parent) {
					y++;
				}
				Position [] path = new Position[y];
				for(Position x = start; x.parent!=null; x=x.parent) {
					path[y-1]=x;
					y--;
				}
				for (int z=0; z<maze.length; z++) {
					for (int j=0; j<maze.length; j++) {
						if (maze[z][j]!='1')
							maze[z][j]='0';
					}
				}
				for(Position x = start; x.parent!=null; x=x.parent)
					maze[x.i][x.j]='X';
					maze[0][0]='X';
					return path;
			}
			else { 
				maze[start.i][start.j]='X';
				validstack(maze,start,stacklist);
				
				
			}
		}
		return null;
	}
	
	public static void validstack(char [] [] maze, Position current, ArrayDeque<Position> ins) {
		int x = current.i; int y = current.j;
		if (y-1>-1 && maze[x][y-1]=='0') 
			ins.push(new Position(x,y-1,maze[x][y-1],current));

		if (x-1>-1 && maze[x-1][y]=='0')
			ins.push(new Position(x-1,y,maze[x-1][y],current));

		if (x+1<maze.length && maze[x+1][y]=='0')
			ins.push(new Position(x+1,y,maze[x+1][y],current));

		if (y+1<maze.length && maze[x][y+1]=='0')
			ins.push(new Position(x,y+1,maze[x][y+1],current));
	}

	public static Position [] queueSearch(char [] [] maze){
		ArrayDeque<Position> queue = new ArrayDeque<Position> ();
		queue.addLast(new Position(0,0,'0'));
		int i=0;
		while(!queue.isEmpty()) {
			Position initial = queue.removeFirst();
			if (initial.i==maze.length-1 && initial.j==maze.length-1) {
				maze[initial.i][initial.j]='X';
				for(Position x = initial.parent; x!=null; x=x.parent) {
					i++;
				}
				Position [] path = new Position[i];
				for(Position x = initial; x.parent!=null; x=x.parent) {
					path[i-1]=x;
					i--;
				}
				for (int z=0; z<maze.length; z++) {
					for (int j=0; j<maze.length; j++) {
						if (maze[z][j]!='1')
							maze[z][j]='0';
					}
				}
				for(Position x = initial; x.parent!=null; x=x.parent)
					maze[x.i][x.j]='X';
				maze[0][0]='X';
				return path;
			}
			else { 
				maze[initial.i][initial.j]='X';
				validqueue(maze,initial,queue);
			}
		}
		return null;
	}

	public static void validqueue(char [] [] maze, Position current, ArrayDeque<Position> ins) {
		int x = current.i; int y = current.j;
		if (y-1>-1 && maze[x][y-1]=='0') 
			ins.addLast(new Position(x,y-1,maze[x][y-1],current));

		if (x-1>-1 && maze[x-1][y]=='0')
			ins.addLast(new Position(x-1,y,maze[x-1][y],current));

		if (x+1<maze.length && maze[x+1][y]=='0')
			ins.addLast(new Position(x+1,y,maze[x+1][y],current));

		if (y+1<maze.length && maze[x][y+1]=='0')
			ins.addLast(new Position(x,y+1,maze[x][y+1],current));
	}
	
	public static void printPath(Position [] path){
		if(path==null){
			System.err.println("There is no path through the maze.");
            		System.exit(1);
		}
		System.out.print("Path: ([0][0], ");
		for (int i=0; i<path.length-1; i++)
			System.out.print("["+path[i].i+"]"+"["+path[i].j+"], ");
		System.out.print("["+path[path.length-1].i+"]"+"["+path[path.length-1].j+"]"+")");
		System.out.println();
	}
	
	/**
	 * Reads maze file in format:
	 * N  -- size of maze
	 * 0 1 0 1 0 1 -- space-separated 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static char [][] readMaze(String filename) throws IOException{
		char [][] maze;
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("*** Invalid filename: " + filename);
			return null;
		}
		
		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		int i=0;
		while(i < N && scanner.hasNext()){
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j!=N){
				System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
				return null;
			}
			i++;
		}
		if(i!=N){
			System.err.println("*** Invalid file: has wrong number of rows: " + i);
			return null;
		}
		return maze;
	}
	
	public static void printMaze(char[][] maze){
		
		if(maze==null || maze[0] == null){
			System.err.println("*** Invalid maze array");
			return;
		}
		
		for(int i=0; i< maze.length; i++){
			for(int j = 0; j< maze[0].length; j++){
				System.out.print(maze[i][j] + " ");	
			}
			System.out.println();
		}
		
		System.out.println();
	}
}