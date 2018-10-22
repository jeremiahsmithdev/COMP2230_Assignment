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
	private int time = Integer.MAX_VALUE;		// very unstable, needs to be properly set with a constructor in the MinHeap class
	//constructor
	public HeapNode()
	{
		this.vertex = new Station();
		this.time = Integer.MAX_VALUE;
	}
	public HeapNode(Station vertex, int time)
	{
		this.vertex = vertex;
		this.time = Integer.MAX_VALUE;
		path = "";
		visited = false;
	}

	//setters
	public void setVertex(Station vertex)
	{
		this.vertex = vertex;
	}
	public void setWeight(int time)
	{
		this.time = time;
	}
	//getters
	public Station getVertex()
	{
		return vertex;
	}
	public int getWeight()
	{
		return time;
	}

	public String getPath()
	{
		return path;
	}

	public void updatePath(String prePath, String source, int edgeWeight, String destination, HeapNode node)
	{
		if (visited == true)
			path = "";		// path reset for update when old path has become redundant due to a newer and quicker route
		path += prePath + "departed from (" + source + ") and travelled for " + edgeWeight + " and arrived at (" + destination + ") current total is: " + node.getWeight()+" for node " + node.getVertex().getID()+" : " + time + " " + vertex.getID()+"\n";
		visited = true;
	}
}
