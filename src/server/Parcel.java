package server;

public class Parcel {
	public double length;
	public double width;
	public double depth;
	public String cat;

	public String toString()
	{
		return "{ length: "+length+", width: "+width+", depth: "+depth+", cat: "+cat+" }";
	}
}
