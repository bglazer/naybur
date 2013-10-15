package naybur;


import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
 
public class PointTest
{
    int test_dims;
    ArrayList<Double> test_coordinates;
    Point test_point;

    @Before
    public void setup()
    {
        test_dims = 2;

        test_point = new Point(1.0,2.0);
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

        assertEquals(1.0, x,  .00001);
        assertEquals(2.0, y,  .00001);
    }
}
