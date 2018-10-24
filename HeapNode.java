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
		this.changes++;
	}

	//this is the thing i need to fix
	public void updatePath(String prePath, StationEdge edge, HeapNode node)
	{
		// path reset for update when old path has become redundant due to a newer and quicker route
		if (visited == true)
			path = "";
		// first line
		if (prePath.equals(""))		
			path += ", take line " + edge.getLine() + " to station " + edge.getDestination().getName() + ";\n";
//NEW CHANGE HERE			
		//changing lines but in same station aka same station, differen line
		else if(edge.getSource().getName().equals(edge.getDestination().getName()) && !edge.getSource().getLine().equals(edge.getDestination().getLine()))
		{
//			//testing
//			changes++;
//			path += prePath + " then change to line " + edge.getDestination().getLine() + ", and continue to " + edge.getDestination().getName() + edge.getSource().getLine()+"<-is source line, is destination line->"+edge.getDestination().getLine()+";\n";

			node.upChanges();
			path += prePath + "then change to line " + edge.getDestination().getLine() + ", at " + edge.getDestination().getName() + " station"+ ";\n";
		}
		// changing lines
		else if (edge.getSource().getName().equals(edge.getDestination().getName()))
		{
//			//testing
//			changes++;
//			path += prePath + " then change to line " + edge.getDestination().getLine() + ", and continue to " + edge.getDestination().getName() + edge.getSource().getLine()+"<-is source line, is destination line->"+edge.getDestination().getLine()+";\n";

			node.upChanges();
			path += prePath + " then change to line " + edge.getDestination().getLine() + ", and continue to " + edge.getDestination().getName() + ";\n";
		}
		// Stay on current line
		else
		{
			path += prePath + "stay on line " + edge.getSource().getLine() + ", and continue to " + edge.getDestination().getName() + ";\n";
		}

		visited = true;
	}

	// calculate best route between two stations according to optimisationCriteria
//	public void bestRoute(String stationOne, String stationTwo, String optimisationCriteria)
//	{
//		// OUTPUTS AS FOLLOWS:
//		// From X, take line a to station Z;
//		// then change to line b, and continue to W;
//		// ...
//		// then change to line c, and continue to Y.
//		// The total trip will have m changes and will take approximately n minutes.
//	}
	// optimisationCriteria can be either 'time' or 'changes' i.e. optimize for the least of whichever is chosen on program initiation
	// if there are multiple optimal results satisfying the chosen criterion, then output the one that optimises the other criterion
}
