enum ColorType {
	White,
	Gray,
	Black
}

class Vertex{
	Integer Val;
	ColorType Color; //White or Grey or Black
	Integer d;
	Integer f;
	Integer p;
		
	public Vertex(int V, ColorType C, Integer dist, Integer parent){
		Val = V;
		Color = C;
		d = dist;
		p = parent;
	}
	
	public Vertex() {
		Val = 0;
		this.Color = ColorType.White;
		d = Integer.MAX_VALUE;
		f = 0;
		p = 0;		
	}
	
	public void setVertex(int V, ColorType C, Integer dist, Integer f){
		Val = V;
		Color = C;
		d = dist;
		this.f = f;
		p = 0;
	}
	
	public void setVertex(int V, ColorType C){
		Val = V;
		Color = C;
		d = 0;
		f = 0;
		p = 0;
	}

	int getVertex(){
		return Val;
	}
	
	int getDistance(){
		return d;
	}
	
	void setDistance(Integer dist){
		d = dist;
	}
}
