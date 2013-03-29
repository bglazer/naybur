package naybur;

import java.util.ArrayList;

public class Point
{
    private ArrayList<Double> coordinates;
    private int dims;

    public Point(ArrayList<Double> coordinates)
    {
        this.coordinates = coordinates;
        dims = coordinates.size();
    }

    public ArrayList<Double> getCoordinates()
    {
        return coordinates;
    }

    public int getDims()
    {
        return dims;
    }
}
