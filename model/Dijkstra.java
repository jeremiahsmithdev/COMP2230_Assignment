import java.util.LinkedList;
public class Dijkstra
{

	private Graph graph;
	Dijkstra(Graph graph)
	{
		this.graph = graph;
	}
	public void GetMinDistances(int sourceVertex, int destinationVertex){
		int INFINITY = Integer.MAX_VALUE;
		boolean[] SPT = new boolean[graph.vertices];

		//          //create heapNode for all the vertices
		HeapNode [] heapNodes = new HeapNode[graph.vertices];
		for (int i = 0; i <graph.vertices ; i++) 
		{
			heapNodes[i] = new HeapNode();
			heapNodes[i].vertex = i;
			heapNodes[i].distance = INFINITY;
		}

		//decrease the distance for the first index
		heapNodes[sourceVertex].distance = 0;

		//add all the vertices to the MinHeap
		MinHeap minHeap = new MinHeap(graph.vertices);
		for (int i = 0; i <graph.vertices ; i++) 
		{
			minHeap.insert(heapNodes[i]);
		}
		//while minHeap is not empty
		while(!minHeap.isEmpty())
		{
			//extract the min
			HeapNode extractedNode = minHeap.extractMin();

			//extracted vertex
			int extractedVertex = extractedNode.vertex;
			SPT[extractedVertex] = true;

			//iterate through all the adjacent vertices
			LinkedList<Edge> list = graph.adjacencylist[extractedVertex];
			for (int i = 0; i <list.size() ; i++) 
			{
				Edge edge = list.get(i);
				int destination = edge.destination;
				//only if  destination vertex is not present in SPT
				if(SPT[destination]==false ) 
				{
					///check if distance needs an update or not
					//means check total weight from source to vertex_V is less than
					//the current distance value, if yes then update the distance
					int newKey =  heapNodes[extractedVertex].distance + edge.weight ;
					int currentKey = heapNodes[destination].distance;
					if(currentKey>newKey)
					{
						decreaseKey(minHeap, newKey, destination);
						heapNodes[destination].distance = newKey;
						// System.out.println("path updated for weight " + edge.weight +  " to destination " + destination);
						String prePath = "";
						prePath = heapNodes[edge.source].getPath();
						heapNodes[destination].updatePath(prePath, edge.source, edge.weight, destination);
					}
				}
			}
		}
		//print SPT
		printDijkstra(heapNodes, sourceVertex, destinationVertex);

        }

        public void decreaseKey(MinHeap minHeap, int newKey, int vertex)
        {

            //get the index which distance's needs a decrease;
            int index = minHeap.indexes[vertex];

            //get the node and update its value
            HeapNode node = minHeap.mH[index];
            node.distance = newKey;
            minHeap.bubbleUp(index);
        }

        public void printDijkstra(HeapNode[] resultSet, int sourceVertex, int destinationVertex)
	{
            System.out.println("Dijkstra Algorithm: (Adjacency List + Min Heap)");
            for (int i = 0; i <graph.vertices ; i++) 
            {
		    if (i == destinationVertex)
		    {
                System.out.println("Source Vertex: " + sourceVertex + " to vertex " +   + i +
                        " distance: " + resultSet[i].distance);
		System.out.println(resultSet[i].getPath());
		    }
            }
        }
}
