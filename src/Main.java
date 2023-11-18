
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JPanel {
    public static void main(String[] args) {
        FunctionValuesGenerator generator=new FunctionValuesGenerator();
        CrossValidation crossValidation=new CrossValidation();
        int numberOfPoints=60;

        crossValidation.validationForPolynomialFunction(numberOfPoints);
        crossValidation.validationForCosFunction(numberOfPoints);
        crossValidation.validationForSinFunction(numberOfPoints);


    }
}
