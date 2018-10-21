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

    //don't think you really need anything in the constructor
    public DijkstraAlgorithm() 
    {
        
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
            //of the minheap
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extracedNode.vertex;
            SPT[extractedVertex] = true;

//TODO I'm not sure how it will traverse through different lines
//or even find the actual station it should be looking for

            //iterate through all the adjacent vertices
            for(int i = 0; i <graph.getEdges().size() ; i++)
            {
                int destination = graph.getEdges().get(i).getDuration();
                if(SPT[destination]==false)
                {
                    ///check if duration needs an update or not
                    //means check total weight from source to vertex_V is less than
                    //the current distance value, if yes then update the distance
                    int newKey = heapNodes[extracetedVertex].duration + graph.getEdges().get(i).getDuration();
                    int currentKey = heapNodes[destination].duration;

                    if(currentKey>newKey)
                    {
                        decreaseKey(minHeap, newKey, destination);
                        heapNodes[destination].duration = newKey;
                    }
                }
            }

            public void print()
            {
                System.out.println("Figure this out later");
            }
            
            public void decreaseKey(MinHeap minHeap, int newKey, int vertex)
            {
                //get the index which duraction's needs a decrease
                int index = minHeap.indexes[vertex];

                //get the node and update its value from minheap
//TODO HEAP NODE mh hasn't been created, since min heaap hasn't been made yet
                HeapNode node = minHeap.mh[index];
                node.duration = newKey;
                //function within minHeap
                minHeap.bubbleup(index);
            }

        }
    }

}