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
	private String Line;
	private ArrayList<StationEdge> Edges;
	private NodeList xmlEdges;

	public Station()
	{

	}

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
			Edges.add(new StationEdge(name, line, duration, this , findDestination(name, line, stations)));
		}
	}
	public Station findDestination(String name, String line, List<Station> stations)
	{
		Station destination = new Station();
		for(int i=0; i < stations.size(); i++)
		{
			if(stations.get(i).getName()==name &&stations.get(i).getLine()==line)
			{
				destination = stations.get(i);
			}
		}
		return destination;

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
