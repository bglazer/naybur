package naybur.kdtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class MainTest
{
    public static void main(String[] args)
    {
        int dims = 2;

        KDTree kd = new KDTree(dims);

        double[][] points = {{2,3}, {5,4}, {9,6}, {4,7}, {8,1}, {7,2}};

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

   
        KDNode root = kd.build(point_list, 0);

        printTree(root);

        LinkedList<KDNode> stack = new LinkedList<KDNode>();
        ArrayList<Double> sp = new ArrayList<Double>();
        sp.add(8.0);
        sp.add(5.0);
        
        kd.buildStack(stack, sp, kd.getRoot(), 0); 

        System.out.println();

        for(KDNode n : stack)
            System.out.println(n.getPoint());

        sp.clear();
        sp.add(8.0);
        sp.add(0.0);
        
        KDNode closest = kd.findNearest(sp);
        System.out.println("\n" + closest.getPoint());
    } 

    public static void printTree(KDNode node)
    {
        LinkedList<KDNode> queue = new LinkedList();

        queue.add(node);

//        System.out.println(node.getPoint()); 

        while(!queue.isEmpty())
        {
            KDNode n = queue.pop();

            System.out.println(n.getPoint());

            if(n.getLeft() != null)
            {
                queue.add(n.getLeft());
            }

            if(n.getRight() != null)
            {
                queue.add(n.getRight());
            }
        }
    }
          
}
