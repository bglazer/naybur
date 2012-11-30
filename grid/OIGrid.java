package naybur.grid;

import naybur.Utility;
import java.util.LinkedList;

class OIGrid
{
    private LinkedList[][] grid; 

    private double delta;

    private int len;

    private double[][] point_list;

    public OIGrid(double[][] points, double delta) 
    {
        grid = new LinkedList[(int)(1/delta)][(int)(1/delta)];
        overhaul(points, delta);
    }

    public void overhaul(double[][] points, double delta)
    {
        this.delta = delta;

        point_list = points;

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

    public double[] findNearest(double[] search_point)
    {
        double[] result = {0, 0};
        return result;
    }

}
