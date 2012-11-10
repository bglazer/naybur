import java.util.ArrayList;

class KDNode<T extends Number>
{
    private KDNode left, right;
    private ArrayList<T> position;

    public KDNode(ArrayList<T> position)
    {
        this.position = position;
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

    public ArrayList<T> getPoint()
    {
        return position;
    }
}
