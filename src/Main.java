//import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /*
     * Main Function reads the number of vertices and edges in a graph.
     * then creates a Adjacency List for the graph and prints it.
     * Then, it may execute following functions :
     *  BFS(start);
     *  DFS();
     *  Dijkistra's
     *  Presence of a cycle using DFS traversal;
     *  TopologicalSort()
     *  TransposeGraph()
     */

	public static void main(String...arg) throws FileNotFoundException {

        int source , destination, weight;
        int number_of_edges,number_of_vertices;
        int count = 1;
        int directed = 0;
        // Scanner scan = new Scanner(System.in);
        Scanner scan = new Scanner(new File("SCC.txt"));        
        try
        {
            //Read the number of vertices and edges in graph
            //System.out.println("Enter the number of vertices and edges in graph");
            number_of_vertices = scan.nextInt();
            number_of_edges = scan.nextInt();
            //System.out.println("Press 1 for Directed edges");
            directed = scan.nextInt();
            	
            
            Graph G = new Graph(number_of_vertices, directed);

            //Reads the edges present in the graph 
            //System.out.println("Enter "+ number_of_edges +" edges in the Format : <source index> <destination index>");
            while (count <= number_of_edges)
            {
                source = scan.nextInt();
                destination = scan.nextInt();
                weight = scan.nextInt();
                G.setEdge(source, destination, weight);
                count++;
            }
            
            G.printAdjacencyList(); 
            G.BFS(1);
            G.PrintDFSTraversal();
            G.Dijkstra(1);
            G.Prims_MST(1);
            G.TopologicalSort();
            G.DFS_CYCLE();
            
            Graph GT = G.TransposeGraph();
            System.out.println("----TRANSPOSE--------");
  	      	GT.printAdjacencyList();
  	      	
      	  	int nSCC = G.StronglyConnectedComponents();
            System.out.println("Number of Strongly connected component : " + nSCC);
         } 
         catch(InputMismatchException inputMismatch)
         {
             System.out.println("Error in Input Format. \nFormat : <source index> <destination index>");
         }
         scan.close();
	}
}
