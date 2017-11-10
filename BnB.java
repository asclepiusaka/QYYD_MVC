class BnB{
	private int upperBound;
	private int lowerBound;
	private long start, end;
	private ArrayList<Vertex> optimalSolution;
	private ArrayList<Vertex> currentSolution;
	private Graph G;


	BnB(long interval, Graph g){
		start = System.currentTimeMillis();
		end = start + interval * 1000;
		upperBound = Integer.MAX_VALUE;
		lowerBound = 0;
		G = g;
	}

	public void checkTime(){
		return System.currentTimeMillis()>=end;
	}

	public boolean vertexCover(){
		for (boolean b:G.edgeCovered){
			if (!b) return false;
		}
		return true;
	}

	public int DFS(){
		if(checkTime){
			return 0;
		}
		if(vertexCover()){
			if(currentSolution.size() < optimalSolution.size())
				optimalSolution = currentSolution;
			return 0;
		}

		int index;
		for(int i=0; i<G.vertexCovered.length; i++){//choose a vertex not pruned or selected
			if(!G.vertexCovered[i]){
				index = i;
				break;
			}
		}

		//make copy before we mess around
		boolean[] copyVertexCovered = Arrays.copyOf(vertexCovered, vertexCovered.length);
		boolean[] copyEdgeCovered = Arrays.copyOf(edgeCovered, edgeCovered.length);

		Vertex currentChoice = G.getVertex(index);//the vertex we choose to add in solution
		currentSolution.add(currentChoice);
		int[] keys = currentChoice.getEdgeKeys();//get all edges' keys linked to this vertex 
		for(int key:keys){
			Edge temp = edgeMap.get(key);//find correspond edge in HashMap by key
			edgeCovered[temp.id] = true;//mark this edge covered based on id
			//prune vertices
			Vertex another = temp.getAnother();//get adjacent vertex of this edge "temp"
			int[] keysAnother = another.getEdgeKeys();//get all the edges' keys of vertex "another"
			boolean tempBoolean = true;
			for(int keyAnother:keysAnother){
				Edge tempAnother = edgeMap.get(keyAnother);//find correspond edge in HashMap by keyAnother
				tempBoolean = tempBoolean && edgeCovered[tempAnother.id];
				// if(!edgeCovered[tempAnother.id]) break;//if any edge has not been covered, break
			}
			vertexCovered[another.id] = tempBoolean;
		}

		DFS();
		
		//restore what we messed
		vertexCovered = copyVertexCovered;
		edgeCovered = copyEdgeCovered;

	}



}