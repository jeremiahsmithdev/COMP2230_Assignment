/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: Parses XML file and saves data as Station objects and associated StationEdge objects
 *       */
import java.io.File;

import java.util.List;
import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
// TODO THE RAIL NETWORK SHOULD BE PASSED TO YOUR PROGRAM AS AN INPUT
// BUGS: using testNetwork, output is currently incorrect when going from Station "Five" to Station "One"
// Q1
// Q2 (extra)

// EXAMPLE USE OF PROGRAM:
// java assign1 RailNetwork.XML X Y optimisationCriteria
// where X and Y are the names of two stations and optimisationCriteria is the additional criteria that the algorithm must be optomised for

// main class
public class assign1
{
	private int one;
	public static void main(String args[]) throws Exception
	{
		// String RailNetwork = args[0];	// the XML file will be given as a command line argument and savede to this variable
		// String stationOne args[1];		// commented out for now while we do manual testing
		// String stationTwo args[2];
		// String optimisationCriteria args[3];
		//
		// XML FILE PARSING BEGIN
		String RailNetwork = "RailNetwork.xml";	// "RailNetwork.xml"
		File file = new File(RailNetwork);	// this will be changed so that the rail network is passed to program as an input
		// File file = new File(RailNetwork);		// done as above, commented out for easier testing
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory         // instantiates an instance of this library for parsing
			.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);                               // this is the important part, 'document' is our XML file

		NodeList stationList = document.getElementsByTagName("Station");               // splits document into nodes by Station tag

		NodeList stationNodes = document.getElementsByTagName("Station");              // splits stations into nodes for each tag
		List<Station> stations = new ArrayList<Station>();                                 // this will hold all of our Station objects
		for (int i = 0; i < stationNodes.getLength(); i++)                             // we parse the file and create Station objects within this loop
		{
			NodeList stationAttributes = stationNodes.item(i).getChildNodes();     // splits this stationNode into its child elements
			String Name = stationAttributes.item(1).getTextContent();              // station Name recorded
			String Line = stationAttributes.item(3).getTextContent();              // station Line recorded
			NodeList stationEdges = stationAttributes.item(5).getChildNodes();     // edges element split into child nodes for each edge

			stations.add(new Station(Name, Line, stationEdges, i));                       // recorded data is sent to the Station constructor
		}

		for (int i = 0; i < stationNodes.getLength(); i++)                             // we parse the file and create Station objects within this loop
		{
			stations.get(i).initializeEdges(stations);
		}


		// XML FILE PARSING END

		// prints stored object data in order of XML file
		// this is not used, just a demonstration that the information has been stored correctly
	// printData(stations);
		// TODO - Create graph:
		Graph graph = new Graph(stations);

		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
<<<<<<< HEAD
		String result = dijkstra.getMinPath("Bondi Junction", "Clyde");
=======
		String result = dijkstra.getMinPath("Lidcombe", "Erskineville");
>>>>>>> ca3fd5333459786400161945d6c253aea419eed4
		if (result != "")
			System.out.println(result);
		else
			System.out.println("There is no path");

		    // graph.dijkstra_GetMinDistances(sourceVertex);

		// TODO - calculate best route using graph and bestRoute()
		// program calculates and returns information about the bestRoute(stationOne, stationTwo, optimisationCriteria)
		// using the two stations provided as command line arguments

//		DijkstraAlgorithm dij = new DijkstraAlgorithm(graph, station1, station2);
	}
	// RULES
	// algorithm uses average time for travelling between adjacent stations (duration)
	// and a flat time of 15 minutes to change from one line to another

	// calculate best route between two stations according to optimisationCriteria
//	public void bestRoute(String stationOne, String stationTwo, String optimisationCriteria)
//	{
//		// OUTPUTS AS FOLLOWS:
//		// From X, take line a to station Z;
//		// then change to line b, and continue to W;
//		// ...
//		// then change to line c, and continue to Y.
//		// The total trip will have m changes and will take approximately n minutes.
//	}
	// optimisationCriteria can be either 'time' or 'changes' i.e. optimize for the least of whichever is chosen on program initiation
	// if there are multiple optimal results satisfying the chosen criterion, then output the one that optimises the other criterion

	public static void printData(List<Station> stations)
	{
		for (int i = 0; i < stations.size(); i++)
		{
			System.out.println("Station Name: " +stations.get(i).getName());
			System.out.println("Line: "+stations.get(i).getLine());
			for (int a = 0; a < stations.get(i).getEdges().size(); a++)
			{
				System.out.println("	Edge:");
				System.out.println("		Name: "+stations.get(i).getEdges().get(a).getName());
				System.out.println("		Line: "+stations.get(i).getEdges().get(a).getLine());
				System.out.println("		Duration: "+stations.get(i).getEdges().get(a).getDuration());
				System.out.println("		Source: "+stations.get(i).getEdges().get(a).getSource().getName());
				System.out.println("		Destination: "+stations.get(i).getEdges().get(a).getDestination().getName());
			}
		}
	}
	/**
	 * @return the one
	 */
	public int getOne() {
		return one;
	}
}
