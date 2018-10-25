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
	private boolean visited;
	private String path;
	private Station vertex;
	private int time;
	private int changes;
	private boolean firstChange;
	private String criterion;

	//constructors
	public Node()
	{
		this.vertex = new Station();
		this.time = Integer.MAX_VALUE;
		this.changes = Integer.MAX_VALUE;
		criterion = "";
	}
	public Node(Station vertex, int time, String criterion)
	{
		this.vertex = vertex;
		this.time = Integer.MAX_VALUE;
		this.changes = Integer.MAX_VALUE;
		this.criterion = criterion;
		//saves the path from source to this current heapNode
		path = "";
		visited = false;
		firstChange = true;
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
	//returns comparator that is asked for
	public int getComparator()
	{
		if (criterion.equals("time"))
			return time;
		else
			return changes;
	}
	public int getSecondComparator()
	{
		if (criterion.equals("changes"))
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


	//this is the thing i need to fix
	public void updatePath(String prePath, StationEdge edge, Node node, String finalDestination)
	{
		path = prePath;
		// first line
		if (prePath.equals(""))		
		{
			path += "From " + edge.getSource().getName() + " take line " + edge.getLine() + " to station ";
		}


		// changes lines
		if (edge.getSource().getName().equals(edge.getDestination().getName()))
		{
			// changes++;
			// node.incrementChanges();
			path += edge.getSource().getName() + ";\nthen change to line " + edge.getDestination().getLine() + ", and continue to station ";
		}

		// last station
		if (edge.getDestination().getName().equals(finalDestination))
			path += vertex.getName() + ";\n";
	}

	// controls sorting of nodes in the heap by comparing which path is better based on criteria
	public int compareTo(Node node)
	{
		// System.out.println("comparing " + vertex.getName() + " and " + node.getVertex().getName());
		if (getComparator() > node.getComparator())
			return 1;
		else if (getComparator() == node.getComparator())
		{
			if (getSecondComparator() > node.getSecondComparator())
				return 1;
			else if (getSecondComparator() < node.getSecondComparator())
				return -1;
			return 0;
		}
		else if (getComparator() < node.getComparator())
			return -1;
		return 0;
	}
}
