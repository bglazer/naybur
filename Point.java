package naybur;

import java.util.ArrayList;

public class Point
{
    public ArrayList<Double> coordinates;
    public int dims;

    public Point(double x, double y)
    {
        this.coordinates = new ArrayList<Double>();

        coordinates.add(x);
        coordinates.add(y);

        dims = coordinates.size();
    }

    public Point(ArrayList<Double> coordinates)
    {
        this.coordinates = coordinates;
        dims = coordinates.size();
    }

    public Double x()
    {
        return coordinates.get(0);
    }

    public Double y()
    {
        return coordinates.get(1);
    }

    public String toString()
    {
        return "{" + x() + ", " + y() + "}";
    }
}
