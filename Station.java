/**
 * *
 *  * Station.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 cXXXXXXX
 *     * @version: 016/10/2018
 *      * Description: Holds information to create and store a Station object
 *       */
// TODO modify according to http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NodeList;

import org.w3c.dom.NodeList;

public class Station
{
	private String Name;
	//Since with xml, the same station different line is counted
	//as a different station
	private String Line;
	private ArrayList<StationEdge> Edges;
	private NodeList xmlEdges;

	public Station(String Name, String Line, NodeList xmlEdges)
	{
		this.Name = Name;
		this.Line = Line;
		this.Edges = new ArrayList<StationEdge>();
		this.xmlEdges = xmlEdges;
	}

	public void initializeEdges(List<Station> stations)
	{
		for (int i = 1; i < xmlEdges.getLength(); i = i + 2)
		{
			System.out.println(i);
			NodeList edgeAttributes = xmlEdges.item(i).getChildNodes();
			String name = edgeAttributes.item(1).getTextContent();
			System.out.println(name);
			String line = edgeAttributes.item(3).getTextContent();
			System.out.println(line);
			int duration = Integer.parseInt(edgeAttributes.item(5).getTextContent());
			System.out.println("duration = "+edgeAttributes.item(5).getNodeName());
<<<<<<< HEAD
			Edges.add(new StationEdge(name, line, duration)); //, source, destination));
=======
			Edges.add(new StationEdge(name, line, duration, this, findDestination(name, line, stations)));
>>>>>>> 63ecd309a35fcde3ada7a907a38ba47a5b9a32a1
		}
	}
	public Station findDestination(String name, String line, List<Station> stations)
	{


	}
	public ArrayList<StationEdge> getEdges()
	{
		return Edges;
	}

	public String getName()
	{
		return Name;
	}
	public String getLine()
	{
		return Line;
	}
}
