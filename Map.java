import java.util.Scanner;
 
public class Map
{
    private int distances[];
    private int vertices ;
    public static final int maximum =1000;
 
    public Map(int vertices )
    {
        this.vertices  = vertices ;
        distances = new int[vertices  + 1];
    }
 
    public void BellmanFordEvaluation(int source, int adjacencymatrix[][])
    {
        for (int node = 1; node <= vertices ; node++)
        {
            distances[node] = maximum;
        }
 
        distances[source] = 0;
        for (int node = 1; node <= vertices  - 1; node++)
        {
            for (int root = 1; root <= vertices ; root++)
            {
                for (int destinationnode = 1; destinationnode <= vertices ; destinationnode++)
                {
                    if (adjacencymatrix[root][destinationnode] != maximum)
                    {
                        if (distances[destinationnode] > distances[root] 
                                + adjacencymatrix[root][destinationnode])
                            distances[destinationnode] = distances[root]
                                + adjacencymatrix[root][destinationnode];
                    }
                }
            }
        }
 
        for (int root = 1; root <= vertices ; root++)
        {
            for (int destinationnode = 1; destinationnode <= vertices ; destinationnode++)
            {
                if (adjacencymatrix[root][destinationnode] != maximum)
                {
                    if (distances[destinationnode] > distances[root]
                           + adjacencymatrix[root][destinationnode])
                        System.out.println("The Graph contains negative egde cycle");
                }
            }
        }
 
        for (int vertex = 1; vertex <= vertices ; vertex++)
        {
            System.out.println("distance of source  " + source + " to "
                      + vertex + " is " + distances[vertex]);
        }
    }
 
    public static void main(String... arg)
    {
        int vertices  = 0;
        int source;
        Scanner scanner = new Scanner(System.in);
 
        vertices  = 5;
 
        int adjacencymatrix[][] = new int[vertices  + 1][vertices  + 1];
        System.out.println("Enter the adjacency matrix");
        for (int root = 1; root <= vertices ; root++)
        {
            for (int destinationnode = 1; destinationnode <= vertices ; destinationnode++)
            {
                adjacencymatrix[root][destinationnode] = scanner.nextInt();
 	        if (root == destinationnode)
                {
                    adjacencymatrix[root][destinationnode] = 0;
                    continue;
                }
                if (adjacencymatrix[root][destinationnode] == 0)
                {
                    adjacencymatrix[root][destinationnode] = maximum;
                }
            }
        }
 
        
        source = 0;
 
        Map bellmanford = new Map(vertices );
        bellmanford.BellmanFordEvaluation(source, adjacencymatrix);
        scanner.close();	
    }
}