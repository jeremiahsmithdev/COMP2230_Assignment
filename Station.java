/**
 * *
 *  * Station.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: Holds information to create and store a Station object
 *       */
//Libraries
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NodeList;

public class Station
{
	//variables
	private String Name;
	private String Line;
	private ArrayList<StationEdge> Edges;
	private NodeList xmlEdges;
	private int ID;

	//constructor(s)
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

	//functions
	public void initializeEdges(List<Station> stations)						//saves the edges(Train lines) into the station object
	{																		//gets the attributes, name, line, duration of the edge
																			//and saves them into the station through a forloop
		for (int i = 1; i < xmlEdges.getLength(); i = i + 2)
		{
			NodeList edgeAttributes = xmlEdges.item(i).getChildNodes();
			String name = edgeAttributes.item(1).getTextContent();
			String line = edgeAttributes.item(3).getTextContent();
			int duration = Integer.parseInt(edgeAttributes.item(5).getTextContent());
			Edges.add(new StationEdge(name, line, duration, this , findDestination(name, line, stations)));
			//BugTesting
//			System.out.println(i);
//			System.out.println(name);
//			System.out.println(line);
//			System.out.println("duration = "+duration);
		}
	}
	
	public Station findDestination(String name, String line, List<Station> stations)		
	{																//Based off the station name and line it is associated with
		Station destination = new Station(); 						//returns the location of the vertice/station that you are looking
																	//for in the list of stations
		for(int i=0; i < stations.size(); i++)
		{
			//if station name and line is the same set that station as destination
			if(stations.get(i).getName().equals(name) && stations.get(i).getLine().equals(line))
			{
				destination = stations.get(i);
			}
		}
		return destination;

	}

	//setters
	public void setID(int ID)
	{
		this.ID = ID;
	}

	//getters
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
}
