package naybur.kdtree;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
 
public class KDTest{
 
    private ArrayList<ArrayList<Double>> point_list, test_points; 

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
        
//        printPoints(test_points);
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
        KDTree kd = new KDTree(2);

        kd.build(point_list, 0);

        
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
    public void testSearch() 
    {

    }
}
