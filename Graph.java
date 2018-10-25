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
	//variables
	private final List<Station> vertices;
	private final List<StationEdge> edges;

	//constructor
	public Graph(List<Station> vertices)
	{
		// vertices passed as stationList from main
		this.vertices = vertices;	
		this.edges = new ArrayList<StationEdge>();

		// add the respective StationEdges for each Station
		for (int i = 0; i < vertices.size(); i++)
		{
			for (int a = 0; a < vertices.get(i).getEdges().size(); a++)
			{
				edges.add(vertices.get(i).getEdges().get(a));
			}
		}
	}

	//functions
	public Station findVertices(String station1)	//recieves a station name via string searches through the list of stations
	{												//and returns the verticies with the same name as the string
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

	//getters
	public List<Station> getVertices()
	{
		return vertices;
	}
	public List<StationEdge> getEdges()
	{
		return edges;
	}
}
