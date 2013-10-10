package naybur;

import java.util.ArrayList;
import java.util.List;

public class Point
{
    public int dims;
    public List<Double> coordinates;

    public Point(double x, double y)
    {
    	this.coordinates = new ArrayList<Double>(2);
    	coordinates.add(0, x);
    	coordinates.add(1, y);
    	
        dims = 2;
    }
    
    public Point(List<Double> coordinates)
    {
    	dims = coordinates.size();
    	this.coordinates = coordinates;
    }
    
    public String toString()
    {
    	String printout = "{ ";
    	for(Double d : this.coordinates)
    	{
    		printout += d + " "; 
    	}
    	printout += " }";
    	
    	return printout;
    }
    
    public double get(int axis)
    {
    	return coordinates.get(axis);
    }
    
    public int size()
    {
    	return coordinates.size();
    }
    
    public double x()
    {
    	return coordinates.get(0);
    }   
    
    public double y()
    {
    	return coordinates.get(1);
    }
    
    public void x(double x)
    {
    	coordinates.set(0, x);
    }
    
    public void y(double y)
    {
    	coordinates.set(1, y);
    }
}