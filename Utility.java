package naybur;

import java.util.ArrayList;

public class Utility 
{
    public static double sqDist(ArrayList<Double> a, ArrayList<Double> b)
    {
        double dist = 0;

        for(int i = 0; i < a.size(); i++)
        {
            dist += Math.pow(a.get(i) - b.get(i), 2);
        }

        return dist;
    }

    public static double sqDistArray(double[] a, double[] b)
    {
        double dist = 0;

        for(int i = 0; i < a.length; i++)
        {
            dist += Math.pow(a[i] - b[i], 2);
        }

        return dist;
    }

    public static double[][] map(double[][] point_list, double[] start_range, double[] end_range, int dims)
    {
        double start_diff = Math.abs(start_range[0] - start_range[1]);
        double end_diff = Math.abs(end_range[0] - end_range[1]);

        for(int i = 0; i < point_list.length; i++)
        {
            for(int j = 0; j < dims; j++)
            {
                point_list[i][j] = ((point_list[i][j] - start_range[0])/start_diff + end_range[0]) * end_diff; 
            }
        }

        return point_list;
    }

    public static double[] map(double[] point_list, double[] start_range, double[] end_range, int dims)
    {
        double start_diff = Math.abs(start_range[0] - start_range[1]);
        double end_diff = Math.abs(end_range[0] - end_range[1]);

        for(int j = 0; j < dims; j++)
        {
            point_list[j] = ((point_list[j] - start_range[0])/start_diff + end_range[0]) * end_diff; 
        }

        return point_list;
    }


}
