/**
 * *
 *  * Station.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 cXXXXXXX
 *     * @version: 016/10/2018
 *      * Description: Holds information to create and store a Station object
 *       */
import java.util.ArrayList;

import org.w3c.dom.NodeList;

public class Station
{
	private String Name;
	private String Line;
	private ArrayList<StationEdge> Edges;

	public Station(String Name, String Line, NodeList edges)
	{
		this.Name = Name;
		this.Line = Line;
		this.Edges = new ArrayList<StationEdge>();
		for (int i = 1; i < edges.getLength(); i = i + 2)
		{
			System.out.println(i);
			NodeList edgeAttributes = edges.item(i).getChildNodes();
			String name = edgeAttributes.item(1).getTextContent();
			System.out.println(name);
			String line = edgeAttributes.item(3).getTextContent();
			System.out.println(line);
			int duration = Integer.parseInt(edgeAttributes.item(5).getTextContent());
			System.out.println("duration = "+edgeAttributes.item(5).getNodeName());
			Edges.add(new StationEdge(name, line, duration));
		}
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
