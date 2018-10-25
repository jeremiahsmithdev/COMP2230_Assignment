// import com.sun.glass.ui.SystemClipboard;

/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: node used in the minHeap
 *       */
public class Node implements Comparable<Node>
{
	//variables
	private boolean visited;
	private String path;
	private Station vertex;
	private int time;
	private int changes;
	private String criterion;

	//constructors
	public Node()											//default constructor
	{
		this.vertex = new Station();
		this.time = Integer.MAX_VALUE;
		this.changes = Integer.MAX_VALUE;
		criterion = "";
	}
	public Node(Station vertex, int time, String criterion)	//node instantiated with the vertex, time 
	{														//and criterion(time/changes)
		this.vertex = vertex;
		this.time = Integer.MAX_VALUE;
		this.changes = Integer.MAX_VALUE;
		this.criterion = criterion;
		path = "";											//saves the path from source to this current Node
		visited = false;									//checks if node has been visited
	}

	//functions
	public void updatePath(String prePath, StationEdge edge, Node node, String finalDestination)
	{
		//updates paths, concatenates the neccesary print out to path which is saved at the node
		//so that the code tester can visually see the results better

		path = prePath;
		//adds first line
		if (prePath.equals(""))		
		{
			path += "From " + edge.getSource().getName() + " take line " + edge.getLine() + " to station ";
		}

		//adds changes lines
		if (edge.getSource().getName().equals(edge.getDestination().getName()))
		{
			path += edge.getSource().getName() + ";\nthen change to line " + edge.getDestination().getLine() + ", and continue to station ";
		}

		//adds last station
		if (edge.getDestination().getName().equals(finalDestination))
		{
			path += vertex.getName() + ";\n";
		}
	}

	//controls sorting of nodes in the heap by comparing which path is better based on criteria
	public int compareTo(Node node)
	{
		if (getComparator() > node.getComparator())
		{
			return 1;
		}
		else if (getComparator() == node.getComparator())
		{
			if (getSecondComparator() > node.getSecondComparator())
			{
				return 1;
			}
			else if (getSecondComparator() < node.getSecondComparator())
			{
				return -1;
			}

			return 0;
		}
		else if (getComparator() < node.getComparator())
		{
			return -1;
		}

		return 0;
	}

	//setters
	public void setVertex(Station vertex)
	{
		this.vertex = vertex;
	}
	public void setTime(int time)
	{
		this.time = time;
	}
	public void setChanges(int changes)
	{
		this.changes = changes;
	}

	//getters
	public Station getVertex()
	{
		return vertex;
	}
	public String getPath()
	{
		return path;
	}
	public int getComparator()				//returns main comparision variable used for dijkstras Algorithm
	{										//used to calculate the minimum time or changes path
		if (criterion.equals("time"))
			return time;
		else
			return changes;
	}
	public int getSecondComparator()		//returns 2nd comparision variable used for dijkstras Algorithm
	{										//used to calculate prefered path, if main comparator value
		if (criterion.equals("changes"))	//between verticies is the same
			return time;
		else
			return changes;
	}
	public int getTime()
	{
		return time;
	}
	public int getChanges()
	{
		return changes;
	}
}
