public class MinHeap
{
	int capacity;
	int currentSize;
	HeapNode[] mH;
	int [] indexes; //will be used to decrease the distance


	public MinHeap(int capacity) 
	{
		this.capacity = capacity;
		mH = new HeapNode[capacity + 1];
		indexes = new int[capacity];
		mH[0] = new HeapNode();
		mH[0].distance = Integer.MIN_VALUE;
		mH[0].vertex=-1;
		currentSize = 0;
	}

	public void display() 
	{
		for (int i = 0; i <=currentSize; i++) 
		{
			System.out.println(" " + mH[i].vertex + "   distance   " + mH[i].distance);
		}
		System.out.println("________________________");
	}

	public void insert(HeapNode x) 
	{
		currentSize++;
		int idx = currentSize;
		mH[idx] = x;
		indexes[x.vertex] = idx;
		bubbleUp(idx);
	}

	public void bubbleUp(int pos) 
	{
		int parentIdx = pos/2;
		int currentIdx = pos;
		while (currentIdx > 0 && mH[parentIdx].distance > mH[currentIdx].distance) 
		{
			HeapNode currentNode = mH[currentIdx];
			HeapNode parentNode = mH[parentIdx];

			//swap the positions
			indexes[currentNode.vertex] = parentIdx;
			indexes[parentNode.vertex] = currentIdx;
			swap(currentIdx,parentIdx);
			currentIdx = parentIdx;
			parentIdx = parentIdx/2;
		}
	}

	public HeapNode extractMin() 
	{
		HeapNode min = mH[1];
		HeapNode lastNode = mH[currentSize];
		//            update the indexes[] and move the last node to the top
		indexes[lastNode.vertex] = 1;
		mH[1] = lastNode;
		mH[currentSize] = null;
		sinkDown(1);
		currentSize--;
		return min;
	}

	public void sinkDown(int k) 
	{
		int smallest = k;
		int leftChildIdx = 2 * k;
		int rightChildIdx = 2 * k+1;
		if (leftChildIdx < heapSize() && mH[smallest].distance > mH[leftChildIdx].distance) 
		{
			smallest = leftChildIdx;
		}
		if (rightChildIdx < heapSize() && mH[smallest].distance > mH[rightChildIdx].distance) 
		{
			smallest = rightChildIdx;
		}
		if (smallest != k) 
		{

			HeapNode smallestNode = mH[smallest];
			HeapNode kNode = mH[k];

			//swap the positions
			indexes[smallestNode.vertex] = k;
			indexes[kNode.vertex] = smallest;
			swap(k, smallest);
			sinkDown(smallest);
		}
	}

	public void swap(int a, int b) 
	{
		HeapNode temp = mH[a];
		mH[a] = mH[b];
		mH[b] = temp;
	}

	public boolean isEmpty() 
	{
		return currentSize == 0;
	}

	public int heapSize()
	{
		return currentSize;
	}
}
