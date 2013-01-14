package naybur.grid;

import static naybur.Utility.*;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;

class OIGrid
{
    private LinkedList[][] grid; 

    private double delta;

    private int len;

    private double[][] point_list;
    private double[][] range;

    public OIGrid(double[][] points, double[][] range)
    {
        delta = 1/Math.sqrt(point.length);

        grid = new LinkedList[(int)(1/delta)][(int)(1/delta)];

        this.range = range;
        this.point_list = Utility.map(points, range);

        overhaul(point_list);
    }

    public void overhaul(double[][] points)
    {
        for(int i = 0; i < point_list.length; i++)
        {
            int cell_index_x = (int)Math.floor(point_list[i][0]/delta);
            int cell_index_y = (int)Math.floor(point_list[i][1]/delta);
            
            LinkedList cell = grid[cell_index_x][cell_index_y];
            
            if(cell == null)
                cell  = new LinkedList();

            cell.add(point_list[i]);
        }
    }

    public double[][] findNearest(double[] search_point, int k)
    {
        double[] searchp_mapped = map(search_point, range); 

        int[] search_cell = { (int)Math.floor(searchp_mapped[0]/delta), (int)Math.floor(searchp_mapped[1]/delta) } ;

        Rect rect = new Rect(0, searchp_mapped);

        ArrayList points = rect.findPoints(3); 
        
        double[] dist = new double[points.size()];

        for(int i = 0; i < points.size(); i++)
        {
            dist[i] = Utility.sqDistArray(search_point, (double[])points.get(i));
        }

        double[][] results = new double[k][2];

        return results;
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

        public ArrayList<Double> findPoints(int k)
        {
            points_found = 0;

            LinkedList<Double> points = new LinkedList<Double>();
            int width = grid[0].length;
            int height = grid.length;

            while(points_found < k)
            {
                int start_x = cent[0] - len < 0 ? 0 : cent[0] - len;
                int end_x = cent[0] + len > width ? 0 : cent[0] + len;

                int start_y = cent[1] - len < 0 ? 0 : cent[1] - len;
                int end_y = cent[1] + len > height ? 0 : cent[1] + len;

                for(int i = start_x; i < end_x; i++)
                {
                    for(int j = start_y; j < end_y; j++)
                    {
                        points.addAll(grid[i][j]);
                    }
                }

                len++;
            }
        }
    }

}
