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
	private String criterion;	
	private String result;
	private int graphSize;
	private MinHeap minHeap;;
	private boolean visitedList[];
	private int destination;
	private Node heapNodes[];
	private Node last;
	//don't think you really need anything in the constructor
	public DijkstraAlgorithm(Graph graph) 
	{
		this.graph = graph;
		this.graphSize = graph.getVertices().size();
		this.result = "";
		this.last = new Node();
	}

	//recieves the graph then sets first station as beginning vertex
	//it then calculates all of the possibile shortest paths to every verticies
	//using the 2nd station it finds the shortest path to a vertices with that same name
	public String getMinPath(String station1, String station2, String criterion)
	{
		this.criterion = criterion;
		int MAX_VALUE = Integer.MAX_VALUE;
		int destination = 5;
		//visited list of boolean that is the size of all the vertices
		boolean[] visitedList = new boolean[graph.getVertices().size()];

		//Generate the heapnodes with the amount of vertices
		//all of the distances are set to inifinity initially;
		Node [] heapNodes = new Node[graph.getVertices().size()];
		for(int i = 0; i < heapNodes.length; i++)
		{
			heapNodes[i] = new Node(graph.getVertices().get(i),MAX_VALUE, criterion);
		}
		//except for the initial node which is set to 0
		for(int i = 0; i < graph.getVertices().size(); i++)
		{
			if(graph.getVertices().get(i).getName().equals(station1))
			{
				heapNodes[i].setTime(0);
				heapNodes[i].setChanges(0);
			}
		}
		//add all the vertices to MinHeap
		MinHeap minHeap = new MinHeap(graph.getVertices().size(), criterion);
		for(int i = 0; i < heapNodes.length; i++)
		{
			minHeap.insert(heapNodes[i]);
		}
		while(!minHeap.isEmpty())
		{
			Node extractedNode = minHeap.extractMin();		// extract min of minHeap
			Station extractedVertex = extractedNode.getVertex();	// extract vertex
			visitedList[extractedVertex.getID()] = true;
			if (extractedVertex.getName().equals(station2) && extractedNode.compareTo(last) < 0)	 // have already arrived, save data from node
			{
				result +="\n"+extractedNode.getPath();	// add travel information
				result += "The total trip will take approximately " + extractedNode.getTime() + // add travel statistics
					" minutes and will have " + extractedNode.getChanges() + " changes.";
				last = extractedNode;
			}

			//iterate through all the adjacent vertices
			for (int i = 0; i < extractedVertex.getEdges().size(); i++)
			{
				StationEdge edge = extractedVertex.getEdges().get(i);
				destination = edge.getDestination().getID();
				if(visitedList[destination]==false)
				{
					///check if duration needs an update or not
					//means check total weight from source to vertex_V is less than
					//the current distance value, if yes then update the distance
					int newTimeKey = heapNodes[extractedVertex.getID()].getTime() + edge.getDuration();
					int currentTimeKey = heapNodes[destination].getTime();
					int newChangeKey = heapNodes[extractedVertex.getID()].getChanges();
					int currentChangeKey = heapNodes[extractedVertex.getID()].getChanges();
					// increment changes when source = destination
					if (edge.getDestination().getName().equals(edge.getSource().getName()))	// increment changes when source = destination
						newChangeKey++;

					if(criterion.equals("time") && newTimeKey <= currentTimeKey)
					{
						if (newChangeKey > currentChangeKey && newTimeKey == currentTimeKey)	// prevent unnecessary updates
							newChangeKey = currentChangeKey;
						heapNodes[destination].setTime(newTimeKey); 
						heapNodes[destination].setChanges(newChangeKey);
						decreaseKey(minHeap, newTimeKey, destination);
						String prePath = heapNodes[edge.getSource().getID()].getPath();
						heapNodes[destination].updatePath(prePath, edge, heapNodes[edge.getDestination().getID()], station2);
					}
					else if (criterion.equals("changes") && newChangeKey <= currentChangeKey)
					{
						if (newTimeKey > currentTimeKey && newChangeKey == currentChangeKey)// prevent unnecessary updates
							newTimeKey = currentTimeKey;
						heapNodes[destination].setTime(newTimeKey); 
						heapNodes[destination].setChanges(newChangeKey);
						decreaseKey(minHeap, newChangeKey, destination);
						String prePath = heapNodes[edge.getSource().getID()].getPath();
						heapNodes[destination].updatePath(prePath, edge, heapNodes[edge.getDestination().getID()], station2);
					}
				}
			}
		}
		return result;
	}


	public void decreaseKey(MinHeap minHeap, int newTime, int vertex)
	{
		//get the index which duraction's needs a decrease
		// int index = minHeap.indexes[vertex];
		int index = minHeap.getIndex(vertex);

		//get the node and update its value from minheap
		Node node = minHeap.getNode(index);

		//function within minHeap
		minHeap.bubbleUp(index);
	}

}
