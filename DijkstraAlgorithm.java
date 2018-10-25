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
	private String optimisationCriteria;	
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


		//except for the initial node which is set to 0
		for(int i = 0; i < graph.getVertices().size(); i++)
		{
			if(graph.getVertices().get(i).getName().equals(station1))
			{
				heapNodes[i].setTime(0);
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

		minHeap.display();

		while(!minHeap.isEmpty())
		{
			//extract the min
			//of the minheap
			HeapNode extractedNode = minHeap.extractMin();


			//extracted vertex
			// int extractedVertex = extractedNode.getVertices();
			Station extractedVertex = extractedNode.getVertex();
			visitedList[extractedVertex.getID()] = true;

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

					int newChanges = heapNodes[extractedVertex.getID()].getChanges();
					int currentChanges = heapNodes[extractedVertex.getID()].getChanges();
					int currentKeyOne = heapNodes[destination].getComparator(optimisationCriteria);
					int newKeyOne = heapNodes[extractedVertex.getID()].getComparator(optimisationCriteria) + edge.getDuration();//getValue(optimisationCriteria);//abbrev...
					if(newKeyOne <= currentKeyOne)
					{
						decreaseKey(minHeap, newKeyOne, destination);
						heapNodes[destination].setTime(newKeyOne); 

						// increment changes when source = destination
						if (edge.getDestination().getName().equals(edge.getSource().getName()))
							newChanges++;
						heapNodes[destination].setChanges(newChanges);

						String prePath = heapNodes[edge.getSource().getID()].getPath();
						heapNodes[destination].updatePath(prePath, edge, heapNodes[edge.getDestination().getID()], station2);
					}
					// else if(newKeyOne == currentKeyOne)
					// {
					// 	// increment changes when source = destination											//same
					// 	if (edge.getDestination().getName().equals(edge.getSource().getName()))
					// 		newChanges++;
					// 	heapNodes[destination].setChanges(newChanges);
					// 	//						System.out.println("5");
					// 	//if the new changes is slower, update it as the new shortest path
					// 	if(newChanges < currentChanges)
					// 		//if(currentChangesKey < newChangesKey)
					// 	{
					// 		decreaseKey(minHeap, newKeyOne, destination);
					// 		//!!changes made here check with jerry
					// 		heapNodes[destination].setTime(newKeyOne); 
                                        //
					// 		String prePath = heapNodes[edge.getSource().getID()].getPath();
					// 		heapNodes[destination].updatePath(prePath, edge, heapNodes[edge.getDestination().getID()], station2);
					// 	}
					// 	//if not it just ignores and moves onto the next verticies
					// }
				}
			}

		}

		minHeap.display();
		return DijkstraResults(heapNodes, station1, station2);
	}

	// TODO I think we can print the results more simply if we could just pull from the top of the priority queue?
	public String DijkstraResults(HeapNode[] resultSet, String source, String destination)
	{
		int current = Integer.MAX_VALUE;// current ensures we only print one of the answers, considering there is an answer for each line of the destination station
		String minPath = "";// = "From " + source;
		for (int i = 0; i < graph.getVertices().size() ; i++)
		{
			if (graph.getVertices().get(i).getName().equals(destination) && resultSet[i].getComparator(optimisationCriteria) < current)
			{
				current = resultSet[i].getComparator(optimisationCriteria);
				minPath = resultSet[i].getPath();	// add travel information
				minPath += "The total trip will take approximately " + resultSet[i].getTime() + // add travel statistics
					" minutes and will have " + resultSet[i].getChanges() + " changes.";
			}
		}


		return minPath;
	}

	public void decreaseKey(MinHeap minHeap, int newTime, int vertex)
	{
		//get the index which duraction's needs a decrease
		// int index = minHeap.indexes[vertex];
		int index = minHeap.getIndex(vertex);

		//get the node and update its value from minheap
		//TODO HEAP NODE mh hasn't been created, since min heaap hasn't been made yet
		HeapNode node = minHeap.getHeapNode(index);

		node.setTime(newTime);

		//function within minHeap
		minHeap.bubbleUp(index);
	}

}
