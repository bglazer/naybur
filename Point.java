package naybur;

import java.util.ArrayList;

public class Point
{
    public double x,y;
    public int dims;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;

        dims = 2;
    }

    public String toString()
    {
        return "{" + x + ", " + y + "}";
    }
}
