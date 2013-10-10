package naybur.kdtree;

import java.util.ArrayList;
import java.util.List;

import naybur.Point;

public class KDNode
{
    private KDNode left, right;
    private Point position;
    private int axis;

    /**
      * Constructor that builds a KDNode with a given position, on a given axis.
      *
      * @param
      */
    public KDNode(Point position, int axis)
    {
        this.position = position;
        this.axis = axis;
    } 

    public void setLeft(KDNode left)
    {
//        System.out.println("setleft");
        this.left = left;
    }

    public void setRight(KDNode right)
    {
//        System.out.println("setright");
        this.right = right;
    }

    public KDNode getLeft()
    {
        return left;
    }

    public KDNode getRight()
    {
        return right;
    }

    public Point getPoint()
    {
        return position;
    }

    public int getAxis()
    {
        return axis;
    }

    /**
      * Returns an ArrayList that contains all zeros except at the axis, which is the value of the position for that axis.
      *
      * @param
      */
    public Point getSplittingPlane(Point position)
    {
        double axis_value = position.get(axis);
        
        List<Double> splitting_plane_list = new ArrayList<Double>();

        for(int i = 0; i < position.size(); i++)
        {
            splitting_plane_list.add(0.0);
        }
        
        splitting_plane_list.set(axis, axis_value);
        
        Point splitting_plane = new Point(splitting_plane_list);

        return splitting_plane;
    }

    public String toString()
    {
        return getPoint().toString();
    }
}
