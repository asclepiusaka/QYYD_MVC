import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

//warning: this method use g.vertexCovered to denote current vc condition, vertex.covered is unchanged!!!
public class LocalSearch {
	private Graph g;
	private long cutoffTime;
	private long start;
	private Random r;
	
	public LocalSearch(long cutoff,Graph graph,int seed) {
		this.cutoffTime = cutoff;
		this.g = graph;
		this.start = System.currentTimeMillis();
		r = new Random(seed);
		
	}
	
	

	public int HCsolve() {
		//get the priorityQueue;
		PriorityQueue<Vertex> pq= new PriorityQueue<Vertex>(Collections.reverseOrder());
		for(int i=1;i<g.vertexList.size();i++) {
			pq.add(g.vertexList.get(i));
		}
		//set all vertex as covered at the very beginning
		for(int i=0;i<g.vertexCovered.length;i++) {
			g.vertexCovered[i] = true;
		}
		//set all edges as covered;
		Iterator<Map.Entry<Integer, Edge>> edges = g.edgeMap.entrySet().iterator();
		while(edges.hasNext()) {
			edges.next().getValue().covered=true;
		}
		g.coveredEdgeSize = g.edgeMap.size();
		
		while(pq.size()!=0&&System.currentTimeMillis()<=(start+cutoffTime*1000)) {
			int degree = pq.peek().degree;
			List<Vertex> currentDegreeList= new ArrayList<Vertex>();
			//add all vertex with same degree to the List;
			while(!pq.isEmpty()&&degree==pq.peek().degree) {
				currentDegreeList.add(pq.poll());
			}
			while(!currentDegreeList.isEmpty()) {
				int index = r.nextInt(currentDegreeList.size());
				Vertex toRemove = currentDegreeList.remove(index);
				boolean deleteable = true;
				//check all the adj vertex, if not covered, the node cannot be moved.
				for(Vertex adj:toRemove.getAdjVertexList()) {
					if(g.vertexCovered[adj.myId]==false) {
						deleteable = false;
						break;
					}
				}
				if(deleteable) {
					g.vertexCovered[toRemove.myId] = false;
				}
			}
		}
		int result = 0;
		for(int i=1;i<g.vertexCovered.length;i++) {
			if(g.vertexCovered[i]==true) {
				result++;
			}
		}
		return result;
	}
	
	public int SAsolve() {
		//set all vertex as covered at the very beginning
		for(int i=0;i<g.vertexCovered.length;i++) {
			g.vertexCovered[i] = true;
		}
		double A = 0.9999999;
		double T = 500;
		
		while(T>=0.00001&&System.currentTimeMillis()<=(start+cutoffTime*1000)) {
			int index = r.nextInt(g.vertexCovered.length-1)+1;
			Vertex vertex= g.getVertex(index);
			if(g.vertexCovered[vertex.getId()]) {//vertex is used, let's decide whether to delete or not;
				boolean deleteable = true;
				//check all the adj vertex, if not covered, the node cannot be moved.
				for(Vertex adj:vertex.getAdjVertexList()) {
					if(g.vertexCovered[adj.myId]==false) {
						deleteable = false;
						break;
					}
				}
				if(deleteable) {
					g.vertexCovered[vertex.myId] = false;
				}
				//else doing nothing.
			}else {
				double p = Math.exp(-(1/T));
				if(p>r.nextDouble()) {
					g.vertexCovered[index] = true;
				}//else doing nothing;
			}
			T *= A;
		}
		
		int result = 0;
		for(int i=1;i<g.vertexCovered.length;i++) {
			if(g.vertexCovered[i]==true) {
				result++;
			}
		}
		System.out.println("current Temp "+T);
		return result;
		
		
	}
	
}
