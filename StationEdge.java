/**
 * *
 *  * StationEdge.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 c3244203
 *     * @version: 016/10/2018
 *      * Description: Holds information to create and store a StationEdge object
 *       */
public class StationEdge
{
	//variables
	private String Name;
	private String Line;
	private int Duration;
	private Station Source;
	private Station Destination;
	private int defaultTime;
	private int changes;

	//constructor
	public StationEdge(String Name, String Line, int Duration, Station Source, Station Destination)
	{
		this.Name = Name;
		this.Line = Line;
		this.Duration = Duration;
		this.Source = Source;
		this.Destination = Destination;
		defaultTime = 1;
		changes = 0;
	}

	//functions
	public int getValue(String optimisationCriteria)		//depening on optimisation criteria
	{														//returns either the duration or changes
		int value = 0;
		if (optimisationCriteria == "time")
			value = Duration;
		else if (optimisationCriteria == "changes")
			value = changes;
		return value;
	}

	//getters
	public String getName()
	{
		return Name;
	}
	public String getLine()
	{
		return Line;
	}
	public int getDuration()
	{
		return Duration;
	}
	public Station getSource()
	{
		return Source;
	}
	public Station getDestination()
	{
		return Destination;
	}
	public int getDefault()
	{
		return defaultTime;
	}
}
