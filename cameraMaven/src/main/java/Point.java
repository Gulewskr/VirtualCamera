import java.awt.geom.Line2D;

public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double[] cordinatesToScreen(double focal, double view_width, double view_heigh)
    {
        double from_focal = focal/z ;
        double _x = from_focal * x + view_width / 2;
        double _y = view_heigh / 2 - from_focal * y;
        return new double[] { _x, _y };
    }

    public void matrixCalculate(double[][] matrix)
    {
        double[] res = MatrixCalculation.MatrixMultiplication(matrix, new double[]{x, y, z, 1});
        x = res[0];
        y = res[1];
        z = res[2];
    }

    public void translate(double[][] translationMatrix)
    {
        x += translationMatrix[0][3];
        y += translationMatrix[1][3];
        z += translationMatrix[2][3];
    }
}
