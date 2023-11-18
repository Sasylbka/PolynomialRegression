import java.util.ArrayList;

public class FunctionsForMetrics {
    public ArrayList<Point> Function(double[]A,double[] x,int numberOfPoints,int pow){
        ArrayList<Point> points=new ArrayList<Point>();
        for(int i =0;i<numberOfPoints;i++){
            double y=0;
            for(int j=0;j<=pow;j++) {
                y+=Math.pow(x[i],j)*A[j];
            }
            points.add(new Point(x[i],y));
        }
        return points;
    }
}
