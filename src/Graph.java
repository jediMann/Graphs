import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import java.util.Map;
import java.util.HashMap;

public class Graph 
{
	
   /* Makes use of Map collection to store the adjacency list for each vertex.*/
    private  Map<Integer, List<Edge> > Adjacency_List;	
	private  Map<Integer, Vertex> V = null;
	private int nVertices;
	private int directed; //directed = 1 means directed, otherwise not
	
   /*
    * Initializes the map to with size equal to number of vertices in a graph
    * Maps each vertex to a given List Object 
    */
    public Graph(int number_of_vertices, int isdirected)
    {
    	directed = isdirected;
    	nVertices = number_of_vertices;
    	V = new HashMap<Integer, Vertex>();
        Adjacency_List = new HashMap<Integer, List<Edge>>();	

    	for (int i = 1 ; i <= nVertices ; i++)
        	V.put(i , new Vertex(i,ColorType.White, Integer.MAX_VALUE, 0));
        
        for (int i = 1 ; i <= number_of_vertices ; i++)
            Adjacency_List.put(i, new LinkedList<Edge>());        
    }

 
    /* Adds nodes in the Adjacency list for the corresponding vertex */
    public void setEdge(int source , int destination, int weight)
    {
       if (source > Adjacency_List.size() || destination > Adjacency_List.size())
       {
           System.out.println("the vertex entered in not present ");
           return;
       }

       List<Edge> slist = Adjacency_List.get(source);
  	   slist.add(new Edge(source, destination, weight));

  	   if(directed != 1){				//not directed
  	  	   List<Edge> dlist = Adjacency_List.get(destination);
  	       dlist.add(new Edge(destination, source, weight));
  	   }
   }

    /* Removes nodes in the Adjacency list for the corresponding vertex */
    public void removeEdge(int source , int destination)
    {
       if (source > Adjacency_List.size() || destination > Adjacency_List.size())
       {
           System.out.println("one of the vertex entered in not present ");
           return;
       }

       List<Edge> sList =  this.getEdge(source);
       for(Iterator<Edge> it = sList.iterator(); it.hasNext(); ){
    	   Edge e = it.next();
    	   if(e.getEndVertex() == destination){
    		   it.remove();
    		   break;
    	   }
       }
	   
  	   if(directed != 1){				//not directed
  	  	   List<Edge> dList = Adjacency_List.get(destination);
  	       for(Iterator<Edge> it = dList.iterator(); it.hasNext(); ){
  	    	   Edge e = it.next();
  	    	   if(e.getEndVertex() == source){
  	    		   it.remove();
  	    		   break;
  	    	   }
  	       }
  	   }       
   }
    
    /* Returns the List containing the vertex joining the source vertex */
    public List<Edge> getEdge(int source)
    {
        if (source > Adjacency_List.size())
        {
            System.out.println("The vertex " + source + " is not present");
            return null;
        }

        return Adjacency_List.get(source);
    }



    /* Prints the adjacency List representing the graph.*/
    public void printAdjacencyList(){

    	if(directed == 1)
    		System.out.println("Adjacency List for the directed graph :");
    	else
    		System.out.println("Adjacency List for the undirected graph :");
    	
        for (int i = 1 ; i <= nVertices ; i++)
        {
           	System.out.print(i+"->[");
            List<Edge> edgeList =  this.getEdge(i);
            for (int j = 1 ; ; j++ )
            {
           	 if(!edgeList.isEmpty()){
                    if (j != edgeList.size())
                    {
                        System.out.print((edgeList.get(j - 1)).getVertex()+"->");
                    }else
                    {
                        System.out.print((edgeList.get(j - 1)).getVertex() + "]");
                        break;
                    }
           	 }else{
           		 System.out.print("]");
           		 break;
           	 }
           		 
            }

            System.out.println();
         } 
        
    }
   
    
    
    //Breadth First Search
    void BFS(int source){

    	System.out.println("--------In BFS with source vertex : " + source);
    	
    	for(int i=1 ; i<= nVertices ; i++){
    		V.get(i).setVertex(i, ColorType.White, 0, 0 );
    	}

        int u, v;
   	    LinkedList<Vertex> Q=new LinkedList<Vertex>();

   	    V.get(source).setVertex(source,ColorType.Gray, 0, 0);
    	Q.addLast(V.get(source));
    	while(!Q.isEmpty()){
    		u = (Q.removeFirst()).getVertex();
    		System.out.print(u);

    		List<Edge> ulist = Adjacency_List.get(u);
        	for(Iterator<Edge> it = ulist.iterator(); it.hasNext(); ){
        		Edge e = it.next();
        		v = e.getEndVertex();
        		if(	V.get(v).Color == ColorType.White){
        			V.get(v).Color = ColorType.Gray;
        			V.get(v).d = V.get(u).d + 1;
        			V.get(v).p = V.get(u).getVertex();
        			Q.addLast(V.get(v));
        		}
        		V.get(u).Color = ColorType.Black;
        	}
        	if(!Q.isEmpty())
        		System.out.print(" -> ");
    	}
    	System.out.println();

    }
    
    

    //Depth First Search
    void DFS(){
    	int time = 0;
    	System.out.println("----------DFS---------");
    	
    	for(int i=1 ; i<= nVertices ; i++){
    		V.get(i).setVertex(i, ColorType.White, 0, 0 );
    	}
    	
    	for(int i=1 ; i<= nVertices ; i++){
    		if(V.get(i).Color == ColorType.White)
    			time = DFS_VISIT(i, time);
     	}
    	
    	for(int i =1 ;i <= nVertices; i++){
    		System.out.println("Node "+ i + " -> (" + V.get(i).d +", " + V.get(i).f + ")" );    		
    	}

    }
    
    int DFS_VISIT(int u, int time){
    	
    	int v;
    	time = time + 1;
    	
    	V.get(u).d = time;
    	V.get(u).Color = ColorType.Gray;
    	List<Edge> ulist = Adjacency_List.get(u);
    	for(Iterator<Edge> it = ulist.iterator(); it.hasNext(); ){
    		Edge e = it.next();
    		v = e.getEndVertex();
    		if(	V.get(v).Color == ColorType.White){
    			V.get(v).p = u;
    			time = DFS_VISIT(v,time);
    		}
    	}
		V.get(u).Color = ColorType.Black;
		time = time + 1;
		V.get(u).f = time; 
		    	
    	return time;
    }
    
    
    
    //Dijkstra Minimum Spanning Tree
    void Dijkstra( int s){
    	
    	System.out.println("--------Dijkstra---------");
    	
    	for(int i=1 ;i<= nVertices ; i++){
    		V.get(i).setVertex(i, ColorType.White, Integer.MAX_VALUE/2, 0 );
    		if(i == s){
    	    	V.get(s).setVertex(s, ColorType.White, 0, 0);
    		}
    	}

        Vertex u;
        int v;            	    	
    	MinHeapVertex H = new MinHeapVertex(nVertices);    	

    	for(int i=1 ; i<= nVertices ; i++)
    		H.insertKey(V.get(i));
    		
    	while(!H.isEmpty()){
    		u = H.extractMin();
    		V.get(u.getVertex()).Color = ColorType.Black;    		
    		System.out.println(u.getVertex());
    		
    		List<Edge> ulist = Adjacency_List.get(u.getVertex());
        	for(Iterator<Edge> it = ulist.iterator(); it.hasNext(); ){
        		Edge e = it.next();
        		v = e.getEndVertex();
        		Vertex adjacentVertex = H.getHeapAtIndex(v);
    			if(V.get(v).Color == ColorType.White && u.d + e.Weight < adjacentVertex.d){
    				H.decreaseKey(v, u.d + e.Weight);
    				V.get(v).d = u.d + e.Weight;
        			V.get(v).p = u.getVertex();
    			}
        	}
    	}
    	printDijkstraDistances();
    }
    
    void printDijkstraDistances(){
    	System.out.println("Vertex   Distance from Source");
   	    for (int i = 1; i <=nVertices; ++i)
   	    	System.out.println(V.get(i).Val + "\t\t" + V.get(i).d);
    }
    
    

    
    //Prim's Minimum Spanning Tree
    void Prims_MST( int s){
    	
    	System.out.println("--------Prim's MST---------");
    	System.out.println("Edge\t\tWeight");
    	
    	for(int i=1 ;i<= nVertices ; i++){
    		V.get(i).setVertex(i, ColorType.White, Integer.MAX_VALUE/2, 0 );
    		if(i == s){
    	    	V.get(s).setVertex(s, ColorType.White, 0, 0);
    		}
    	}

        Vertex u;
        int v;            	    	
    	MinHeapVertex H = new MinHeapVertex(nVertices);    	

    	for(int i=1 ; i<= nVertices ; i++)
    		H.insertKey(V.get(i));
    		
    	while(!H.isEmpty()){
    		u = H.extractMin();
    		V.get(u.getVertex()).Color = ColorType.Black;    		
    		
    		List<Edge> ulist = Adjacency_List.get(u.getVertex());
        	for(Iterator<Edge> it = ulist.iterator(); it.hasNext(); ){
        		Edge e = it.next();
        		v = e.getEndVertex();
        		Vertex endVertex = H.getHeapAtIndex(v);
        			if(V.get(v).Color == ColorType.White && e.Weight < endVertex.d){      				
        				H.decreaseKey(v, e.Weight);
            			V.get(v).p = V.get(u.getVertex()).getVertex();
        			}
        	}
    	}

    	int lengthShortestPath = 0;
   	    for (int i = 2; i <=nVertices; ++i){
   	    	System.out.println(V.get(i).p + " - " + V.get(i).Val + "\t\t" + V.get(i).d);   	    	
   	    	lengthShortestPath += V.get(i).d;
   	    }

   	    System.out.println("Length of Prim's Shortest Path is : " + lengthShortestPath);
    }


}