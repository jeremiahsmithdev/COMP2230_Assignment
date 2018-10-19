/**
 * *
 *  * StationEdge.java – Assignment1
 *   * @author: Jeremiah Smith, Juyong Kim
 *    * @student Number: c3238179 cXXXXXXX
 *     * @version: 016/10/2018
 *      * Description: Holds information to create and store a StationEdge object
 *       */
// TODO modify according to http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html

public class StationEdge
{
	private String Name;
	private String Line;
<<<<<<< HEAD
	private int Duration;
	//additions based off the vogella
=======
	private int Duration;i
>>>>>>> 9e687ae0f087d65ed9686596d8b209419818b778
	private Station Source;
	private Station Destination;

	//constructor
	public StationEdge(String Name, String Line, int Duration, Station Source, Station Destination)
	{
		this.Name = Name;
		this.Line = Line;
		this.Duration = Duration;
		this.Source = Source;
		this.Destination = Destination;
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

	//side note, may or may not use
	//used for printing 
	@Override
	public String toString()
	{
		return Name + " " + Destination;
	}
}
