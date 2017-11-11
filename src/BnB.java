class BnB{
	private int upperBound;
	private int lowerBound;
	private long start, end;
	private ArrayList<Vertex> optimalSolution;
	private ArrayList<Vertex> currentSolution;
	// private int optimalSize;
	private Graph G;


	BnB(long interval, Graph g){
		start = System.currentTimeMillis();
		end = start + interval * 1000;
		upperBound = Integer.MAX_VALUE;
		lowerBound = 0;
		G = g;
		optimalSolution = new ArrayList<Vertex>();
		currentSolution = new ArrayList<Vertex>();
		// optimalSize = Integer.MAX_VALUE;
	}

	public void checkTime(){
		return System.currentTimeMillis()>=end;
	}

	public boolean vertexCover(){
		return G.coveredEdgeSize == G.edgeMap.size();
	}

	public int DFS(int index){
		if(checkTime){
			return 0;
		}
		if(vertexCover()){
			if(currentSolution.size() < upperBound){
				optimalSolution = currentSolution;
				upperBound = optimalSolution.size();
			}
			return 0;
		}

		// int index;
		for(int i=index+1; i<G.vertexCovered.length; i++){//choose a vertex not pruned or selected
			if(!G.vertexCovered[i]){
				index = i;

				//make a stack to store changed vertices and edges
				Stack<Integer> vertexStack = new Stack<Integer>();
				Stack<Integer> edgeStack = new Stack<Integer>();

				Vertex currentChoice = G.getVertex(index);//the vertex we choose to add in solution
				currentSolution.add(currentChoice);
				G.vertexCovered[index] = true;//first change
				vertexStack.push(index);

				int[] keys = currentChoice.getEdgeKeys();//get all edges' keys linked to this vertex 
				if(keys.length>0){//check whether this vertex has at least one edge
					for(int key:keys){
						Edge temp = G.edgeMap.get(key);//find correspond edge in HashMap by key

						if(!temp.covered){
							temp.covered = true;
							edgeStack.push(temp.id);
						}
						//prune vertices
						Vertex another = temp.getAnother(currentChoice);//get adjacent vertex of this edge "temp"
						if(!G.vertexCovered[another.id]){
							int[] keysAnother = another.getEdgeKeys();//get all the edges' keys of vertex "another"
							if(keysAnother.length>0){
								boolean tempBoolean = true;
								for(int keyAnother:keysAnother){
									Edge tempAnother = G.edgeMap.get(keyAnother);//find correspond edge in HashMap by keyAnother
									tempBoolean = tempBoolean && tempAnother.covered;
								}
								G.vertexCovered[another.id] = tempBoolean;
								if(tempBoolean) vertexStack.push(another.id);
							}
						}
					}
				}
				DFS(index);
				
				//restore what we messed
				while(!vertexStack.empty()){
					G.vertexCovered[vertexStack.pop()] = false;
				}
				while(!edgeStack.empty()){
					Edge tempEdge = G.edgeMap.get([edgeStack.pop()]);
					tempEdge.covered = false;//change to key later
				}
				//restore current solution
				currentSolution.remove(currentSolution.size()-1);
			}
		}
		return 1;

	}



}