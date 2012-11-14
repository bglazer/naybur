import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;

class KDTree <T extends Number & Comparable<T>> 
{
    private KDNode<T> root;
    private int dims;

    public KDNode<T> getRoot()
    {
        return root;
    }

    public KDTree(int dims)
    {
        this.dims = dims;
    }
    
    public KDNode<T> build(ArrayList<ArrayList<T>> point_list, int depth)
    {
        int axis = depth % dims;
        int size = point_list.size();
        int mid = (int)(size/2);

        KDNode node;

        MyComparator<T> c = new MyComparator<T>();
        c.setAxis(axis);

        Collections.sort(point_list, c); 
        
        if(size > 0)
        {
            ArrayList<T> median = point_list.get((int)(point_list.size()/2));

            if(median.size() != dims)
            {
                System.out.print("Invalid dimension for input point: ");
                System.out.println(median);
                System.out.println("Expected dimension " + dims + " but received " + median.size() + " dimensions"); 
            }

            node = new KDNode(median, axis);
        }
        else
            return null;

        node.setLeft( build(new ArrayList(point_list.subList(0, mid)), depth+1) );
        node.setRight( build(new ArrayList(point_list.subList(mid+1, size)), depth+1) );

        this.root = node;

        return node;
    }

    public LinkedList<KDNode<T>> buildStack(LinkedList<KDNode<T>> stack, ArrayList<T> search_point, KDNode<T> node, int axis)
    {
        MyComparator c = new MyComparator();
        c.setAxis(axis);
        stack.push(node);

        if(c.compare(search_point, node.getPoint()) > 0 && node.getRight() != null)
        {
            buildStack(stack, search_point, node.getRight(), (axis+1)%dims);
        }
        
        else if(c.compare(search_point, node.getPoint()) < 0 && node.getLeft() != null)
        {
            buildStack(stack, search_point, node.getLeft(), (axis+1)%dims);
        }

        return stack;
    }

    public KDNode<T> findNearest(KDNode root, ArrayList<T> search_point)
    {
        MyComparator c = new MyComparator();

        LinkedList<KDNode<T>> stack = new LinkedList();

        buildStack(stack, search_point, root, 0);

        KDNode<T> node = stack.pop();
        KDNode<T> closest = node;
        
        double best_dist = sqDist(node.getPoint(), search_point);
        double dist;

        while(!stack.isEmpty())
        {
            node = stack.pop();

            dist = sqDist(node.getPoint(), search_point);

            if( dist < best )
            {
                best = dist;
                closest = node; 
            }

            double dist_splitting_plane = sqDist(node, closest.getSplittingPlane()); 

            if( dist_splitting_plane < best )
            {
                c.setAxis(node.getAxis());

                if(c.compare(search_point, node.getPoint()) > 0 && node.getLeft() != null)
                {
                    buildStack(stack, search_point, node.getLeft(), node.getAxis());
                }
                else if(c.compare(search_point, node.getPoint()) < 0 && node.getRight() != null)
                {
                    buildStack(stack, search_point, node.getRight(), node.getAxis());
                }
            }
        }
    }

    public double sqDist(ArrayList<T> a, ArrayList<T> b)
    {
        double dist = 0;

        for(int i = 0; i < dims; i++)
        {
            dist += Math.pow(a.get(i) - b.get(i), 2);
        }

        return dist;
    }

    private class MyComparator<S extends Number & Comparable<S>> implements Comparator<ArrayList<S>>
    {
        private int axis;

        @Override
        public int compare(ArrayList<S> a, ArrayList<S> b)
        {
            return a.get(axis).compareTo(b.get(axis));
        }

        public void setAxis(int axis)
        {
            this.axis = axis;
        }
    }
}
