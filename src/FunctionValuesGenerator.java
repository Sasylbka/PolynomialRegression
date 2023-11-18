import java.util.ArrayList;
import java.util.List;

public class FunctionValuesGenerator {

    public ArrayList<Point> cosFunction(int numberOfPoints){
        ArrayList<Point> points= new ArrayList<>();
        for(int i =0;i<numberOfPoints;i++){
            double x=Math.random()+2*Math.PI;
            points.add(new Point(x,Math.cos(x)));
        }
        return points;
    }
    public ArrayList<Point> sinFunction(int numberOfPoints){
        ArrayList<Point> points= new ArrayList<>();
        for(int i =0;i<numberOfPoints;i++){
            double x=Math.random()+2*Math.PI;
            points.add(new Point(x,x*Math.sin(x)));
        }
        return points;
    }

    public ArrayList<Point> polynomialFunction(int numberOfPoints){
        ArrayList<Point> points= new ArrayList<>();
        int temp=-5;
        for(int i =0;i<numberOfPoints;i++){
            double x = temp;
            temp+=1;
            points.add(new Point(x,5*Math.pow(x,3)+Math.pow(x,2)+5));
        }
        return points;
    }
}
