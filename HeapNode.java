/**
 * *
 *  * assign1.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: node used in the minHeap
 *       */
public class HeapNode
{
    private int vertex;
    private int duration;
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
    //getters
    public int getVertex()
    {
        return vertex;
    }
    public int getDuration()
    {
        return duration;
    }
}