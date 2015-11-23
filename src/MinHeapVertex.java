
public class MinHeapVertex {
	private Vertex[] heap_array; // pointer to array of elements in heap
    private int capacity; // maximum possible size of min heap
    private int heap_size; // Current number of elements in min heap
    Integer[] index;

    public MinHeapVertex(int cap){
        heap_size = 0;
        capacity = cap;
        heap_array = new Vertex[cap];
        index = new Integer[cap+1];
    }
    
    
    public void MinHeapifyDown(int i){
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && heap_array[l].getDistance() < heap_array[i].getDistance())
            smallest = l;
        if (r < heap_size && heap_array[r].getDistance() < heap_array[smallest].getDistance())
            smallest = r;
        if (smallest != i)
        {
        	int indextemp = index[heap_array[i].getVertex()];
        	index[heap_array[i].getVertex()] = index[heap_array[smallest].getVertex()];           
        	index[heap_array[smallest].getVertex()] = indextemp;

        	Vertex temp = heap_array[i];
        	heap_array[i] = heap_array[smallest];        	
        	heap_array[smallest]= temp;
        	
        	MinHeapifyDown(smallest);
        }    	
    }
 
    int parent(int i) { return (i-1)/2; }
 
    int left(int i) { return (2*i + 1); }
 
    int right(int i) { return (2*i + 2); }
 
 
    Vertex extractMin(){
        if (heap_size <= 0)
            return null;
        if (heap_size == 1)
        {
            heap_size--;
            return heap_array[0];
        }
     
        // Store the minimum vakue, and remove it from heap
        Vertex root = heap_array[0];
    	
    	heap_array[0] = heap_array[heap_size-1];
    	index[heap_array[0].getVertex()] = 0;
        
        heap_size--;
        MinHeapifyDown(0);
     
        return root;
    }

    // Decreases value of key at index 'i' to new_val.  It is assumed that
    // new_val is smaller than heap_array[i].    
    public void decreaseKey(int in, int new_val){
    	int i = index[in]; 
        heap_array[i].setDistance(new_val);
        while (i != 0 && heap_array[parent(i)].getDistance() > heap_array[i].getDistance())
        {
        	
        	int indextemp = index[heap_array[i].getVertex()];
        	index[heap_array[i].getVertex()] = index[heap_array[parent(i)].getVertex()];
        	index[heap_array[parent(i)].getVertex()] = indextemp;        	
        	
        	Vertex temp;
            temp = heap_array[i];
            heap_array[i] = heap_array[parent(i)];
            heap_array[parent(i)] = temp;           
            //swap(&heap_array[i], &heap_array[parent(i)]);
            i = parent(i);
        }
    }
 
    // Returns the minimum key (key at root) from min heap
    public Vertex getMin() { return heap_array[0]; }
 
    // Deletes a key stored at index i
    public void deleteKey(int i){
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }

    // Inserts a new key 'k'
    public void insertKey(Vertex k){
        if (heap_size == capacity)
        {
            System.err.println("\nMinHeap::Overflow: Could not insertKey\n");
            return;
        }
     
        // First insert the new key at the end
        heap_size++;
        int i = heap_size - 1;
        heap_array[i] = k;
        index[k.getVertex()] = i;
     
        // Fix the min heap property if it is violated
        while (i != 0 && heap_array[parent(i)].getDistance() > heap_array[i].getDistance())
        {

        	int indextemp = index[ heap_array[i].getVertex() - 1 ];
        	index[heap_array[i].getVertex()] = index[heap_array[parent(i)].getVertex()];
        	index[heap_array[ parent(i)].getVertex()] = indextemp;
        	
        	Vertex temp;
        	temp = heap_array[i];
        	heap_array[i] = heap_array[parent(i)];
        	heap_array[parent(i)] = temp;
           
        	//swap(&heap_array[i], &heap_array[parent(i)]);
        	i = parent(i);
        }
    }
    
    Boolean isEmpty(){
    	if(heap_size == 0)
    		return true;
    	else
    		return false;
    }
           
	public Vertex getHeapAtIndex(int i){
		return heap_array[index[i]];
	}
	
    
}
