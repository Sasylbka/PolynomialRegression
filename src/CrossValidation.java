import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CrossValidation {
    private final int[] M=new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    private final double minL=-1000;
    private final double maxL=1000;
    public void validationForPolynomialFunction(int numberOfPoints){
        FunctionValuesGenerator generator=new FunctionValuesGenerator();
        FunctionsForMetrics functionsForMetrics= new FunctionsForMetrics();
        Regularization regularization = new Regularization();
        Metrics metrics = new Metrics();
        ArrayList<Point> points= generator.polynomialFunction(numberOfPoints);
        ArrayList<Point> trainPoints= new ArrayList<>();
        ArrayList<Point>testPoints=new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        int testSize = (int)Math.round(points.size() *0.2);
        points.forEach(next -> {
            int index = count.getAndIncrement();
            if(index<testSize) {
                testPoints.add(next);
            }
            else {trainPoints.add(next);}
        });
        double[]x=getXs(testPoints);
        double MAE=Integer.MAX_VALUE;
        double RMSE=Integer.MAX_VALUE;
        double RSS=Integer.MAX_VALUE;
        int satisfyingM=-1;
        double satisfyingL=0;
        for(int z=0;z<M.length;z++) {
            for(double j =maxL;j>minL;j=j-0.1) {
                double[] regularizationCoefficients = regularization.L2Regularization(trainPoints, M[z] + 1, j);
                ArrayList<Point> predictPoints = functionsForMetrics.Function(regularizationCoefficients,x, testPoints.size(), M[z]);

                double [] metricScore=metrics.calculateMetrics(testPoints,predictPoints,"Polynomial");
                if(metricScore[0]<RSS && metricScore[1]<MAE && metricScore[2]< RMSE){
                    RSS=metricScore[0];
                    MAE=metricScore[1];
                    RMSE=metricScore[2];

                    satisfyingM=z;
                    satisfyingL=j;
                }
            }
        }
        System.out.println("PolynomialFunction:");
        System.out.println("The best M="+satisfyingM+" the best L="+satisfyingL);
        System.out.println("With results:");
        System.out.println("RSS="+RSS);
        System.out.println("MAE="+MAE);
        System.out.println("RMSE="+RMSE+"\n");

    }
    public void validationForCosFunction(int numberOfPoints){
        FunctionValuesGenerator generator=new FunctionValuesGenerator();
        FunctionsForMetrics functionsForMetrics= new FunctionsForMetrics();
        Regularization regularization = new Regularization();
        Metrics metrics = new Metrics();
        ArrayList<Point> points= generator.cosFunction(numberOfPoints);
        ArrayList<Point> trainPoints= new ArrayList<>();
        ArrayList<Point>testPoints=new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        int testSize = (int)Math.round(points.size() *0.2);
        points.forEach(next -> {
            int index = count.getAndIncrement();
            if(index<testSize) {
                testPoints.add(next);
            }
            else {trainPoints.add(next);}
        });
        double[]x=getXs(testPoints);
        double MAE=Integer.MAX_VALUE;
        double RMSE=Integer.MAX_VALUE;
        double RSS=Integer.MAX_VALUE;
        int satisfyingM=-1;
        double satisfyingL=0;
        for(int z=0;z<M.length;z++) {
            for(double j =maxL;j>minL;j=j-0.1) {
                double[] regularizationCoefficients = regularization.L2Regularization(trainPoints, M[z] + 1, j);
                ArrayList<Point> predictPoints = functionsForMetrics.Function(regularizationCoefficients,x, testPoints.size(), M[z]);

                double [] metricScore=metrics.calculateMetrics(testPoints,predictPoints,"Cos");
                if(metricScore[0]<RSS && metricScore[1]<MAE && metricScore[2]< RMSE){
                    RSS=metricScore[0];
                    MAE=metricScore[1];
                    RMSE=metricScore[2];

                    satisfyingM=z;
                    satisfyingL=j;
                }
            }
        }
        System.out.println("CosFunction:");
        System.out.println("The best M="+satisfyingM+" the best L="+satisfyingL);
        System.out.println("With results:");
        System.out.println("RSS="+RSS);
        System.out.println("MAE="+MAE);
        System.out.println("RMSE="+RMSE+"\n");

    }

    public void validationForSinFunction(int numberOfPoints){
        FunctionValuesGenerator generator=new FunctionValuesGenerator();
        FunctionsForMetrics functionsForMetrics= new FunctionsForMetrics();
        Regularization regularization = new Regularization();
        Metrics metrics = new Metrics();
        ArrayList<Point> points= generator.sinFunction(numberOfPoints);
        ArrayList<Point> trainPoints= new ArrayList<>();
        ArrayList<Point>testPoints=new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        int testSize = (int)Math.round(points.size() *0.2);
        points.forEach(next -> {
            int index = count.getAndIncrement();
            if(index<testSize) {
                testPoints.add(next);
            }
            else {trainPoints.add(next);}
        });
        double[]x=getXs(testPoints);
        double MAE=Integer.MAX_VALUE;
        double RMSE=Integer.MAX_VALUE;
        double RSS=Integer.MAX_VALUE;
        int satisfyingM=-1;
        double satisfyingL=0;
        for(int z=0;z<M.length;z++) {
            for(double j =maxL;j>minL;j=j-0.1) {
                double[] regularizationCoefficients = regularization.L2Regularization(trainPoints, M[z] + 1, j);
                ArrayList<Point> predictPoints = functionsForMetrics.Function(regularizationCoefficients,x, testPoints.size(), M[z]);

                double [] metricScore=metrics.calculateMetrics(testPoints,predictPoints,"Sin");
                if(metricScore[0]<RSS && metricScore[1]<MAE && metricScore[2]< RMSE){
                    RSS=metricScore[0];
                    MAE=metricScore[1];
                    RMSE=metricScore[2];

                    satisfyingM=z;
                    satisfyingL=j;
                }
            }
        }
        System.out.println("SinFunction:");
        System.out.println("The best M="+satisfyingM+" the best L="+satisfyingL);
        System.out.println("With results:");
        System.out.println("RSS="+RSS);
        System.out.println("MAE="+MAE);
        System.out.println("RMSE="+RMSE);
    }
    public double[] getXs(ArrayList<Point>points){
        double[] x=new double[points.size()];
        for(int i =0;i<points.size();i++){
            x[i]=points.get(i).x;
        }
        return x;
    }
    public double[] getYs(ArrayList<Point>points){
        double[] y=new double[points.size()];
        for(int i =0;i<points.size();i++){
            y[i]=points.get(i).y;
        }
        return y;
    }
}
