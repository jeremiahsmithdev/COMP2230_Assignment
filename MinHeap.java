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
    int capacity;
    int currentSize;
    HeapNode[] mH;
    int [] indexes; // used to decrease the duration

    public MinHeap(int capacity) 
    {
        this.capacity = capacity;
        mH = new HeapNode[capacity + 1];
        indexes = new int[capacity];
        // mH[0] = new HeapNode();
        // mH[0].setWeight(Integer.MIN_VALUE);		// ?????????
        // mH[0].setVertex(new Station());
        currentSize = 0;
    }

    //displays the minHeap, will need modification
    public void display() 
    {
        for (int i = 0; i < currentSize; i++) 
        {
            System.out.println(" " + mH[i].getVertex().getName() + "   total   " + mH[i].getComparator());
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
		//     mH[0] = x;
        int idx = currentSize;
        mH[idx] = x;
        indexes[x.getVertex().getID()] = idx;
        bubbleUp(idx);

	currentSize++;
	    // System.out.println(mH[252].getComparator());
    }

    //pushes value up to where it belongs
    public void bubbleUp(int pos) 
    {
        int parentIdx = pos/2;
        int currentIdx = pos;

	    // System.out.println(mH[parentIdx].getVertex().getName() + " has " +mH[252].getComparator());

	    // System.out.println(mH[parentIdx].getComparator());
	    // System.out.println(mH[currentIdx].getComparator());
	    // System.out.println(mH[parentIdx].getVertex().getName());
	    // System.out.println(mH[currentIdx].getVertex().getName());

        while (currentIdx > 0 && mH[parentIdx].getComparator() > mH[currentIdx].getComparator()) 
        {
            HeapNode currentNode = mH[currentIdx];
            HeapNode parentNode = mH[parentIdx];


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
        HeapNode min = mH[0];
        HeapNode lastNode = mH[currentSize-1];
//            update the indexes[] and move the last node to the top
        indexes[lastNode.getVertex().getID()] = 0;
        mH[0] = lastNode;
        mH[currentSize] = new HeapNode();//null;
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
        if (leftChildIdx < heapSize() && mH[smallest].getComparator() > mH[leftChildIdx].getComparator()) 
        {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && mH[smallest].getComparator() > mH[rightChildIdx].getComparator()) 
        {
            smallest = rightChildIdx;
        }
        if (smallest != k) 
        {

            HeapNode smallestNode = mH[smallest];
            HeapNode kNode = mH[k];

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
        HeapNode temp = mH[a];
        mH[a] = mH[b];
        mH[b] = temp;
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
