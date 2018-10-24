/**
 * *
 *  * Station.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: Holds information to create and store a Path
 *       */
public class Path
{
	//variables
	private String path;
	private int time;
	private int changes;

	//Empty Constructor
	public Path()
	{
	}

	//Setters
	public void setTime(int time)
	{
		this.time = time;
	}
	public void setChanges(int changes)
	{
		this.changes = changes;
	}
	//Getters
	public int getTime()
	{
		return time;
	}
	public int getChanges()
	{
		return changes;
	}
}
