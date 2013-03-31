package naybur;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import naybur.Point;
 
public class PointTest
{
    int test_dims;
    ArrayList<Double> test_coordinates;
    Point test_point;

    @Before
    public void setup()
    {
        test_dims = 3;
        test_coordinates = new ArrayList<Double>();
        test_coordinates.add(1.0);
        test_coordinates.add(2.0);
        test_coordinates.add(3.0);

        test_point = new Point(test_coordinates);
    }

    @Test
    public void testGetDims() 
    {
        int dims = test_point.dims;

        assertEquals(dims, test_dims);
    }

    @Test
    public void testXY()
    {
        double x = test_point.x();
        double y = test_point.y();

        assertEquals(x, test_coordinates.get(0), .00001);
        assertEquals(y, test_coordinates.get(1), .00001);
    }

}
