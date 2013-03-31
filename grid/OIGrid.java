package naybur.grid;

import static naybur.Utility.*;
import naybur.Point;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;

class OIGrid
{
    //grid is an array of linked lists. Each linked list hold indexes of points in point_list. 
    private LinkedList<Integer>[][] grid; 

    private double delta;

    private int len;

    private ArrayList<Point> point_list;

    private double[][] range;

    public OIGrid(ArrayList<Point> points, double[][] range)
    {
        delta = 1/Math.sqrt(points.size());

        int num_grids = (int)Math.ceil(1/delta);

        grid = new LinkedList[num_grids][num_grids];
        
        for(int i = 0; i < num_grids; i++)
        {
            for(int j = 0; j < num_grids; j++)
            {
                grid[i][j] = new LinkedList<Integer>();    
            }
        }

        this.point_list = points;
        this.range = range;

        overhaul();
    }

    public void overhaul()
    {
        for(int i = 0; i < point_list.size(); i++)
        {
            Point mapped_point = map( point_list.get(i), range ); 

            int cell_index_x = (int)Math.floor( mapped_point.x() / delta);
            int cell_index_y = (int)Math.floor( mapped_point.y() / delta);
            
//            System.out.println(cell_index_x + " " + cell_index_y);
//            System.out.println(delta);

            if(grid[cell_index_x][cell_index_y] == null)
            {
                grid[cell_index_x][cell_index_y] = new LinkedList<Integer>();
            }

            grid[cell_index_x][cell_index_y].add(i); 
        }
    }

    public ArrayList<Integer> findNearest(Point search_point, int k)
    {
        int[] search_cell = { (int)Math.floor(search_point.x()/delta), (int)Math.floor(search_point.y()/delta) } ;

        Rect rect = new Rect(0, search_cell);

        ArrayList<Integer> point_indexes = rect.findPoints(1); 
        
        Collections.sort(point_indexes, new MyComparator(search_point));

        int farthest_point_index = point_indexes.get(point_indexes.size()-1);

        double farthest_point_dist = sqDist(search_point, point_list[farthest_point_index]); 
        farthest_point_dist = Math.sqrt(farthest_point_dist);

        int new_len = (int)Math.ceil(farthest_point_dist/delta);

        rect = new Rect(new_len, search_cell);

        point_indexes = rect.findPoints(1); 
        
        Collections.sort(point_indexes, new MyComparator(search_point));

        return point_indexes;
    }

    private class MyComparator implements Comparator
    {
        double[] search_point;

        public MyComparator(double[] search_point)
        {
            this.search_point = search_point;
        }

        @Override
        public int compare(int a, int b)
        {
            double dist_a = sqDist(point_list[a], search_point);
            double dist_b = sqDist(point_list[b], search_point);

            if(dist_a > dist_b)
                return 1;
            if(dist_a < dist_b)
                return -1;

            return 0;
        }
    }

    private class Rect
    {
        public int len;
        public int[] cent;

        public Rect(int len, int[] cent)
        {
            this.len = len;
            this.cent = cent;
        }

        public ArrayList<Integer> findPoints(int k)
        {
            ArrayList<Integer> point_indexes = new ArrayList<Integer>();

            int width = grid[0].length;
            int height = grid.length;

            while(point_indexes.size() < k)
            {
                int start_x = cent[0] - len < 0 ? 0 : cent[0] - len;
                int end_x = cent[0] + len >= width ? width-1 : cent[0] + len;

                int start_y = cent[1] - len < 0 ? 0 : cent[1] - len;
                int end_y = cent[1] + len >= height ? height-1 : cent[1] + len;

                for(int i = start_x; i <= end_x; i++)
                {
                    for(int j = start_y; j <= end_y; j++)
                    {
                        point_indexes.addAll(grid[i][j]);
                    }
                }

                len++;
            }

            return point_indexes;
        }
    }

}
