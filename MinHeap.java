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
    //variables
    private int capacity;
    private int currentSize;
    private Node[] node;
    private int [] indexes; //used to decrease the duration
    private String optimisationCriteria;

    //constructor
    public MinHeap(int capacity, String optimisationCriteria) 
    {
        this.capacity = capacity;
	    this.optimisationCriteria = optimisationCriteria;
        node = new Node[capacity + 1];
        indexes = new int[capacity];
        currentSize = 0;
    }


    //inserts nodes/stations into the minHeap
    public void insert(Node x) 
    {
        int idx = currentSize;
        node[idx] = x;
        indexes[x.getVertex().getID()] = idx;
        bubbleUp(idx);

    	currentSize++;
    }

    //pushes value up to where it belongs
    public void bubbleUp(int pos) 
    {
        int parentIdx = pos/2;
        int currentIdx = pos;

        while (currentIdx > 0 && currentIdx < node.length && node[parentIdx].compareTo(node[currentIdx]) > 0)//node[parentIdx].getComparator() > node[currentIdx].getComparator()) 
        {
            Node currentNode = node[currentIdx];
            Node parentNode = node[parentIdx];


            //swap the positions
            indexes[currentNode.getVertex().getID()] = parentIdx;
            indexes[parentNode.getVertex().getID()] = currentIdx;
            swap(currentIdx,parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx/2;
        }
    }

    //basically bubble down
    public void sinkDown(int k) 
    {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k+1;

        if (leftChildIdx >  0 && leftChildIdx < node.length && node[smallest].compareTo(node[leftChildIdx]) > 0)
        {
            smallest = leftChildIdx;
        }
        if (rightChildIdx > 0 && rightChildIdx < node.length && node[smallest].compareTo(node[rightChildIdx]) > 0)
        {
            smallest = rightChildIdx;
        }
        if (smallest != k) 
        {

            Node smallestNode = node[smallest];
            Node kNode = node[k];

            //swap the positions
            indexes[smallestNode.getVertex().getID()] = k;
            indexes[kNode.getVertex().getID()] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    //function used to compare two nodes values for minheap manipulation 
    public boolean compareTo(Node parent, Node current)
    {
	    if (current.getComparator() < parent.getComparator())
		    return true;
	    else if (current.getComparator() == parent.getComparator())
		    if (current.getChanges() < parent.getChanges())
			    return true;
	    return false;
    }

    //returns the smallest Node
    public Node extractMin() 
    {
        Node min = node[0];
        Node lastNode = node[currentSize-1];
//            update the indexes[] and move the last node to the top
        indexes[lastNode.getVertex().getID()] = 0;
        node[0] = lastNode;
        node[currentSize] = new Node();//null;
        sinkDown(0);
        currentSize--;
        return min;
    }

    //swaps 2 positions
    public void swap(int a, int b) 
    {
        Node temp = node[a];
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
    public int getIndex(int index)
    {
	    return indexes[index];
    }
    public Node getNode(int index)
    {
	    return node[index];
    }
    public int size()
    {
	    return currentSize;
    }

}
