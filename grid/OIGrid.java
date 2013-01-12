package naybur.grid;

import naybur.Utility;
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
    private double[] range[];

    public OIGrid(double[][] points, double[][] range)
    {
        grid = new LinkedList[(int)(1/delta)][(int)(1/delta)];
        overhaul(points);

        this.range = range;
        double[] end_range = { 0,1 }; 
        points = Utility.map(points, range, end_range, 2);
    }

    public void overhaul(double[][] points)
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

    public double[][] findNearest(double[] search_point, int k)
    {
        double[] end_range  = {0,1};
        
        double[] searchp_mapped = Utility.map(search_point, range, end_range, 2); 

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
        public ArrayList points;

        public Rect(int len, int[] cent)
        {
            this.len = len;
            this.cent = cent;
        }

        public ArrayList findPoints(int k)
        {
            ArrayList points = new ArrayList();

            boolean[][] visited = new boolean[grid.length][grid[0].length];
            LinkedList queue = new LinkedList(), next_queue = new LinkedList();

            int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

            next_queue.push(cent);
            visited[cent[0]][cent[1]] = true;

            while(points.size() < k)
            {
                queue = next_queue;
                next_queue.clear();

                while(!queue.isEmpty())
                {
                    int[] cell = (int[])queue.pop();
                    points.addAll(grid[cell[0]][cell[1]]);

                    //North
                    int[] temp_cell = { cell[0], cell[1] + 1};
                    if(temp_cell[1] < grid.length && !visited[temp_cell[0]][temp_cell[1]])
                    {
                        if(temp_cell[1] > cent[1] + len)
                            next_queue.push(temp_cell);
                    }
                    else if(!visited[temp_cell[0]][temp_cell[1]])
                    {
                        queue.push(temp_cell);
                        points.addAll(grid[temp_cell[0]][temp_cell[1]]);
                    }

                    //South
                    temp_cell[0] = cell[0];
                    temp_cell[1] = cell[1];

                    if(temp_cell[1] >= 0 && !visited[temp_cell[0]][temp_cell[1]])
                    {
                        if(temp_cell[1] < cent[1] - len)
                            next_queue.push(grid[temp_cell[0]][temp_cell[1]]);
                    }
                    else if(!visited[temp_cell[0]][temp_cell[1]])
                    {
                        queue.push(temp_cell);
                        points.addAll(grid[temp_cell[0]][temp_cell[1]]);
                    }

                    //East
                    temp_cell[0] = cell[0] + 1;
                    temp_cell[1] = cell[1];

                    if(temp_cell[0] < grid.length && !visited[temp_cell[0]][temp_cell[1]])
                    {
                        if(temp_cell[0] > cent[0] + len)
                            next_queue.push(grid[temp_cell[0]][temp_cell[1]]);
                    } 
                    else if(!visited[temp_cell[0]][temp_cell[1]])
                    {
                        queue.push(temp_cell);
                        points.addAll(grid[temp_cell[0]][temp_cell[1]]);
                    }

                    //West
                    temp_cell[0] = cell[0] - 1;
                    temp_cell[1] = cell[1];

                    if(temp_cell[0] >= 0 && !visited[temp_cell[0]][temp_cell[1]])
                    {
                        if(temp_cell[0] < cent[0] - len)
                            next_queue.push(grid[temp_cell[0]][temp_cell[1]]);
                    }
                    else if(!visited[temp_cell[0]][temp_cell[1]])
                    {
                        queue.push(temp_cell);
                        points.addAll(grid[temp_cell[0]][temp_cell[1]]);
                    }
                }
            }

            len++;

            return points;
        }
    }

}
