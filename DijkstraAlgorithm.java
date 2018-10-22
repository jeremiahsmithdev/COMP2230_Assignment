// import DijkstraUsingMinHeap.MinHeap;

/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: DijkstraAlgorithm that goes through the graph to produce a minheap of results
 *       */

//TODO MinHeap must be constructed since this algorithm runs on saving values into
//the min heap, also dijkstra algorithm still not finished
//TODO test if the values are readying through properly, just typing stuff up
//so that i have a solid understanding of what the classes should be doing
public class DijkstraAlgorithm
{
    private Graph graph;
    private String station1,station2;
    private String optimisationCriteria = "time";	// hardcoded for now TODO set properly
    //don't think you really need anything in the constructor
    public DijkstraAlgorithm(Graph graph) 
    {
	    this.graph = graph;
    }

    //recieves the graph then sets first station as beginning vertex
    //it then calculates all of the possibile shortest paths to every verticies
    //using the 2nd station it finds the shortest path to a vertices with that same name
    public String getMinPath(String station1, String station2)
    {
        int INFINITY = Integer.MAX_VALUE;
	int destination = 5;
        boolean[] visitedList = new boolean[graph.getVertices().size()];

        //Generate the heapnodes with the amount of vertices
        //all of the distances are set to inifinity initially;
        HeapNode [] heapNodes = new HeapNode[graph.getVertices().size()];
        for(int i = 0; i < heapNodes.length; i++)
        {
            heapNodes[i] = new HeapNode(graph.getVertices().get(i),INFINITY);
        }
	System.out.println(heapNodes.length);

        //except for the initial node which is set to 0
        for(int i = 0; i < graph.getVertices().size(); i++)
        {
            if(graph.getVertices().get(i).getName().equals(station1))
            {
                heapNodes[i].setWeight(0);
            }
        }
	// heapNodes[sourceVertex].setWeeight(0);

        //add all the vertices to MinHeap
        MinHeap minHeap = new MinHeap(graph.getVertices().size());
        // for(int i = 0; i < graph.getVertices().size(); i++)
        for(int i = 0; i < heapNodes.length; i++)
        {
            minHeap.insert(heapNodes[i]);
        }
//TODO not sure exactly how the minheap traverses
        //while minHeap is not empty
        while(!minHeap.isEmpty())
        {
            //extract the min
            //of the minheap
            HeapNode extractedNode = minHeap.extractMin();
	    // System.out.println("negative? " +heapNodes[extractedNode.getVertex().getID()].getWeight());


// System.out.println(heapNodes[252].getWeight());
            //extracted vertex
            // int extractedVertex = extractedNode.getVertices();
            Station extractedVertex = extractedNode.getVertex();
            visitedList[extractedVertex.getID()] = true;

            //iterate through all the adjacent vertices
            for(int i = 0; i <graph.getEdges().size() ; i++)
            {
		    StationEdge edge = graph.getEdges().get(i);
                destination = graph.getEdges().get(i).getDestination().getID();//getValue(optimisationCriteria);
                if(visitedList[destination]==false)
                {
                    ///check if duration needs an update or not
                    //means check total weight from source to vertex_V is less than
                    //the current distance value, if yes then update the distance
                    int newKey = heapNodes[edge.getSource().getID()].getWeight() + edge.getDuration();//getValue(optimisationCriteria);//abbrev...
		    // System.out.println("From " + edge.getSource().getName() + " to " + edge.getDestination().getName());
		    // System.out.println(" starting with " + heapNodes[edge.getSource().getID()].getWeight() + " and adding " + edge.getDuration());
		    // System.out.println("result: " + newKey+"\n");
                    int currentKey = heapNodes[destination].getWeight();

                    if(currentKey>newKey)
                    {
			// System.out.println("path updated for weight " + edge.getDuration() +  " to destination " + destination);
                        decreaseKey(minHeap, newKey, destination);
                        heapNodes[destination].setWeight(newKey); 
			// System.out.println(destination + " set to " + newKey);

			String prePath = heapNodes[edge.getSource().getID()].getPath();
			heapNodes[destination].updatePath(prePath, edge.getSource().getName(), edge.getDuration(), edge.getDestination().getName(), heapNodes[edge.getDestination().getID()]);
                    }
                }
            }
        }

            // System.out.println("Dijkstra Algorithm: (Adjacency List + Min Heap)");
            // for (int i = 0; i < graph.getVertices().size(); i++) 
            // {
            //     System.out.println("Source Vertex: " + station1+ " to vertex " +   + i +
            //             " distance: " + heapNodes[i].getWeight());
            // }

	    minHeap.display();
	    return DijkstraResults(heapNodes, 0, 5);//station1, station2);
	    // return heapNodes[5].getWeight();
	// return heapNodes[222].getWeight();
    }
        public String DijkstraResults(HeapNode[] resultSet, int sourceVertex, int destinationVertex)
	{
            for (int i = 0; i < graph.getVertices().size() ; i++) 
            {
		    if (i == destinationVertex)
		    {
			System.out.println(resultSet[i].getPath());
			System.out.println("Source Vertex: " + graph.getVertices().get(sourceVertex).getName() + " to vertex " + graph.getVertices().get(destinationVertex).getName() +
					" distance: " + resultSet[i].getWeight());
		    }
            }
	    return "";
        }
    
    public void decreaseKey(MinHeap minHeap, int newKey, int vertex)
    {
        //get the index which duraction's needs a decrease
        int index = minHeap.indexes[vertex];

        //get the node and update its value from minheap
//TODO HEAP NODE mh hasn't been created, since min heaap hasn't been made yet
        HeapNode node = minHeap.mH[index];
	System.out.println("minheap " +node.getWeight());
        node.setWeight(newKey);
        //function within minHeap
        minHeap.bubbleUp(index);
    }

}
