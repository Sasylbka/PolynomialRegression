import java.util.ArrayList;

public class Metrics {
    public double[] calculateMetrics(ArrayList<Point> testPoints,ArrayList<Point>predictPoints,String functionType){
        double MAE=0;
        double RMSE=0;
        double RSS=0;

        for(int i=0;i<testPoints.size();i++){
            MAE+=Math.abs(testPoints.get(i).y-predictPoints.get(i).y);
            RMSE+=Math.pow(testPoints.get(i).y-predictPoints.get(i).y,2);
        }
        RSS=RMSE;
        MAE=MAE/testPoints.size();
        RMSE=Math.sqrt(RMSE/testPoints.size());
        //System.out.println("MAE for "+functionType+"="+MAE);
        //System.out.println("RMSE for "+functionType+"="+RMSE);
        //System.out.println("RSS for "+functionType+"="+RSS+"\n");
        return new double[]{RSS,MAE,RMSE};
    }
}
