import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm
{
    private List<Station> nodes;
    private List<StationEdge> edges;
    private Set<Station> visitedNodes;
    private Set<Station> notVisitedNodes;
    private Map<Station, Station> previousNode;
    private Map<Station, Integer> duration;


    private Graph graph;
    public DijkstraAlgorithm(Graph graph) 
    {
        this.nodes = new ArrayList<Station>(graph.getVertices());
        this.edges = new ArrayList<StationEdge>(graph.getEdges());
    }
    public void execute(Station source)
    {
        visitedNodes = new HashSet<Station>();
        notVisitedNodes = new HashSet<Station>();
        previousNode = new HashMap<Station, Station>();
        duration = new HashMap<Station, Integer>();
    }
}