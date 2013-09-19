package naybur.grid;

import static naybur.Utility.*;
import naybur.Point;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class OIGrid
{
    //grid is an array of linked lists. Each linked list hold indexes of points in point_list. 
    public LinkedList<Integer>[][] grid; 

    private double delta, diff;

    private int len, num_grids;

    public ArrayList<Point> point_list;

    private double[][] range;

    public OIGrid(ArrayList<Point> points, double[][] range)
    {
        double xdiff = Math.abs(range[0][0] - range[0][1]);
        double ydiff = Math.abs(range[1][0] - range[1][1]);
        double diff = xdiff > ydiff ? xdiff : ydiff;
//        println(diff);

        delta = 1/Math.sqrt(points.size()) * diff;
//        println(delta);
        num_grids = (int)Math.ceil((1/delta)*diff);
//        println(num_grids);

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
//        println(delta);
        for(int i = 0; i < num_grids; i++)
        {
            for(int j = 0; j < num_grids; j++)
            {
//                println(grid[i][j]);
                grid[i][j].clear();
            }
        }

//        println(" ");
        for(int i = 0; i < point_list.size(); i++)
        {
//            println(point_list.get(i));
//            Point mapped_point = map(point_list.get(i), range); 
            Point point = point_list.get(i);

            int cell_index_x = (int)Math.floor(point.x / delta);
            int cell_index_y = (int)Math.floor(point.y / delta);
            
//            println(i+ ": " +  point.x + " " + point.y);
//            println(i+ ": "+ cell_index_x + " " + cell_index_y);

            grid[cell_index_x][cell_index_y].add(i); 
        }
    }

    public List<Integer> findNearest(Point search_point, int k)
    {
        int[] search_cell = { (int)Math.floor(search_point.x/delta), (int)Math.floor(search_point.y/delta) } ;
//        println(search_cell[0] + " " + search_cell[1]);
//        println(search_point);

        Rect rect = new Rect(0, search_cell);

        ArrayList<Integer> point_indexes = rect.findPoints(k); 
        
//        println(point_indexes);
        Collections.sort(point_indexes, new MyComparator(search_point));
//        println(point_indexes);

        int farthest_point_index = point_indexes.get(point_indexes.size()-1);

        double farthest_point_dist = sqDist(search_point, point_list.get(farthest_point_index)); 
        farthest_point_dist = Math.sqrt(farthest_point_dist);

        int new_len = (int)Math.ceil(farthest_point_dist/delta);
//        println(delta);
//        println(new_len);

        rect = new Rect(new_len, search_cell);

        point_indexes = rect.findPoints(k); 
        
        Collections.sort(point_indexes, new MyComparator(search_point));

//        println(point_indexes);
        return point_indexes.subList(0,k);
    }

    private class MyComparator implements Comparator<Integer>
    {
        Point search_point;

        public MyComparator(Point search_point)
        {
            this.search_point = search_point;
        }

        @Override
        public int compare(Integer a, Integer b)
        {
            double dist_a = sqDist(point_list.get(a), search_point);
            double dist_b = sqDist(point_list.get(b), search_point);

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

//            println("**********************************************");
            while(point_indexes.size() < k)
            {
                point_indexes.clear();
//                println(len);
                int start_x = cent[0] - len < 0 ? 0 : cent[0] - len;
                int end_x = cent[0] + len >= width ? width-1 : cent[0] + len;

                int start_y = cent[1] - len < 0 ? 0 : cent[1] - len;
                int end_y = cent[1] + len >= height ? height-1 : cent[1] + len;

//                println(" ");
                for(int i = start_x; i <= end_x; i++)
                {
                    for(int j = start_y; j <= end_y; j++)
                    {
//                        println(i + " " + j);
//                        println(grid[i][j]);
                        point_indexes.addAll(grid[i][j]);
                    }
                }

                len++;
            }

//            println(point_indexes);
            return point_indexes;
        }
    }
}
