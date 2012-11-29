package naybur.grid;

import java.util.LinkedList;

class OIGrid
{
    private LinkedList[][] grid; 

    private double delta;

    private int len;

    private double[][] point_list;

    public OIGrid(double[][] points, double width, double height, double delta) 
    {
        grid = new LinkedList[(int)(width/delta)][(int)(height/delta)];
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
            
            cell.add(point_list[i]);

        }
    }

}
