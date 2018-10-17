/**
 * *
 *  * Graph.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 cXXXXXXX
 *     * @version: 017/10/2018
 *      * Description: Describes a graph using Stations as vertices and StationEdges as Edges
 *       */

// TODO NOTE: See resource - http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
import java.util.List;

public class Graph
{
	private final List<Station> vertices;
	private final List<StationEdge> edges;

	public Graph(List<Station> vertices, List<StationEdge> edges)
	{
		this.vertices = vertices;
		this.edges = edges;
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
