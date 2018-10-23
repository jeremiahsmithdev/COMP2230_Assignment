import java.util.LinkedList;
//class Graph and algorithm combined
public class Graph 
{
	//graph generation starts here
	int vertices;
	LinkedList<Edge>[] adjacencylist;

	Graph(int vertices)
	{
		this.vertices = vertices;
		adjacencylist = new LinkedList[vertices];
		//initialize adjacency lists for all the vertices
		for (int i = 0; i <vertices ; i++) 
		{
			adjacencylist[i] = new LinkedList<>();
		}
	}

	public void addEdge(int source, int destination, int weight) 
	{
		Edge edge = new Edge(source, destination, weight);
		adjacencylist[source].addFirst(edge);

		edge = new Edge(destination, source, weight);
		adjacencylist[destination].addFirst(edge); //for undirected graph
	}

	public LinkedList<Edge>[] adjacencylist()
	{
		return adjacencylist;
	}

	//dijkstar algorithm starts here
    }
