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
        int dims = test_point.getDims();

        assertEquals(dims, test_dims);
    }

}
