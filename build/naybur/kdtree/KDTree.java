package naybur.kdtree;

import naybur.Utility;
import naybur.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class KDTree
{
    private KDNode root;
    private int dims;

    /**
      * Returns the root node of the kdtree ie the topmost node. 
      *
      */

    public KDNode getRoot()
    {
        return root;
    }

    /**
      * Initialize a zero element kdtree.
      *
      * @param dims number of dimensions of the kdtree
      */
    public KDTree(int dims)
    {
    	this.dims = dims;
    }

    /**
      * Custom Comparator class to compare points based on a specified dimension. 
      *
      */
    private class PointComparator implements Comparator<Point>
    {
        private int axis;

        @Override
        public int compare(Point a, Point b)
        {
            if(a.get(axis) > b.get(axis))
                return 1;
            else if(a.get(axis) < b.get(axis))
                return -1;
            else 
                return 0;
        }

        public void setAxis(int axis)
        {
            this.axis = axis;
        }
    }

    /**
      * Build a kdtree recursively using a list of points. 
      *
      * @param
      */
    public KDNode build(List<Point> point_list, int depth)
    {
        int axis = depth % dims;
        int size = point_list.size();
        int mid = (int)(size/2);
        
        PointComparator comparator = new PointComparator();

        KDNode node;

        axis = depth%dims;
        comparator.setAxis(axis);

        Collections.sort(point_list, comparator); 
        
        if(size > 0)
        {
            Point median = point_list.get(point_list.size()/2);

            node = new KDNode(median, axis);
        }
        else
            return null;

        node.setRight( build(new ArrayList<Point>(point_list.subList(mid+1, size)), depth+1) );
        node.setLeft( build(new ArrayList<Point>(point_list.subList(0, mid)), depth+1) );

        this.root = node;

        return node;
    }
    
    /**
      * Given a search point, constructs a LIFO LinkedList stack of nodes. This stack contains all the nodes that would be traversed 
      * in order to add the search point to the tree. This is a crucial step in doing the nearest neighbor search.
      *
      * @param stack The LinkedList stack that you want to use. Can be empty or already contain elements.
      * @param search_point Search point that is used to find the right nodes to add to the stack
      * @param node Node to start the search from
      * @param axis Axis of the given node.
      */

    private LinkedList<KDNode> buildStack(LinkedList<KDNode> stack, Point search_point, KDNode node )
    {
        int axis = node.getAxis();

        stack.push(node);
       
        PointComparator c = new PointComparator();

        c.setAxis(axis);

        if(c.compare(search_point, node.getPoint()) <= 0 && node.getLeft() != null)
        {
            buildStack(stack, search_point, node.getLeft());
        }
        else if(c.compare(search_point, node.getPoint()) > 0 && node.getRight() != null)
        {
            buildStack(stack, search_point, node.getRight());
        }

        return stack;
    }

    /**
      * Finds the nearest point in the KDTree to a given search point.
      *
      * @param search_point Search point used to do the nearest neighbor search.
      */

    public KDNode findNearest(Point search_point)
    {

        LinkedList<KDNode> stack = new LinkedList<KDNode>();
        
        PointComparator c = new PointComparator();

        buildStack(stack, search_point, root );

        KDNode node = stack.peek();
        KDNode closest = node;
        
        double best_dist = Utility.sqDist(node.getPoint(), search_point);
        double dist;

        while(!stack.isEmpty())
        {
            node = stack.pop();

            dist = Utility.sqDist(node.getPoint(), search_point);

            if( dist < best_dist )
            {
                best_dist = dist;
                closest = node; 
            }

            double dist_splitting_plane = Math.pow(search_point.get(node.getAxis()) - (Double)node.getPoint().get(node.getAxis()), 2);

            if( dist_splitting_plane <= best_dist )
            {
                c.setAxis(node.getAxis());

                KDNode left = node.getLeft();
                KDNode right = node.getRight();

                if(c.compare(search_point, node.getPoint()) > 0 && left != null)
                {
                    buildStack(stack, search_point, left);
                }
                else if(c.compare(search_point, node.getPoint()) <= 0 && right != null)
                {
                    buildStack(stack, search_point, right);
                }
            }

        }

        return closest;
    }

    public static void printTree(KDNode node)
    {
        System.out.println("printTree");

        LinkedList<KDNode> queue = new LinkedList<KDNode>();

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
