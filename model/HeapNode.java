public class HeapNode
{
	public int vertex;
	public int distance;
	public String path;
	public boolean visited;

	HeapNode()
	{
		path = "";
		visited = false;
	}


	public String getPath()
	{
		return path;
	}

	public void updatePath(String prePath, int source, int edgeWeight, int destination)
	{
		if (visited == true)
			path = "";	// path reset for update
		path += prePath + "departed from (" + source + ") and travelled for " + edgeWeight + " and arrived at (" + destination + ")\n";
		visited = true;
	}
}
