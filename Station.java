/**
 * *
 *  * Station.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: Holds information to create and store a Station object
 *       */
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NodeList;

public class Station
{
	private String Name;
	private String Line;
	private ArrayList<StationEdge> Edges;
	private NodeList xmlEdges;
	private int ID;

	public Station()
	{}

	public Station(String Name, String Line, NodeList xmlEdges, int ID)
	{
		this.Name = Name;
		this.Line = Line;
		this.Edges = new ArrayList<StationEdge>();
		this.xmlEdges = xmlEdges;
		this.ID = ID;
	}

	public void initializeEdges(List<Station> stations)
	{
		for (int i = 1; i < xmlEdges.getLength(); i = i + 2)
		{
//			System.out.println(i);
			NodeList edgeAttributes = xmlEdges.item(i).getChildNodes();
			String name = edgeAttributes.item(1).getTextContent();
//			System.out.println(name);
			String line = edgeAttributes.item(3).getTextContent();
//			System.out.println(line);
			int duration = Integer.parseInt(edgeAttributes.item(5).getTextContent());
//			System.out.println("duration = "+duration);
			Edges.add(new StationEdge(name, line, duration, this , findDestination(name, line, stations)));
		}
	}
	public Station findDestination(String name, String line, List<Station> stations)
	{
		Station destination = new Station(); for(int i=0; i < stations.size(); i++)
		{
			if(stations.get(i).getName().equals(name) && stations.get(i).getLine().equals(line))
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

	public int getID()
	{
		return ID;
	}

	public void setID(int ID)
	{
		this.ID = ID;
	}
}
