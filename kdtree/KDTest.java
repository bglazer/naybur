import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
 
public class KDTest{
 
    private Collection collection;
 
    @BeforeClass
    public static void oneTimeSetUp() {
        int dims = 2;

        KDTree kd = new KDTree(dims);

        ArrayList<ArrayList<Double>> point_list = new ArrayList();
        ArrayList<Double> ta[] = new ArrayList[points.length];

        for(int i = 0; i < points.length; i++)
        {
            ta[i] = new ArrayList();

            for(int j = 0; j < points[i].length; j++)
            {
                ta[i].add(points[i][j]);
            }

            point_list.add(ta[i]);
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
        collection.add("itemA");
        assertEquals(1, collection.size());
        System.out.println("@Test - testOneItemCollection");
    }
}
