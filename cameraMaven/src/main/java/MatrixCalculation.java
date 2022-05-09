public class MatrixCalculation {
    static double[] MatrixMultiplication(double[][] a, double[] b)
    {
        double x = a[0][0] * b[0] + a[0][1] * b[1] + a[0][2] * b[2] + a[0][3] * b[3];
        double y = a[1][0] * b[0] + a[1][1] * b[1] + a[1][2] * b[2] + a[1][3] * b[3];
        double z = a[2][0] * b[0] + a[2][1] * b[1] + a[2][2] * b[2] + a[2][3] * b[3];
       /* System.out.println("'calculated'");
        System.out.println(a[0][0] + " : " + a[0][1] + " : " + a[0][2] + " : " + a[0][3]);
        System.out.println(a[1][0] + " : " + a[1][1] + " : " + a[1][2] + " : " + a[1][3]);
        System.out.println(a[2][0] + " : " + a[2][1] + " : " + a[2][2] + " : " + a[2][3]);
        System.out.println(a[3][0] + " : " + a[3][1] + " : " + a[3][2] + " : " + a[3][3]);
        System.out.println(b[0] + " : " + b[1] + " : " + b[2]);
        */
        return new double[]{x,y,z};
    }
}
