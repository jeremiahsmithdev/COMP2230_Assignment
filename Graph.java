/**
 * *
 *  * Graph.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 017/10/2018 
 *      * Description: Describes a graph using Stations as vertices and StationEdges as Edges
 *       */
import java.util.List;
import java.util.ArrayList;

public class Graph
{
	private final List<Station> vertices;
	private final List<StationEdge> edges;

	public Graph(List<Station> vertices, List<StationEdge> edges)
	{
		this.vertices = vertices;	// vertices passed as stationList from main
		this.edges = new ArrayList<StationEdge>();

		// add the respective StationEdges for each Station
		for (int i = 0; i < vertices.size(); i++)
		{
			for (int a = 0; a < vertices.get(i).getEdges().size(); i++)
			{
				edges.add(vertices.get(i).getEdges().get(1));
			}
		}
	}

	//getters
	public List<Station> findVertices(String station1)
	{
		Station destination = new Station();

		for(int i=0; i < vertices.size(); i++)
		{
			if(vertices.get(i).getName().equals(station1))
			{
				destination = vertices.get(i);
			}
		}
		return destination;
	}
	public List<Station> getVertices()
	{
		return vertices;
	}
	public List<StationEdge> getEdges()
	{
		return edges;
	}
}
