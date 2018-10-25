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

// TODO * input from command line,*  changes optimisation
// BUGS: using testNetwork, output is currently incorrect when going from Station "Five" to Station "One"

// EXAMPLE USE OF PROGRAM:
// java assign1 RailNetwork.XML X Y optimisationCriteria

public class assign1
{
	private static String RailNetwork = "RailNetwork.xml";
	private static String stationOne = "Glenfield";
	private static String stationTwo = "Carlingford";
	private static String optimisationCriteria = "time";

	public static void main(String args[]) throws Exception
	{
		if (args.length == 4)
		{
			String RailNetwork = args[0]; // the XML file will be given as a command line argument and savede to this variable
			String stationOne = args[1];  // commented out for now while we do manual testing
			String stationTwo = args[2];
			String optimisationCriteria = args[3];
		}

		                                                                               // XML FILE PARSING BEGIN
		String RailNetwork = "RailNetwork.xml";                                        // "RailNetwork.xml"
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

		String result = dijkstra.getMinPath(stationOne, stationTwo, optimisationCriteria);

		//NEW CHANGE HERE
		//		//using for testing purposes
		//		Scanner scanner = new Scanner(System.in);
		//		System.out.println("Please enter the criteria: ");
		//		String crit = scanner.nextLine();
		//		System.out.println("Please enter the first station: ");
		//		String stat1 = scanner.nextLine();
		//		System.out.println("Please enter the second station: ");
		//		String stat2 = scanner.nextLine();
		//		String result = dijkstra.getMinPath(stat1, stat2, crit);

		if (result != "")
			System.out.println(result);
		else
			System.out.println("There is no path");
	}
}
