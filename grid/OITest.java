package naybur.grid;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import static naybur.Utility.*;
 
public class OITest{
 
    private double[][] point_list, test_point_list; 
    private OIGrid grid;
    private int scale_x = 10, scale_y = 10; 
    private int num_points = 1000;
    private double[][] pos_range = { {0, scale_x}, {0, scale_y} };
    private double[][] neg_range = { {-scale_x, scale_x}, {-scale_y, scale_y} };
    
    @Before
    public void setUp()
    {
    }

    public double[][] createTestList(int num_point_list, boolean integers, boolean negative)
    {
        double[][] test_list = new double[num_point_list][2]; 

        for(int i = 0; i < num_point_list; i++)
        {
            double temp_val1 = Math.random() * scale_x;
            double temp_val2 = Math.random() * scale_y;
            
            if(integers)
            {
                temp_val1 = Math.floor(temp_val1);
                temp_val2 = Math.floor(temp_val2);
            }

            if(negative)
            {
                temp_val1 = temp_val1 * 2 - scale_x; 
                temp_val2 = temp_val2 * 2 - scale_y; 
            }

            test_list[i][0] = temp_val1;
            test_list[i][1] = temp_val2;
        }

        return test_list;
    } 

    public void compareResults(double[][] point_list, double[][] test_point_list)
    {
        System.out.println("compareResults");
        
        for(int i = 0; i < test_point_list.length; i++)
        {
            double[] linear_result = linearSearch(point_list, test_point_list[i]);

            int num_results = 1;
            ArrayList<Integer> grid_result = grid.findNearest(test_point_list[i], num_results);
 
            System.out.println(i + ": " + test_point_list[i][0] + ", " + test_point_list[i][1]);

            double linear_dist = sqDist(linear_result, test_point_list[i]);
            double grid_dist  = sqDist(point_list[grid_result.get(0)], test_point_list[i]);

            if(linear_dist - grid_dist > .00001)
            {
            	System.out.println(test_point_list[i]);
            	printPoints(point_list);
            }
            assertEquals(linear_dist, grid_dist, .00001);
        }

    }


    public static void printPoints(double[][] list)
    {
        System.out.println("printPoints");
        System.out.print("{ ");

        int i, j = 0;

        for(i = 0; i < list.length-1; i++)
        {
            System.out.print("{");

            for(j = 0; j < list[i].length-1; j++)
            {
                System.out.print(list[i][j] + ", " );
            }

            System.out.print(list[i][j] + "}, ");

        } 

        System.out.print("{");

        for(j = 0; j < list[i].length-1; j++)
        {
            System.out.print(list[i][j] + ", " );
        }

        System.out.print(list[i][j] + "} }");
        System.out.println();
    }
    
    @After
    public void tearDown() {
    }
 

    public static double[] linearSearch(double[][] list, double[] point)
    { 
        double best_dist;
        double dist;

        double[] best_point = list[0];

        dist = sqDist(list[0], point);
        best_dist = dist;

        for(int i = 0; i < list.length; i++)
        {
            dist = sqDist(list[i], point);

            if(dist < best_dist)
            {
                best_dist = dist;
                best_point = list[i];
            }
        }

        return best_point;
    }

    @Test
    public void testBuildGrid() 
    {
        System.out.println("\ntestBuildGrid\n");
        double[][] point_list = createTestList(num_points, false, false);

        printPoints(point_list);

        grid = new OIGrid(point_list, pos_range);
    }

    @Test
    public void testSearchCustom()
    {
        double[][] point_list = { {-6.0, -4.0}, {-8.0, -10.0}, {2.0, -2.0}, {-2.0, -10.0}, {-10.0, 8.0}, {-10.0, 6.0}, {-2.0, 0.0}, {0.0, 2.0}, {8.0, -10.0}, {-8.0, -10.0} };

        OIGrid grid = new OIGrid(point_list, neg_range);

        double[] sp = new double[2];

        sp[0] = -4.0;
        sp[1] = 6.0;

        printPoints(point_list);

        ArrayList<Integer> grid_result = grid.findNearest(sp, 1);
        double[] linear_result = linearSearch(point_list, sp);

        double linear_dist = sqDist(linear_result, sp);
        double grid_dist  = sqDist(point_list[grid_result.get(0)], sp);

        assertEquals(linear_dist, grid_dist, .00001);
    } 

    @Test
    public void testSearchRandomIntegerPos() 
    {
        System.out.println("\ntestSearchRandomIntegerPos\n");
        point_list = createTestList(num_points, true, false);
        test_point_list = createTestList(num_points, true, false);

        grid = new OIGrid(point_list, pos_range);

        printPoints(point_list);
//        printPoints(test_point_list);

        compareResults(point_list, test_point_list);
    }

    @Test 
    public void testSearchRandomDoublePos()
    {
        System.out.println("\ntestSearchRandomDoublePos\n");
        point_list = createTestList(num_points, false, false); 
        test_point_list = createTestList(num_points, false, false);

        grid = new OIGrid(point_list, pos_range);

        printPoints(point_list);
//        printPoints(test_point_list);

        compareResults(point_list, test_point_list);
    }

    @Test
    public void testSearchRandomIntegerNeg() 
    {
        System.out.println("\ntestSearchRandomIntegerNeg\n");
        point_list = createTestList(num_points, true, true);
        test_point_list = createTestList(num_points, true, true);

        grid = new OIGrid(point_list, neg_range);

        printPoints(point_list);
//        printPoints(test_point_list);

        compareResults(point_list, test_point_list);
    }

    @Test
    public void testSearchRandomDoubleNeg()
    {
        System.out.println("\ntestSearchRandomDoubleNeg\n");
        point_list = createTestList(num_points, false, true); 
        test_point_list = createTestList(num_points, false, true);

        grid = new OIGrid(point_list, neg_range);

        printPoints(point_list);
//        printPoints(test_point_list);

        compareResults(point_list, test_point_list);
    }
    
}
