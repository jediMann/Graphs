public class Edge{
	Integer StartVertex;
	Integer EndVertex;
	Integer Weight;
	
	Edge(Integer S, Integer E, Integer W){
		StartVertex = S;
		EndVertex = E;
		Weight = W;
	}
	
	public Integer getEndVertex(){
		return EndVertex;
	}
	
	int getVertex(){
		return EndVertex;
	}
}