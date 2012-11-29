package naybur.kdtree;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
 
public class GridTest{
 
    private double[][] point_list, test_points; 
    private KDTree kd;
    private double scale_x = 1000;
    private double scale_y = 1000;

    @Before
    public void setUp()
    {
    }

    public double[][] createTestList(int num_points, boolean integers, boolean negative)
    {
        double[][] test_list = new double[num_points][2]; 

        for(int i = 0; i < num_points; i++)
        {
            double[] temp_arraylist = new ArrayList();
            
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

    public void compareResults(double[][] point_list, double[][] test_points)
    {
        System.out.println("compareResults");
        
        for(int i = 0; i < test_points.size(); i++)
        {
            double[] linear_result = linearSearch(point_list, test_points.get(i));

            double[] kd_result = kd.findNearest(test_points.get(i)).getPoint();
 
//            System.out.println(i + ": " + test_points.get(i));

            double linear_dist = KDTree.sqDist(linear_result, test_points.get(i));
            double kd_dist  = KDTree.sqDist(kd_result, test_points.get(i));

            assertEquals(linear_dist, kd_dist, .00001);
        }

    }


    public static void printPoints(double[][] list)
    {
        System.out.println("printPoints");
        System.out.print("{ ");

        int i, j = 0;

        for(i = 0; i < list.size()-1; i++)
        {
            System.out.print("{");

            for(j = 0; j < list.get(i).size()-1; j++)
            {
                System.out.print(list.get(i).get(j) + ", " );
            }

            System.out.print(list.get(i).get(j) + "}, ");

        } 

        System.out.print("{");

        for(j = 0; j < list.get(i).size()-1; j++)
        {
            System.out.print(list.get(i).get(j) + ", " );
        }

        System.out.print(list.get(i).get(j) + "} }");
        System.out.println();
    }
    
    @After
    public void tearDown() {
    }
 

    public static double[] linearSearch(double[][] list, double[] point)
    { 
        double best_dist;
        double dist;

        double[] best_point = list.get(0);

        dist = KDTree.sqDist(list.get(0), point);
        best_dist = dist;

        for(int i = 0; i < list.size(); i++)
        {
            dist = KDTree.sqDist(list.get(i), point);

            if(dist < best_dist)
            {
                best_dist = dist;
                best_point = list.get(i);
            }
        }

        return best_point;
    }

    @Test
    public void testBuildTree() 
    {
        kd = new KDTree(2);

        double[][] point_list = createTestList(1000, false, false);

        kd.build(point_list, 0);

//        printPoints(point_list);
//        KDTree.printTree(kd.getRoot());        
    }

    @Test
    public void testSearchCustom()
    {
        int dims = 2;

        kd = new KDTree(dims);

        double[][] points = { {1.0, 6.0}, {3.0, 5.0}, {4.0, 8.0}, {4.0, 0.0}, {4.0, 7.0}, {5.0, 9.0}, {5.0, 8.0}, {6.0, 0.0}, {7.0, 1.0}, {9.0, 6.0} };
        double[][] custom_point_list = new ArrayList(), test_points;
        double[] ta[] = new ArrayList[points.length];

        for(int i = 0; i < points.length; i++)
        {
            ta[i] = new ArrayList();

            for(int j = 0; j < points[i].length; j++)
            {
                ta[i].add(points[i][j]);
            }

            custom_point_list.add(ta[i]);
        } 

   
        KDNode root = kd.build(custom_point_list, 0);

        double[] sp = new double[]();

        sp.add(5.0);
        sp.add(6.0);
        
        double[] kd_result = kd.findNearest(sp).getPoint();
        double[] linear_result = linearSearch(custom_point_list, sp);

/*        System.out.println();
        System.out.println(kd_result);
        System.out.println(linear_result);
        System.out.println();
*/
        double linear_dist = KDTree.sqDist(linear_result, sp);
        double kd_dist  = KDTree.sqDist(kd_result, sp);

        assertEquals(linear_dist, kd_dist, .00001);
    } 

    @Test
    public void testSearchRandomIntegerPos() 
    {
        System.out.println("\ntestSearchRandomIntegerPos\n");
        point_list = createTestList(1000, true, false);
        test_points = createTestList(1000, true, false);

        kd = new KDTree(2);
        kd.build(point_list, 0);

//        KDTree.printTree(kd.getRoot());

//        printPoints(point_list);
//        printPoints(test_points);

        compareResults(point_list, test_points);
    }

    @Test 
    public void testSearchRandomDoublePos()
    {
        System.out.println("\ntestSearchRandomDoublePos\n");
        point_list = createTestList(1000, false, false); 
        test_points = createTestList(1000, false, false);

        kd = new KDTree(2);
        kd.build(point_list, 0);

//        printPoints(point_list);
//        printPoints(test_points);

        compareResults(point_list, test_points);
    }

    @Test
    public void testSearchRandomIntegerNeg() 
    {
        System.out.println("\ntestSearchRandomIntegerNeg\n");
        point_list = createTestList(1000, true, true);
        test_points = createTestList(1000, true, true);

        kd = new KDTree(2);
        kd.build(point_list, 0);

//        KDTree.printTree(kd.getRoot());

//        printPoints(point_list);
//        printPoints(test_points);

        compareResults(point_list, test_points);
    }

    @Test
    public void testSearchRandomDoubleNeg()
    {
        System.out.println("\ntestSearchRandomDoubleNeg\n");
        point_list = createTestList(1000, false, true); 
        test_points = createTestList(1000, false, true);

        kd = new KDTree(2);
        kd.build(point_list, 0);

//        printPoints(point_list);
//        printPoints(test_points);

        compareResults(point_list, test_points);
    }
}
