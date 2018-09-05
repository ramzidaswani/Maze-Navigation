
public class Mazer {
	
	public static void main(String[] args) {
		
	int[][] grid = {{1,1,1,0,1,1,0,0,0,1,1,1,1},
				   {1,0,1,1,1,0,1,1,1,1,0,0,1},
		           {0,0,0,0,1,0,1,0,1,0,1,0,0},
		           {1,1,1,0,1,1,1,0,1,0,1,1,1},
		           {1,0,1,0,0,0,0,1,1,1,0,0,1},
		           {0,0,1,1,1,1,1,1,0,1,1,1,1},
		           {1,0,0,0,0,0,0,0,0,0,0,1,0},
		           {1,1,1,1,1,1,1,1,1,1,1,1,1}};
	
	System.out.println(grid[1][1]);
	Node newnode= new Node(5);
	newnode.addNode(1);
	addNode(2);
	addNode(3);
	System.out.println(Node);
	

	}
	
	public static void navigate(int [][] arr){
		
		int i =0;
		
		while(arr[i++][0]==1){
		//	y.Node=arr[i][0];
		}
		
	}
	
	public static void addNode(int element){
		
		Node newnode= new Node(element);
		
		newnode.key=element;
		
		System.out.println(newnode);
		
			
		}
	
	

}
