import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
 
import static naybur.Utility.*;

public class UtilTest{
    private int num_elements;
    private int dims;
    private double start_low, start_high;
    private double end_low, end_high, asdf;
    
    private double[] start_range; 
    private double   start_diff;
    private double[] end_range;
    private double   end_diff;

    private double[][] point_list;

    @Before
    public void setUp()
    {
        num_elements = 100;
        dims  = 5;
        start_low  = -100;
        start_high =  100;
        end_low  = -1;
        end_high =  1;
        
        start_range = new double[] { start_low, start_high } ; 
        start_diff = Math.abs(start_range[0] - start_range[1]);
        end_range = new double[] { end_low, end_high } ;
        end_diff = Math.abs(end_range[0] - end_range[1]);
        
        point_list = new double[num_elements][dims];

        for(int i = 0; i < num_elements; i++)
        {
           for(int j = 0; j < dims; j++)
           {
              point_list[i][j] = Math.random() * start_diff - start_diff/2;
           }
        } 
    }

    @Test
    public void testMapArray()
    {
        double[][] mapped_list = map(point_list, start_range, end_range, dims);

        for(int i = 0; i < num_elements; i++)
        {
           for(int j = 0; j < dims; j++)
           {
//               System.out.println(i + " " + point_list[i][j] + " " + mapped_list[i][j]);
               assertTrue( mapped_list[i][j] > end_low &&  mapped_list[i][j] < end_high ); 
           }
        }

    }

    @Test
    public void testMapSingle()
    {
        double[] mapped_point = map(point_list[0], start_range, end_range, dims);

        for(int i = 0; i < dims; i++)
        {
            System.out.println( point_list[0][i] + " " + mapped_point[i] );
            assertTrue( mapped_point[i] > end_low &&  mapped_point[i] < end_high ); 
        }
    }

    @Test
    public void testMapUnitSquare()
    {
        double[][] mapped_list
    }
}
