package big;
import java.util.List;
import java.util.ArrayList;
public class Graph {
	public List<Vertex> VertexList;
	 	//maybe another boolean array to denote visited
	 	//add data structure as you need
	 
	 	public Graph(int initSize) {
	 		VertexList = new ArrayList<>(initSize+1);
	 		for(int i = 0;i<initSize+1;i++) {
	 			VertexList.add(new Vertex(i));
	 		}
	 	}
	 	
	 	public Graph() {
	 		VertexList = new ArrayList<>();
	 	}
	 	
	 	public int size() {
	 		return VertexList.size()-1;
	 	}
	 	
	 	public Vertex getVertex(int i) {
	 		return this.VertexList.get(i);
	 	}
}
