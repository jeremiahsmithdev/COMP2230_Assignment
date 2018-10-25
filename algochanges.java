
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
	private Node arrival;
	private MinHeap minHeap;;
	private boolean visitedList[];
	private int destination;
	private Node heapNodes[];
	//don't think you really need anything in the constructor
	public DijkstraAlgorithm(Graph graph) 
	{
		this.graph = graph;
		this.graphSize = graph.getVertices().size();
		this.result = "";
	}

	//recieves the graph then sets first station as beginning vertex
	//it then calculates all of the possibile shortest paths to every verticies
	//using the 2nd station it finds the shortest path to a vertices with that same name
	public String getMinPath(String station1, String station2, String criterion)
	{
		this.criterion = criterion;
		int MAX_VALUE = Integer.MAX_VALUE;
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
		minHeap = new MinHeap(graph.getVertices().size(), criterion);

		// for (int i = 0; i < heapNodes.length; i++)
		// {
		// 	minHeap.insert(heapNodes[i]);
		// }

		for (int i = 0; i < heapNodes.length; i++)
		{
			visitedList[i] = false;
		}
		for (int i = 0; i < heapNodes.length; i++)
		{
			// only runs on start stations
			if (visitedList[i] == false && heapNodes[i].getTime() == 0)
				bfs(minHeap.extractMin(), i);
		}

		return result;
	}

	public void bfs(Node extractedNode, int index)
	{
		Station extractedVertex = extractedNode.getVertex();
		visitedList[extractedVertex.getID()] = true;
		while(!minHeap.isEmpty())
		{
			//extracted vertex
			//
			// have already arrived, save data from node
			if (extractedVertex.getName().equals(station2))
			{
				System.out.println(extractedVertex.getName());
				result +="\n"+extractedNode.getPath();	// add travel information
				result += "The total trip will take approximately " + extractedNode.getTime() + // add travel statistics
					" minutes and will have " + extractedNode.getChanges() + " changes.";
			}


			// int extractedVertex = extractedNode.getVertices();

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

					System.out.println("========= EDGE " + edge.getSource().getName() + "  -->  " + edge.getDestination().getName());
					System.out.println("source:       Changes = " + extractedNode.getChanges() + " and Time = " + extractedNode.getTime());
					System.out.println("destination:  Changes = " + heapNodes[destination].getChanges()+ " and Time = " +heapNodes[destination].getTime()); 
					int newTimeKey = heapNodes[extractedVertex.getID()].getTime() + edge.getDuration();//getValue(crite
					System.out.println("		setting time for.. " + heapNodes[destination].getVertex().getName() + " to " + heapNodes[extractedVertex.getID()].getTime() + " + " + edge.getDuration() );
					int currentTimeKey = heapNodes[destination].getTime();
					int newChangesKey = heapNodes[extractedVertex.getID()].getChanges();
					int currentChangesKey = heapNodes[extractedVertex.getID()].getChanges();

					// newTimeKey = heapNodes[extractedVertex.getID()].getComparator() + edge.getDuration();//getValue(crite

					// increment changes when source = destination
					// if (newTimeKey < currentTimeKey)
					// 	newTimeKey = heapNodes[extractedVertex.getID()].getComparator() + edge.getDuration();//getValue(crite
					if (edge.getDestination().getName().equals(edge.getSource().getName()))
						newChangesKey++;
					System.out.println("		setting changes for.. " + heapNodes[destination].getVertex().getName() + " to " + newChangesKey);

					if(criterion.equals("time") && newTimeKey <= currentTimeKey)
					{
						System.out.println("ENDTER");
						heapNodes[destination].setTime(newTimeKey); 
						heapNodes[destination].setChanges(newChangesKey);
						decreaseKey(minHeap, newTimeKey, destination);
						String prePath = heapNodes[edge.getSource().getID()].getPath();
						heapNodes[destination].updatePath(prePath, edge, heapNodes[edge.getDestination().getID()], station2);
					}
					else if (criterion.equals("changes") && newChangesKey <= currentChangesKey)
					{
						heapNodes[destination].setTime(newTimeKey); 

						System.out.println("		node " + heapNodes[destination].getVertex().getName() + " time set to " + newTimeKey);
						System.out.println( " as above..." + heapNodes[destination].getTime() + " for " + heapNodes[destination].getVertex().getName());

						heapNodes[destination].setChanges(newChangesKey);
						System.out.println("		node " + heapNodes[destination].getVertex().getName() + " changes set to " + newChangesKey);
						decreaseKey(minHeap, newChangesKey, destination);
						String prePath = heapNodes[edge.getSource().getID()].getPath();
						heapNodes[destination].updatePath(prePath, edge, heapNodes[edge.getDestination().getID()], station2);
					}
				}
			}
		}
	}

		// TODO I think we can print the results more simply if we could just pull from the top of the priority queue?
		public String DijkstraResults(Node[] resultSet, String source, String destination)
		{
			// int current = Integer.MAX_VALUE_VALUE;// current ensures we only print one of the answers, considering there is an answer for each line of the destination station
			String minPath = "";// = "From " + source;
			// for (int i = 0; i < resultSet.length; i++)
			// {
			// 	// if (graph.getVertices().get(i).getName().equals(destination))// && resultSet[i].getComparator() < current)
			// 	if (resultSet[i].getVertex().getName().equals(destination) && resultSet[i].getComparator() < current)
			// 	{
			// 		current = resultSet[i].getComparator();
			// 		minPath +="\n\nINDEX ("+ i + ")\n"+resultSet[i].getPath();	// add travel information
			// 		minPath += "The total trip will take approximately " + resultSet[i].getTime() + // add travel statistics
			// 			" minutes and will have " + resultSet[i].getChanges() + " changes. \n\nFor graph size:" + graph.getVertices().size() + " and resultSet size: + " + resultSet.length;
			// 	}
			// }

			// ====================== PRINTS ALL RESULTS FOR ALL VERTICES =================================
			for (int i = 0; i < resultSet.length; i++)
			{
				if (resultSet[i].getVertex().getName().equals(destination))// && resultSet[i].getComparator() < current)
				{
					System.out.println("printing from " + resultSet[i].getVertex().getName());
					// current = resultSet[i].getComparator();
					minPath +="\n\n("+ i + ")"+resultSet[i].getPath();	// add travel information
					minPath += "The total trip will take approximately " + resultSet[i].getTime() + // add travel statistics
						" minutes and will have " + resultSet[i].getChanges() + " changes. \nFor graph size:" + graph.getVertices().size() + " and resultSet size: + " + resultSet.length;
				}
			}

			// ====================== PRINTS TOP OF RESULTSET WHICH IS USUALLY CORRECT =================================
			// minPath +="\n" + resultSet[graphSize-1].getPath();	// add travel information
			// minPath += "The total trip will take approximately " + resultSet[graphSize-1].getTime() + // add travel statistics
			// 	" minutes and will have " + resultSet[graphSize-1].getChanges() + " changes. \nFor graph size:" + graph.getVertices().size() + " and resultSet size: + " + resultSet.length;

			return minPath;
		}

		public void decreaseKey(MinHeap minHeap, int newTime, int vertex)
		{
			//get the index which duraction's needs a decrease
			// int index = minHeap.indexes[vertex];
			int index = minHeap.getIndex(vertex);

			//get the node and update its value from minheap
			//TODO HEAP NODE mh hasn't been created, since min heaap hasn't been made yet
			Node node = minHeap.getNode(index);

			// node.setTime(newTime);

			//function within minHeap
			minHeap.bubbleUp(index);
		}

	}
