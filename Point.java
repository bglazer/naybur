package naybur;

import java.util.ArrayList;

public class Point
{
    public ArrayList<Double> coordinates;
    public int dims;

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
}
