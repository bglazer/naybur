package naybur.kdtree;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
 
public class KDTest{
 
    private ArrayList<ArrayList<Double>> point_list, test_points; 
    private KDTree kd;

    @Before
    public void setUp()
    {
        int dims = 2;
        int num_points = 100;
        double scale_factor_x = 100;
        double scale_factor_y = 100;

        KDTree kd = new KDTree(dims);

        point_list = new ArrayList();
        test_points = new ArrayList();

        for(int i = 0; i < num_points; i++)
        {
            ArrayList<Double> temp_arraylist = new ArrayList();

            temp_arraylist.add(Math.random() * scale_factor_x);    
            temp_arraylist.add(Math.random() * scale_factor_y);

            test_points.add(temp_arraylist);

            ArrayList<Double> temp_arraylist2 = new ArrayList();
            temp_arraylist2.add(Math.random() * scale_factor_x);    
            temp_arraylist2.add(Math.random() * scale_factor_y);

            point_list.add(temp_arraylist2);
        }
//        printPoints(point_list);
    }

    public static void printPoints(ArrayList<ArrayList<Double>> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = 0; j < list.get(i).size(); j++)
            {
                System.out.print(list.get(i).get(j) + " " );
            }

            System.out.println();
        } 
    }
    
    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
 
    @Test
    public void testBuildTree() 
    {
        kd = new KDTree(2);

        kd.build(point_list, 0);

//        KDTree.printTree(kd.getRoot());        
    }

    public static ArrayList<Double> linearSearch(ArrayList<ArrayList<Double>> list, ArrayList<Double> point)
    {
        double best_dist;
        double dist;

        ArrayList<Double> best_point = null;

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
    public void testSearchCustom()
    {
        int dims = 2;

        kd = new KDTree(dims);

        double[][] points = {{2,3}, {5,4}, {9,6}, {4,7}, {8,1}, {7,2}};

        ArrayList<ArrayList<Double>> custom_point_list = new ArrayList();
        ArrayList<Double> ta[] = new ArrayList[points.length];

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

        ArrayList<Double> sp = new ArrayList<Double>();

        sp.add(7.0);
        sp.add(0.0);
        
        ArrayList<Double> kd_result = kd.findNearest(sp).getPoint();
        ArrayList<Double> linear_result = linearSearch(custom_point_list, sp);

        assertEquals(linear_result.get(0), kd_result.get(0));
        assertEquals(linear_result.get(1), kd_result.get(1));
} 

    @Test
    public void testSearchRandom() 
    {
//        printPoints(point_list);
//        printPoints(test_points);

        kd = new KDTree(2);
        kd.build(point_list, 0);

        for(int i = 0; i < test_points.size(); i++)
        {
            ArrayList<Double> linear_result = linearSearch(point_list, test_points.get(i));

            ArrayList<Double> kd_result = kd.findNearest(test_points.get(i)).getPoint();
 
            assertEquals(linear_result.get(0), kd_result.get(0));
            assertEquals(linear_result.get(1), kd_result.get(1));
        }
    }
}
