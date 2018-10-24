// import DijkstraUsingMinHeap.MinHeap;
/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: DijkstraAlgorithm that goes through the graph to produce a minheap of results
 *       */
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
    public String getMinPath(String station1, String station2, String optimisationCriteria)
    {
//NEW CHANGES HERE
        this.optimisationCriteria = optimisationCriteria;
        int INFINITY = Integer.MAX_VALUE;
        int destination = 5;
        //visited list of boolean that is the size of all the vertices
        boolean[] visitedList = new boolean[graph.getVertices().size()];

        //Generate the heapnodes with the amount of vertices
        //all of the distances are set to inifinity initially;
        HeapNode [] heapNodes = new HeapNode[graph.getVertices().size()];
        for(int i = 0; i < heapNodes.length; i++)
        {
            heapNodes[i] = new HeapNode(graph.getVertices().get(i),INFINITY);
        }

        //Bug Testing
//	    System.out.println(heapNodes.length);

        //except for the initial node which is set to 0
        for(int i = 0; i < graph.getVertices().size(); i++)
        {
            if(graph.getVertices().get(i).getName().equals(station1))
            {
                heapNodes[i].setComparator(0);
            }
        }

        //add all the vertices to MinHeap
        MinHeap minHeap = new MinHeap(graph.getVertices().size(), optimisationCriteria);

        for(int i = 0; i < heapNodes.length; i++)
        {
            minHeap.insert(heapNodes[i]);
        }
//TODO not sure exactly how the minheap traverses
        //while minHeap is not empty

//	System.out.println("INITIAL HEAP:");
	minHeap.display();

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
//	    System.out.println("\n 						Extracted min is " + extractedVertex.getName() + " with value " + extractedNode.getComparator(optimisationCriteria));

            //iterate through all the adjacent vertices
            // for(int i = 0; i <graph.getEdges().size() ; i++)
	    for (int i = 0; i < extractedVertex.getEdges().size(); i++)
            {
		    // StationEdge edge = graph.getEdges().get(i);
		    StationEdge edge = extractedVertex.getEdges().get(i);
                destination = edge.getDestination().getID();//graph.getEdges().get(i).getDestination().getID();//getValue(optimisationCriteria);
                if(visitedList[destination]==false)
                {
                    ///check if duration needs an update or not
                    //means check total weight from source to vertex_V is less than
                    //the current distance value, if yes then update the distance

//			System.out.println("UPDATING KEY for: " + edge.getDestination().getName() + ": " + heapNodes[extractedVertex.getID()].getComparator(optimisationCriteria) + " += " + edge.getDuration() + " (SOURCE = " + edge.getSource().getName() + ")");


                    int newKey = heapNodes[extractedVertex.getID()].getComparator(optimisationCriteria) + edge.getDuration();//getValue(optimisationCriteria);//abbrev...
//		    System.out.println("acquired newKey " + newKey);
		    // System.out.println("From " + edge.getSource().getName() + " to " + edge.getDestination().getName());
		    // System.out.println(" starting with " + heapNodes[edge.getSource().getID()].getWeight() + " and adding " + edge.getDuration());
		    // System.out.println("result: " + newKey+"\n");
                    int currentKey = heapNodes[destination].getComparator(optimisationCriteria);


		    // System.out.println("current key taken from " + );
//		    System.out.println("currentKey " + currentKey + " newKey " + newKey );
                    if(currentKey>newKey)
                    {
//			    System.out.println("UPDATE SUCCESS");

			// System.out.println("path updated for weight " + edge.getDuration() +  " to destination " + destination);
                        decreaseKey(minHeap, newKey, destination);
                        heapNodes[destination].setComparator(newKey); 
			// System.out.println(destination + " set to " + newKey);

			String prePath = heapNodes[edge.getSource().getID()].getPath();
//			System.out.println("\n from " + edge.getSource().getName() + " to " + edge.getDestination().getName() + " in " + edge.getDuration());
			// String prePath = heapNodes[extractedVertex.getID()].getPath();
			// System.out.println("TRACING ---- " +prePath);
			heapNodes[destination].updatePath(prePath, edge, heapNodes[edge.getDestination().getID()]);
                    }
//			System.out.println("MINHEAP DISPLAY: ");
			minHeap.display();
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
	    return DijkstraResults(heapNodes, station1, station2);//station1, station2);
	    // return heapNodes[5].getWeight();
	// return heapNodes[222].getWeight();
    }
    public String DijkstraResults(HeapNode[] resultSet, String source, String destination)
    {
	    int current = Integer.MAX_VALUE;
	    String minPath = "";
	    for (int i = 0; i < graph.getVertices().size() ; i++) 
	    {
//NEW CHANGES HERE
            //asks for time
            if (graph.getVertices().get(i).getName().equals(destination))
		    //if (graph.getVertices().get(i).getName().equals(destination) && resultSet[i].getComparator(optimisationCriteria) < current)
		    {
			    current = resultSet[i].getComparator(optimisationCriteria);
                minPath = "From " + source + resultSet[i].getPath();
                System.out.println("here ");
                    //if asks for time
                    if(this.optimisationCriteria.equals("time"))
                    {
                        minPath += "From Station: " + source + " to vertex " + destination +
                        " The total trip will take approximately " + resultSet[i].getComparator(optimisationCriteria) + 
                        " minutes and will have " + resultSet[i].getChanges() + " changes.";
                    }
                    //if asks for changes
                    else if(this.optimisationCriteria.equals("changes"))
                    {
                        minPath += "From Station: " + source + " to vertex " + destination +
				        " The total trip will have " + resultSet[i].getChanges() + " changes.";
                    }
            }

	    }

	    // for (int i = 0; i < resultSet.length; i++)
	    // {
		//     System.out.println(resultSet[i].getComparator());
	    // }
	    return minPath;
    }
    
    public void decreaseKey(MinHeap minHeap, int newKey, int vertex)
    {
        //get the index which duraction's needs a decrease
        // int index = minHeap.indexes[vertex];
	int index = minHeap.getIndex(vertex);

        //get the node and update its value from minheap
//TODO HEAP NODE mh hasn't been created, since min heaap hasn't been made yet
        HeapNode node = minHeap.getHeapNode(index);

	// System.out.println("key for vertex " +node.getVertex().getName() + " on line " + node.getVertex().getLine() + " is " + node.getWeight());
	// System.out.println("minheap " +node.getWeight());
        node.setComparator(newKey);
        //function within minHeap
        minHeap.bubbleUp(index);
    }

}
