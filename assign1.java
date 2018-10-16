import java.util.List;
import java.util.ArrayList;
// These are the JAXP APIs used by DOMEcho:

// package dom;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
// These classes are for the exceptions that can be thrown when the XML document is parsed:

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;
// These classes read the sample XML file and manage output:

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
// Finally, import the W3C definitions for a DOM, DOM exceptions, entities and nodes:

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


class MyErrorHandler implements ErrorHandler { // private static

	private PrintWriter out;

	MyErrorHandler(PrintWriter out) {
		this.out = out;
	}

	private String getParseExceptionInfo(SAXParseException spe) {
		String systemId = spe.getSystemId();
		if (systemId == null) {
			systemId = "null";
		}

		String info = "URI=" + systemId + " Line=" + spe.getLineNumber() +
			": " + spe.getMessage();
		return info;
	}

	public void warning(SAXParseException spe) throws SAXException {
		out.println("Warning: " + getParseExceptionInfo(spe));
	}

	public void error(SAXParseException spe) throws SAXException {
		String message = "Error: " + getParseExceptionInfo(spe);
		throw new SAXException(message);
	}

	public void fatalError(SAXParseException spe) throws SAXException {
		String message = "Fatal Error: " + getParseExceptionInfo(spe);
		throw new SAXException(message);
	}
}

public class assign1
{
	public static void main(String args[]) throws Exception
	{
		File file = new File("RailNetwork.xml");
		System.out.println(file.canExecute());
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
			.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);

		NodeList stationList = document.getElementsByTagName("Station");
		System.out.println("Total of elements : " + stationList.getLength());
		int stations = stationList.getLength();

		NodeList stationNodes = document.getElementsByTagName("Station");
		List<Station> list = new ArrayList<Station>();
		for (int i = 0; i < stationNodes.getLength(); i++)
		{
			NodeList stationAttributes = stationNodes.item(i).getChildNodes();
			String Name = stationAttributes.item(1).getTextContent();
			String Line = stationAttributes.item(3).getTextContent();
			// System.out.println(stationAttributes.item(5).getNodeName());
			NodeList stationEdges = stationAttributes.item(5).getChildNodes();

			System.out.println(i);
			list.add(new Station(Name, Line, stationEdges));

		}

		// prints stored object data in order of XML file
		for (int i = 0; i < stationNodes.getLength(); i++)
		{
			System.out.println("Station Name: " +list.get(i).getName());
			System.out.println("Line: "+list.get(i).getLine());
			for (int a = 0; a < list.get(i).getEdges().size(); a++)
			{
				System.out.println("	Edge:");
				System.out.println("		Name: "+list.get(i).getEdges().get(a).getName());
				System.out.println("		Line: "+list.get(i).getEdges().get(a).getLine());
				System.out.println("		Duration: "+list.get(i).getEdges().get(a).getDuration());
			}
		}
	}
}
