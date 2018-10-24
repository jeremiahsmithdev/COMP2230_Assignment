/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: Saves values from the DijkstraAlgorithm in a min heap
 *       */
public class MinHeap
{
    private int capacity;
    private int currentSize;
    private HeapNode[] node;
    private int [] indexes; // used to decrease the duration
    private String optimisationCriteria;

    public MinHeap(int capacity, String optimisationCriteria) 
    {
        this.capacity = capacity;
	this.optimisationCriteria = optimisationCriteria;
        node = new HeapNode[capacity + 1];
        indexes = new int[capacity];
        // node[0] = new HeapNode();
        // node[0].setWeight(Integer.MIN_VALUE);		// ?????????
        // node[0].setVertex(new Station());
        currentSize = 0;
    }

    public int getIndex(int index)
    {
	    return indexes[index];
    }

    public HeapNode getHeapNode(int index)
    {
	    return node[index];
    }

    //displays the minHeap, will need modification
    public void display() 
    {
        for (int i = 0; i < currentSize; i++) 
        {
            System.out.println(" " + node[i].getVertex().getName() + "   total   " + node[i].getComparator(optimisationCriteria));
        }
	System.out.println();
    }
    public int size()
    {
	    return currentSize;
    }
    //inserts nodes/stations into the minHeap
    public void insert(HeapNode x) 
    {
	    // if (currentSize == 0)
		//     node[0] = x;
        int idx = currentSize;
        node[idx] = x;
        indexes[x.getVertex().getID()] = idx;
        bubbleUp(idx);

	currentSize++;
	    // System.out.println(node[252].getComparator(optimisationCriteria));
    }

    //pushes value up to where it belongs
    public void bubbleUp(int pos) 
    {
        int parentIdx = pos/2;
        int currentIdx = pos;

	    // System.out.println(node[parentIdx].getVertex().getName() + " has " +node[252].getComparator(optimisationCriteria));

	    // System.out.println(node[parentIdx].getComparator(optimisationCriteria));
	    // System.out.println(node[currentIdx].getComparator(optimisationCriteria));
	    // System.out.println(node[parentIdx].getVertex().getName());
	    // System.out.println(node[currentIdx].getVertex().getName());

        while (currentIdx > 0 && node[parentIdx].getComparator(optimisationCriteria) > node[currentIdx].getComparator(optimisationCriteria)) 
        {
            HeapNode currentNode = node[currentIdx];
            HeapNode parentNode = node[parentIdx];


            //swap the positions
            indexes[currentNode.getVertex().getID()] = parentIdx;
            indexes[parentNode.getVertex().getID()] = currentIdx;
            swap(currentIdx,parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx/2;
        }
    }

    //returns the smallest Node
    public HeapNode extractMin() 
    {
        HeapNode min = node[0];
        HeapNode lastNode = node[currentSize-1];
//            update the indexes[] and move the last node to the top
        indexes[lastNode.getVertex().getID()] = 0;
        node[0] = lastNode;
        node[currentSize] = new HeapNode();//null;
        sinkDown(0);
        currentSize--;

        return min;
    }

    //basically bubble down
    public void sinkDown(int k) 
    {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k+1;
        if (leftChildIdx < heapSize() && node[smallest].getComparator(optimisationCriteria) > node[leftChildIdx].getComparator(optimisationCriteria)) 
        {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && node[smallest].getComparator(optimisationCriteria) > node[rightChildIdx].getComparator(optimisationCriteria)) 
        {
            smallest = rightChildIdx;
        }
        if (smallest != k) 
        {

            HeapNode smallestNode = node[smallest];
            HeapNode kNode = node[k];

            //swap the positions
            indexes[smallestNode.getVertex().getID()] = k;
            indexes[kNode.getVertex().getID()] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }
    //swaps 2 positions
    public void swap(int a, int b) 
    {
        HeapNode temp = node[a];
        node[a] = node[b];
        node[b] = temp;
    }
    //checks if empty through the size
    public boolean isEmpty() 
    {
        return currentSize == 0;
    }
    //getters
    public int heapSize()
    {
        return currentSize;
    }

}
