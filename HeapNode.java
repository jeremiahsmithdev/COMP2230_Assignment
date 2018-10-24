// import com.sun.glass.ui.SystemClipboard;

/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: node used in the minHeap
 *       */
public class HeapNode
{
	private boolean visited;
	private String path;
	private Station vertex;
	private int time;
	private int changes;
	private boolean firstChange;

	//constructors
	public HeapNode()
	{
		this.vertex = new Station();
		this.time = Integer.MAX_VALUE;
	}
	public HeapNode(Station vertex, int time)
	{
		this.vertex = vertex;
		this.time = Integer.MAX_VALUE;
		this.changes = 0;
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
	public void setComparator(int time)//, int changes)
	{
			this.time = time;
			// this.changes = changes;
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
	public int getComparator(String criteria)
	{
		if (criteria.equals("time"))
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

	public void upChanges()
	{
		changes++;
	}

	//this is the thing i need to fix
	public void updatePath(String prePath, StationEdge edge, HeapNode node, String finalDestination)
	{
		path = prePath;
		// first line
		if (prePath.equals(""))		
		{
			path = "From " + edge.getSource().getName() + " take line " + edge.getLine() + " to station ";
		}


		// changes lines
		if (edge.getSource().getName().equals(edge.getDestination().getName()))
		{
			// changes++;
			node.upChanges();
			path += edge.getSource().getName() + ";\nthen change to line " + edge.getDestination().getLine() + ", and continue to station ";
		}

		// last station
		if (edge.getDestination().getName().equals(finalDestination))
			path += vertex.getName() + ";\n";
	}
}
