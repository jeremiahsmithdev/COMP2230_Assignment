import DijkstraUsingMinHeap.MinHeap;

/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: DijkstraAlgorithm that goes through the graph to produce a minheap of results
 *       */

//TODO MinHeap must be constructed since this algorithm runs on saving values into
//the min heap, also dijkstra algorithm still not finished
//TODO test if the values are readying through properly, just typing stuff up
//so that i have a solid understanding of what the classes should be doing
public class DijkstraAlgorithm
{
    private Graph graph;
    private String station1,station2;

    public DijkstraAlgorithm(Graph graph, String station1, String station2) 
    {
        this.graph = graph;
        this.station1 = station1;
        this.station2 = station2;

        getMinDuration(graph, station1,station2);
    }

    public void getMinDuration(Graph graph, String station1, String station2)
    {
        int INFINITY = Integer.MAX_VALUE;
        boolean[] SPT = new boolean[graph.getVertices().size()];

        //Generate the heapnodes with the amount of vertices
        //all of the distances are set to inifinity initially;
        HeapNode [] heapNodes = new HeapNode[graph.getVertices().size()];
        for(int i = 0; i < graph.getVertices().size(); i++)
        {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = graph.getVertices().get(i);
            heapNodes[i].duration = INFINITY;
        }

        //except for the initial node which is set to 0
        heapNodes[graph.findVertices(station1)].duration = 0;

        //add all the vertices to MinHeap
        MinHeap minHeap = new MinHeap(graph.getVertices().size());
        for(int i = 0; i < graph.getVertices().size(); i++)
        {
            minHeap.insert(heapNodes[i]);
        }
//TODO not sure exactly how the minheap traverses
        //while minHeap is not empty
        while(!minHeap.isEmpty())
        {
            //extract the min
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extracedNode.vertex;
            SPT[extractedVertex] = true;

            //iterate through all the adjacent vertices

        }
    }

}

public class HeapNode
{
    int vertex;
    int duration;
}