import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class KDTree <T extends Number> 
{
    private int dims;

    public KDTree(int dims)
    {
        this.dims = dims;
    }

    public KDNode build(ArrayList<ArrayList<T>> point_list, int depth)
    {
        int axis = depth % dims;
        int size = point_list.size();
        int mid = (int)(size/2);

        KDNode node;

        Collections.sort(point_list, new MyComparator(axis)); 
        
        if(size > 0)
        {
            ArrayList<T> median = point_list.get((int)(point_list.size()/2));

            if(median.size() != dims)
            {
                System.out.print("Invalid dimension for input point: ");
                System.out.println(median);
                System.out.println("Expected dimension " + dims + " but received " + median.size() + " dimensions"); 
            }

            node = new KDNode(median);
        }
        else
            return null;

//        System.out.println(mid);
        node.setLeft( build(new ArrayList(point_list.subList(0, mid)), depth+1) );
        node.setRight( build(new ArrayList(point_list.subList(mid+1, size)), depth+1) );

        return node;
    }

    private class MyComparator<T extends Number & Comparable<T>> implements Comparator<ArrayList<T>>
    {
        private int axis;

        public MyComparator(int axis)
        {
            this.axis = axis;
        }
        
        @Override
        public int compare(ArrayList<T> a, ArrayList<T> b)
        {
            return a.get(axis).compareTo(b.get(axis));
        }
    }
}
