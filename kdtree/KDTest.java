package naybur.kdtree;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
 
public class KDTest{
 
    private Collection collection;
 
    @BeforeClass
    public static void oneTimeSetUp() {
        int dims = 2;
        int num_points = 100;
        double scale_factor_x = 100;
        double scale_factor_y = 100;

        KDTree kd = new KDTree(dims);

        ArrayList<ArrayList<Double>> point_list = new ArrayList();
        ArrayList<ArrayList<Double>> test_points = new ArrayList();

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
        
        printPoints(test_points);
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
 
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    }
 
    @Before
    public void setUp() {

        System.out.println("@Before - setUp");
    }
 
    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
 
    @Test
    public void testEmptyCollection() {
        System.out.println("@Test - testEmptyCollection");
    }
 
    @Test
    public void testOneItemCollection() {
    }
}
