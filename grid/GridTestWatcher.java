package naybur.grid;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import naybur.Point;
import java.util.*;

public class GridTestWatcher extends TestWatcher {
    private Point searchPoint; 
    private ArrayList<Point> list;

    public GridTestWatcher()
    {
    }

    @Override
    protected void failed(Throwable e, Description description) {
        System.out.println(searchPoint);         
        System.out.println(list);         
    }

    public void setPoint(Point searchPoint)
    {
        this.searchPoint = searchPoint;
    }
    
    public void setList(ArrayList<Point> list)
    {
        this.list = list;
    }
}
