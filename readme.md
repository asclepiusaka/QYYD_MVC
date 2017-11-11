## some assumption for coding
1. for Vertex index id, let's use index start from 1,leave the first slot in array empty (a[0]);
2. in the current parsing manner, for each Vertex, we have the EdgeList and VertexList corresponding to each other, in other word, Vertex v connect to Vertex w = v.getAdjVertexList.get(i) through Edge e = v.getAdjEdgeList.get(i). 