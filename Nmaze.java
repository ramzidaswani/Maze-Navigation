
public class Nmaze {
	
	static int[][] grid = {{1,1,1,0,1,1,0,0,0,1,1,1,1},
						   {1,0,1,1,1,0,1,1,1,1,1,0,1},
				           {0,0,0,0,1,0,1,0,1,0,1,0,0},
				           {1,1,1,0,1,1,1,0,1,0,1,1,1},
				           {1,0,1,0,0,0,0,1,1,1,0,0,1},
				           {0,0,1,1,1,1,1,1,0,1,1,1,1},
				           {1,0,0,0,0,0,0,0,0,0,0,1,0},
				           {1,1,1,1,1,1,1,1,1,1,1,1,1}};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Nmaze labyrinth = new Nmaze();
	      
	      labyrinth.print_maze(grid);

	      if (labyrinth.solve(grid,0, 0))
	         System.out.println ("Maze solved!");
	      else
	         System.out.println ("No solution.");

	      labyrinth.print_maze(grid);

	}
	
	public void print_maze (int[][] grid2) {
		   
	      System.out.println();

	      for (int row=0; row < grid2.length; row++) {
	         for (int column=0; column < grid2[row].length; column++)
	            System.out.print (grid2[row][column]);
	         System.out.println();
	      }

	      System.out.println();
	      
	   }  
	
	public static boolean solve(int maze[][],int row, int column){
		
		boolean done=false;
		
		if(row<maze.length && column < maze[0].length && row>=0 && column>=0 && maze[row][column]==1   ){
			
			maze[row][column]=0;
			
			if(row==maze.length-1 && column== maze[0].length-1 )
				done=true ;
			
			else{
				
				done=solve(maze,row+1,column);
				
				if(!done){
					done=solve(maze, row, column+1);
				}
				if(!done){
					done=solve(maze, row-1, column);
				}
				if(!done){
					done=solve(maze, row, column-1);
				}
				
			}
			
		}
		
		if(done){
			maze[row][column]= 7;
		}
		return done;
	}
}
