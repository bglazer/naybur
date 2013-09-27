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
    //grid is an array of linked lists. The linked lists hold Points in point_list.
	//made public so that 
    public List<List<List<Point>>> grid; 

	//range is assumed to be a 2x2 array representing the area that the points under consideration occupy.
	//e.g. [[0,0][100,200]] is a rectangle with top left corner at 0,0 and bottom right at [100,200]
    private double[][] range;

	//delta represents the size of a grid square
	//diff is the absolute value of the longest dimension of the range.
    private double delta, diff;

    private int num_grids;

    public ArrayList<Point> point_list;

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

        grid = new ArrayList<List<List<Point>>>(num_grids);
        
        for(int i = 0; i < num_grids; i++)
        {
			List<List<Point>> row = new ArrayList<List<Point>>(num_grids);
			grid.add(row);
            for(int j = 0; j < num_grids; j++)
            {
				List<Point> col = new LinkedList<Point>();
				row.add(col);
            }
        }

        this.point_list = points;
        this.range = range;

        overhaul();
    }

    public void overhaul()
    {
//        println(delta);
        for(List<List<Point>> row : grid)
        {
            for(List<Point> col : row)
            {
                col.clear();
            }
        }

//        println(" ");
        for(Point point : point_list)
        {
            int cell_index_x = (int)Math.floor(point.x / delta);
            int cell_index_y = (int)Math.floor(point.y / delta);
            
//            println(i+ ": " +  point.x + " " + point.y);
//            println(i+ ": "+ cell_index_x + " " + cell_index_y);

            grid.get(cell_index_x).get(cell_index_y).add(point); 
        }
    }

    public List<Point> findNearest(Point search_point, int k)
    {
        int[] search_cell = { (int)Math.floor(search_point.x/delta), (int)Math.floor(search_point.y/delta) } ;
//        println(search_cell[0] + " " + search_cell[1]);
//        println(search_point);

        Rect rect = new Rect(0, search_cell);

        List<Point> neighbors = rect.findPoints(k); 
        
//        println(neighbors);
        Collections.sort(neighbors, new MyComparator(search_point));
//        println(neighbors);

        Point farthest_neighbor = neighbors.get(neighbors.size()-1);

        double farthest_neighbor_dist = sqDist(search_point, farthest_neighbor); 
        farthest_neighbor_dist = Math.sqrt(farthest_neighbor_dist);

        int new_len = (int)Math.ceil(farthest_neighbor_dist/delta);
//        println(delta);
//        println(new_len);

        rect = new Rect(new_len, search_cell);

        neighbors = rect.findPoints(k); 
        
        Collections.sort(neighbors, new MyComparator(search_point));

//        println(neighbors);
        return neighbors.subList(0,k);
    }

    private class MyComparator implements Comparator<Point>
    {
        Point search_point;

        public MyComparator(Point search_point)
        {
            this.search_point = search_point;
        }

        @Override
        public int compare(Point a, Point b)
        {
            double dist_a = sqDist(a, search_point);
            double dist_b = sqDist(b, search_point);

            if(dist_a > dist_b)
                return 1;
            if(dist_a < dist_b)
                return -1;

            return 0;
        }
    }

    private class Rect
    {
        private int len;
        private int[] cent;

        public Rect(int len, int[] cent)
        {
            this.len = len;
            this.cent = cent;
        }

        public List<Point> findPoints(int k)
        {
            List<Point> neighbors = new ArrayList<Point>();

            int width = grid.get(0).size();
            int height = grid.size();

//            println("**********************************************");
            while(neighbors.size() < k)
            {
                neighbors.clear();
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
//                        println(grid.get(i).get(j));
                        neighbors.addAll(grid.get(i).get(j));
                    }
                }

                len++;
            }

//            println(neighbors);
            return neighbors;
        }
    }
}
