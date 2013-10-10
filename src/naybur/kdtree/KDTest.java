package naybur.kdtree;

import static naybur.Utility.*;

import naybur.Point;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
 
public class KDTest{
 
    List<Point> point_list;
	private List<Point> test_points; 
    private KDTree kd;
    private double scale_x = 1000;
    private double scale_y = 1000;

    @Before
    public void setUp()
    {
    }

    /**
      * Create a list of numbers.
      *
      *
      * @param num_points Number of points in the list.
      * @param integers True - List contains integers. 
      *                 False - List contains doubles.
      * @param negative True - Negative numbers are included.
      *                 False - Only positive numbers. 
      *
      */
    public List<Point> createTestList(int num_points, boolean integers, boolean negative)
    {
        List<Point> test_list = new ArrayList<Point>();
        List<Double> coordinates = new ArrayList<Double>();

        for(int i = 0; i < num_points; i++)
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

            coordinates.add(temp_val1);
            coordinates.add(temp_val2);
            Point temp_point = new Point(coordinates);
            test_list.add(temp_point);
        }

        return test_list;
    } 

    /** 
      * Given a list of points used to create a kdtree and a list of search_points, test that the results of searching this kd tree
      * match a linear search.
      *  
      * @param point_list A list of points used to construct kdtree. 
      * @param search_points List of search points to search. 
      */
    public void compareResults(List<Point> point_list, List<Point> search_points)
    {
        System.out.println("compareResults");
        
        for(int i = 0; i < search_points.size(); i++)
        {
            Point linear_result = linearSearch(point_list, search_points.get(i));

            Point kd_result = kd.findNearest(search_points.get(i)).getPoint();
 
            double linear_dist = sqDist(linear_result, search_points.get(i));
            double kd_dist  = sqDist(kd_result, search_points.get(i));

            assertEquals(linear_dist, kd_dist, .00001);
        }

    }

    /**
      * Print a list of points to stdout. 
      *
      * @param list list to print
      */
    public static void printPoints(ArrayList<ArrayList<Double>> list)
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
 
    
    /**
      * Builds a kdtree.  
      *
      */

    @Test
    public void testBuildTree() 
    {
        kd = new KDTree(2);

        List<Point> point_list = createTestList(1000, false, false);

        kd.build(point_list, 0);

//        printPoints(point_list);
//        KDTree.printTree(kd.getRoot());        
    }

    /**
      * Nearest neighbor search for a custom list of points.  
      * Tests kdtree against a linear search for a correct distance between the search point and its nearest neighbor. 
      *
      */
/*
    @Test
    public void testSearchCustom()
    {
        int dims = 2;

        kd = new KDTree(dims);

        double[][] points = { {1.0, 6.0}, {3.0, 5.0}, {4.0, 8.0}, {4.0, 0.0}, {4.0, 7.0}, {5.0, 9.0}, {5.0, 8.0}, {6.0, 0.0}, {7.0, 1.0}, {9.0, 6.0} };
        List<Point> custom_point_list = new ArrayList<Point>();
		List<Point> test_points;

        for(int i = 0; i < points.length; i++)
        {
            ta[i] = new ArrayList();

            for(int j = 0; j < points[i].length; j++)
            {
                ta[i].add(points[i][j]);
            }

            custom_point_list.addAll(ta[i]);
        } 

   
        KDNode root = kd.build(custom_point_list, 0);

        Point sp = new ArrayList<Double>();

        sp.add(5.0);
        sp.add(6.0);
        
        ArrayList<Double> kd_result = kd.findNearest(sp).getPoint();
        ArrayList<Double> linear_result = custom_point_list.get(linearSearch(custom_point_list, sp));

        System.out.println();
        System.out.println(kd_result);
        System.out.println(linear_result);
        System.out.println();

        double linear_dist = sqDist(linear_result, sp);
        double kd_dist  = sqDist(kd_result, sp);

        assertEquals(linear_dist, kd_dist, .00001);
    } 
*/
    /**
      * Test nearest neighbor using positive integers.
      * Tests kdtree against a linear search for a correct distance between the search point and its nearest neighbor. 
      */

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

    /**
      * Test nearest neighbor using positive doubles.
      * Tests kdtree against a linear search for a correct distance between the search point and its nearest neighbor. 
      *
      */

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

    /**
      * Nearest neighbor search using a test set of random integers over a kdtree of random integers with negatives included.
      * Tests kdtree against a linear search for a correct distance between the search point and its nearest neighbor. 
      *
      */
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

    /**
      * Nearest neighbor search using a test set of random doubles over a kdtree of random doubles with negatives included. 
      * Tests kdtree against a linear search for a correct distance between the search point and its nearest neighbor. 
      *
      */
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
