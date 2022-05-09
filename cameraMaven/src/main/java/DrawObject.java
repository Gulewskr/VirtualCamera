import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class DrawObject {

    class Line
    {
        public Point a;
        public Point b;
    }

    private List<Line> edges = new ArrayList<Line>();

    public void addEdge(float x1, float y1, float z1, float x2, float y2, float z2)
    {
        Line l = new Line();
        l.a = new Point(x1, y1, z1);
        l.b = new Point(x2, y2, z2);
        edges.add(l);
    }


    public void transform(double[][] matrix) {
        for(Line l : edges)
        {
            l.a.matrixCalculate(matrix);
            l.b.matrixCalculate(matrix);
        }
    }

    public void rotate(double[][] rotationMatrix) {
        for(Line l : edges)
        {
            l.a.matrixCalculate(rotationMatrix);
            l.b.matrixCalculate(rotationMatrix);
        }
    }

    public List<Line2D> getEdges(float focal, float view_width, float view_height)
    {
        List<Line2D> lines = new ArrayList<Line2D>();

        for(Line l : edges)
        {
            if(l.a.getZ() > focal && l.b.getZ() > focal)
            {
                double[] _a = l.a.cordinatesToScreen(focal, view_width, view_height);
                double[] _b = l.b.cordinatesToScreen(focal, view_width, view_height);

                lines.add(new Line2D.Double(_a[0], _a[1], _b[0], _b[1]));
            }
        }
        return lines;
    }

}
