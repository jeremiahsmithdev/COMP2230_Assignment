//TODO wondering if the heapNode itself should have the station line??
public class HeapNode
{
    int vertex;
    int duration;
    //constructor
    public HeapNode()
    {
        this.vertex = 0;
        this.duration = 0;
    }
    public HeapNode(int vertex, int duration)
    {
        this.vertex = vertex;
        this.duration = duration;
    }

    //setters
    public void setVertex(int vertex)
    {
        this.vertex = vertex;
    }
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
}