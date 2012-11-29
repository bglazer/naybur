package naybur.grid;

import java.util.LinkedList;

class OIGrid
{
    private LinkedList<Double[]>[][] grid; 

    private double delta;

    private int len;

    private double[][] point_list;

    public OIGrid(double[][] points, double delta) 
    {
        this.delta = delta;

        point_list = points;

        for(int i = 0; i < point_list.size(); i++)
        {
            cell_index_x = Math.floor(point_list[i][0]/delta);
            cell_index_y = Math.floor(point_list[i][1]/delta);

            LinkedList<Double[]> cell = grid[cell_index_x][cell_index_y];

            cell.add(point_list[i]);

        }
    }

    public void overhaul(double[] points)
    {

    }

}
