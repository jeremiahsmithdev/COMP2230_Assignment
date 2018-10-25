/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: Parses XML file and saves data as Station objects and associated StationEdge objects
 *       */
import java.io.File;
import java.util.*;

import java.util.List;
import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;

// java assign1 RailNetwork.XML X Y criterion

public class assign1
{
	private static String RailNetwork = "test.xml";
	private static String stationOne = "One";
	private static String stationTwo = "Four";
	private static String criterion = "changes";


	public static void main(String args[]) throws Exception
	{
		RailNetwork = "RailNetwork.xml";
		stationOne = "Bondi Junction";
		stationTwo = "Clyde";
		criterion = "time";
		if (args.length == 4)//TODO NEEDS TESTING
		{
			RailNetwork = args[0]; // the XML file will be given as a command line argument and savede to this variable
			stationOne = args[1];  // commented out for now while we do manual testing
			stationTwo = args[2];
			criterion = args[3];
		}
		if (criterion.equals("time") == false && criterion.equals("changes") == false)
		{
			System.out.println("Please select either 'time' or changes' as your optimisation criteria");
			return;
		}
		File file = new File(RailNetwork);                                             // this will be changed so that the rail network is passed to program as an input
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory         // instantiates an instance of this library for parsing
			.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);                               // this is the important part, 'document' is our XML file
		NodeList stationList = document.getElementsByTagName("Station");               // splits document into nodes by Station tag
		NodeList stationNodes = document.getElementsByTagName("Station");              // splits stations into nodes for each tag
		List<Station> stations = new ArrayList<Station>();                             // this will hold all of our Station objects
		for (int i = 0; i < stationNodes.getLength(); i++)                             // we parse the file and create Station objects within this loop
		{
			NodeList stationAttributes = stationNodes.item(i).getChildNodes();     // splits this stationNode into its child elements
			String Name = stationAttributes.item(1).getTextContent();              // station Name recorded
			String Line = stationAttributes.item(3).getTextContent();              // station Line recorded
			NodeList stationEdges = stationAttributes.item(5).getChildNodes();     // edges element split into child nodes for each edge
			stations.add(new Station(Name, Line, stationEdges, i));                // recorded data is sent to the Station constructor
		}
		for (int i = 0; i < stationNodes.getLength(); i++)                             // we parse the file and create Station objects within this loop
		{
			stations.get(i).initializeEdges(stations);
		}
		Graph graph = new Graph(stations);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		String result = dijkstra.getMinPath(stationOne, stationTwo, criterion);
		if (result != "")
			System.out.println(result);
		else
			System.out.println("There is no path");
	}
}
