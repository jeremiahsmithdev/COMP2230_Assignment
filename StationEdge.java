public class StationEdge
{
	private String Name;
	private String Line;
	private int Duration;

	public StationEdge(String Name, String Line, int Duration)
	{
		this.Name = Name;
		this.Line = Line;
		this.Duration = Duration;
	}

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
}
